package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.*;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:19 2018/5/5
 * @Modified By:
 */
@Service
public class PickingApplyHeaderService {
	@Autowired
	private PickingApplyHeaderRepository pickingApplyHeaderRepository;

	@Autowired
	private MessageQueueService messageQueueService;

	@Autowired
	private PickingAuditRepository pickingAuditRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MaterialsEntryRepository materialsEntryRepository;

	@Autowired
	private MaterialsTotalRepository materialsTotalRepository;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProcessManageRepository processManageRepository;

	/**
	 * 新增
	 *
	 * @param pickingApplyHeader
	 * @return
	 */
	@Transactional
	public PickingApplyHeader save(PickingApplyHeader pickingApplyHeader) {

		// 验证领料部门
		if (pickingApplyHeader.getDepartment() == null
				|| departmentService.findOne(pickingApplyHeader.getDepartment().getCode()) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_DEPARTMENT_NULL_OR_NOT_EXIST);
		}

		// 验证申请人
		if (pickingApplyHeader.getApplicant() == null
				|| userService.findOne(pickingApplyHeader.getApplicant().getCode()) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_USER_NULL_OR_NOT_EXIST);
		}

		// 验证审核流程
		ProcessManage processManage = null;
		if (pickingApplyHeader.getProcessManage() == null || (processManage = processManageRepository
				.findOne(pickingApplyHeader.getProcessManage().getCode())) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_PROCESS_MANAGE_NULL_OR_NOT_EXIST);
		}

		int times = 0;
		String number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
		while (pickingApplyHeaderRepository.findFirstByNumber(number) != null) {
			times++;
			if (times > 100) {
				throw new MesException(EnumException.NOT_UNIQUE_NUMBER);
			}
			number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
		}
		// 设置出库编号
		pickingApplyHeader.setNumber(number);

		// 设置申请日期
		pickingApplyHeader.setApplyDate(new Date());

		// 设置申请流程
		pickingApplyHeader.setProcess(processManage.getProcess());

		// 设置审核状态
		if (pickingApplyHeader.getAuditStatus() != null
				&& pickingApplyHeader.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			// 待审核
			pickingApplyHeader.setAuditStatus(GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT);
		} else {
			// 已保存,未提交
			pickingApplyHeader.setAuditStatus(GlobalUtil.PickingApplyHeaderStatus.NOT_SUBMIT);
		}

		// 设置领料状态
		pickingApplyHeader.setPickingStatus(GlobalUtil.PickingApplyHeaderStatus.PRE_PICKING);

		if (pickingApplyHeader.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			pickingApplyHeader.setCurAuditorCode(processManage.getLeader1().getCode());
		}

		PickingApplyHeader picking = pickingApplyHeaderRepository.save(pickingApplyHeader);

		// 如果已提交, 则发消息给审核人
		if (picking.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			// 【添加消息】给审核人1
			MessageQueue messageQueue = new MessageQueue();
			messageQueue.setContent(picking.getDepartment().getName());
			messageQueue.setUrl(GlobalUtil.AppPageType.PICKING_AUDIT.getCode() + "-" + picking.getProcess().getCode()
					+ "-" + picking.getCode());
			messageQueue.setAddressee(processManage.getLeader1());
			messageQueueService.auditSave(messageQueue);
		}

		return picking;
	}

	/**
	 * 更新
	 *
	 * @param pickingApplyHeader
	 * @return
	 */
	@Transactional
	public PickingApplyHeader update(PickingApplyHeader pickingApplyHeader) {
		pickingApplyHeaderRepository.delete(pickingApplyHeader.getCode());

		// 验证领料部门
		if (pickingApplyHeader.getDepartment() == null
				|| departmentService.findOne(pickingApplyHeader.getDepartment().getCode()) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_DEPARTMENT_NULL_OR_NOT_EXIST);
		}

		// 验证申请人
		if (pickingApplyHeader.getApplicant() == null
				|| userService.findOne(pickingApplyHeader.getApplicant().getCode()) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_USER_NULL_OR_NOT_EXIST);
		}

		// 验证审核流程
		ProcessManage processManage = null;
		if (pickingApplyHeader.getProcessManage() == null || (processManage = processManageRepository
				.findOne(pickingApplyHeader.getProcessManage().getCode())) == null) {
			throw new MesException(EnumException.SUBMIT_FAILED_PROCESS_MANAGE_NULL_OR_NOT_EXIST);
		}

		// 设置申请日期
		pickingApplyHeader.setApplyDate(new Date());

		pickingApplyHeader.setProcess(processManage.getProcess());

		// 设置审核状态
		if (pickingApplyHeader.getAuditStatus() != null
				&& pickingApplyHeader.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			// 待审核
			pickingApplyHeader.setAuditStatus(GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT);
		} else {
			// 已保存,未提交
			pickingApplyHeader.setAuditStatus(GlobalUtil.PickingApplyHeaderStatus.NOT_SUBMIT);
		}

		// 设置领料状态
		pickingApplyHeader.setPickingStatus(GlobalUtil.PickingApplyHeaderStatus.PRE_PICKING);

		if (pickingApplyHeader.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			pickingApplyHeader.setCurAuditorCode(processManage.getLeader1().getCode());
		}

		PickingApplyHeader save = pickingApplyHeaderRepository.save(pickingApplyHeader);

		// 如果已提交, 则发消息给审核人
		if (save.getAuditStatus() == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			// 【添加消息】给审核人1
			MessageQueue messageQueue = new MessageQueue();
			messageQueue.setContent(save.getDepartment().getName());
			messageQueue.setUrl(GlobalUtil.AppPageType.PICKING_AUDIT.getCode() + "-" + save.getProcess().getCode() + "-"
					+ save.getCode());
			messageQueue.setAddressee(save.getProcessManage().getLeader1());
			messageQueueService.auditSave(messageQueue);
		}

		return save;
	}

	/**
	 * 紧急审核
	 *
	 * @param auditStatus
	 * @param note
	 * @param code
	 * @param auditCode
	 * @param nextAuditCode
	 */
	public void updateAuditStatusByCodeUrgent(Integer auditStatus, String note, Long code, String auditCode,
			String nextAuditCode) {
		// 判断审核状态的合法性
		if (auditStatus != GlobalUtil.PickingApplyHeaderStatus.APPROVE
				&& auditStatus != GlobalUtil.PickingApplyHeaderStatus.NOT_APPROVE) {
			throw new MesException(EnumException.AUDIT_STATUS_NOT_LAWER);
		}

		// 判断申请单是否存在
		PickingApplyHeader pickingApplyHeader = pickingApplyHeaderRepository.findByCode(code);
		if (pickingApplyHeader == null) {
			throw new MesException(EnumException.AUDIT_FAILED_PICKING_APPLY_NOT_EXIST);
		}

		// 判断申请单状态是否已经审核
		if (pickingApplyHeader.getAuditStatus() > GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			throw new MesException(EnumException.AUDITOR_FAILED_END_AUDIT);
		}

		// 判断审核人
		if (userRepository.findOne(auditCode) == null) {
			throw new MesException(EnumException.AUDITOR_NOT_EXIST);
		}

		// 判断审核人是不是当前审核人
		if (!auditCode.equals(pickingApplyHeader.getCurAuditorCode())) {
			throw new MesException(EnumException.AUDITOR_NOT_CUR_AUDITOR);
		}

		// 如果是打回
		if (String.valueOf(GlobalUtil.PickingAuditStatus.REPULSE).equals(nextAuditCode)) {
			endAudit(pickingApplyHeader, note, code, GlobalUtil.PickingAuditStatus.NOT_APPROVE);
		}

		// 如果是终止
		if (String.valueOf(GlobalUtil.PickingAuditStatus.STOP).equals(nextAuditCode)) {
			endAudit(pickingApplyHeader, note, code, auditStatus);
		}

		// 校验下一个审核人
		User nextAuditor = userRepository.findOne(nextAuditCode);
		if (nextAuditor == null) {
			throw new MesException(EnumException.NEXT_AUDITOR_NOT_EXIST);
		}

		// 创建审批记录
		PickingAudit pickingAudit = new PickingAudit();
		pickingAudit.setPickingApplyHeader(pickingApplyHeader);
		pickingAudit.setProcessManage(pickingApplyHeader.getProcessManage());
		pickingAudit.setAuditTime(new Date());
		pickingAudit.setAuditor(pickingApplyHeader.getProcessManage().getLeader1());
		pickingAudit.setNote(note);
		pickingAudit.setAuditResult(auditStatus);
		pickingAudit = pickingAuditRepository.save(pickingAudit);

		// 【添加消息】下一位审核人
		MessageQueue messageQueue = new MessageQueue();
		messageQueue.setContent(pickingApplyHeader.getDepartment().getName());
		messageQueue.setUrl(GlobalUtil.AppPageType.PICKING_AUDIT.getCode() + "-"
				+ pickingApplyHeader.getProcess().getCode() + "-" + pickingApplyHeader.getCode());
		messageQueue.setAddressee(nextAuditor);
		messageQueueService.auditSave(messageQueue);

		// 更新当前审核人
		pickingApplyHeaderRepository.updateCurAuditorCodeByCode(nextAuditCode, code);
	}

	/**
	 * 正常审核
	 *
	 * @param auditStatus
	 * @param note
	 * @param code
	 * @param auditCode
	 */
	public void updateAuditStatusByCode(Integer auditStatus, String note, Long code, String auditCode) {

		// 判断审核状态的合法性
		if (auditStatus != GlobalUtil.PickingApplyHeaderStatus.APPROVE
				&& auditStatus != GlobalUtil.PickingApplyHeaderStatus.NOT_APPROVE) {
			throw new MesException(EnumException.AUDIT_STATUS_NOT_LAWER);
		}

		// 判断申请单是否存在
		PickingApplyHeader pickingApplyHeader = pickingApplyHeaderRepository.findByCode(code);
		if (pickingApplyHeader == null) {
			throw new MesException(EnumException.AUDIT_FAILED_PICKING_APPLY_NOT_EXIST);
		}

		// 判断申请单是状态是否已经审核
		if (pickingApplyHeader.getAuditStatus() > GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			throw new MesException(EnumException.AUDITOR_FAILED_END_AUDIT);
		}

		// 判断审核人
		if (userRepository.findOne(auditCode) == null) {
			throw new MesException(EnumException.AUDITOR_NOT_EXIST);
		}

		// 判断审核人是不是当前审核人
		if (!auditCode.equals(pickingApplyHeader.getCurAuditorCode())) {
			throw new MesException(EnumException.AUDITOR_NOT_CUR_AUDITOR);
		}

		endAudit(pickingApplyHeader, note, code, auditStatus);
	}

	/**
	 * 审核结束
	 *
	 * @param pickingApplyHeader
	 * @param note
	 * @param code
	 * @param auditStatus
	 */
	@Transactional
	public void endAudit(PickingApplyHeader pickingApplyHeader, String note, Long code, Integer auditStatus) {
		// 更改申请单的状态
		pickingApplyHeaderRepository.updateAuditStatusByCode(auditStatus, code);

		// 创建审批记录
		PickingAudit pickingAudit = new PickingAudit();
		pickingAudit.setPickingApplyHeader(pickingApplyHeader);
		pickingAudit.setProcessManage(pickingApplyHeader.getProcessManage());
		pickingAudit.setAuditTime(new Date());
		pickingAudit.setAuditor(pickingApplyHeader.getProcessManage().getLeader1());
		pickingAudit.setNote(note);
		pickingAudit.setAuditResult(auditStatus);
		pickingAudit = pickingAuditRepository.save(pickingAudit);

		// 【添加消息】申请人
		MessageQueue messageQueue = new MessageQueue();
		messageQueue.setContent("原料出库审批已完成");
		messageQueue.setUrl(pickingApplyHeader.getCode() + "-" + pickingAudit.getCode());
		messageQueue.setAddressee(pickingApplyHeader.getApplicant());
		messageQueueService.pickingAuditSave(messageQueue);

		if (auditStatus == GlobalUtil.PickingApplyHeaderStatus.APPROVE) {
			// 修改库存
			Set<PickingApply> pickingApplies = pickingApplyHeader.getPickingApplies();
			double weightSum = 0.0;
			for (PickingApply pickingApply : pickingApplies) {
				if (pickingApply.getWeight() != null) {
					materialsEntryRepository.updateWeightByBatchNumber(pickingApply.getWeight(),
							pickingApply.getBatchNumber());
					weightSum += pickingApply.getWeight();
				}
			}

			// TODO 应该写触发器总重量
			materialsTotalRepository.updateWeightByCode(weightSum, pickingApplyHeader.getCode());

			// 【添加消息】给库管
			messageQueue = new MessageQueue();
			messageQueue.setContent(pickingApplyHeader.getDepartment().getName());
			messageQueue.setUrl(pickingApplyHeader.getCode() + "");
			messageQueueService.godownPrepareGoods(messageQueue);
		}
	}

	/**
	 * 删除
	 *
	 * @param code
	 */
	public void delete(Long code) {
		pickingApplyHeaderRepository.delete(code);
	}

	/**
	 * 查询一个
	 *
	 * @param code
	 * @return
	 */
	public PickingApplyHeader findOne(Long code) {
		return pickingApplyHeaderRepository.findByCode(code);
	}

	/**
	 * 查询所有
	 *
	 * @return
	 */
	public List<PickingApplyHeader> findAll() {
		return pickingApplyHeaderRepository.findAll();
	}

	/**
	 * 通过分页查询所有
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findAll(pageable);
	}

	/**
	 * 通过部门和申请时间查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByDepartmentAndApplyTimeBetween(Department department, Long applyTime,
			Integer page, Integer size, String sortFieldName, Integer asc) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTimeInMillis(applyTime);
		calendar1.set(Calendar.HOUR, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);

		calendar2.setTimeInMillis(calendar1.getTimeInMillis());
		calendar2.add(Calendar.DAY_OF_MONTH, 1);

		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartmentAndApplyDateBetween(department, calendar1.getTime(),
				calendar2.getTime(), pageable);
	}

	/**
	 * 通过部门和领料时间查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByDepartmentAndPickingTimeBetween(Department department, Long pickingTime,
			Integer page, Integer size, String sortFieldName, Integer asc) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTimeInMillis(pickingTime);
		calendar1.set(Calendar.HOUR, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);

		calendar2.setTimeInMillis(calendar1.getTimeInMillis());
		calendar2.add(Calendar.DAY_OF_MONTH, 1);

		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartmentAndPickingTimeBetween(department, calendar1.getTime(),
				calendar2.getTime(), pageable);
	}

	/**
	 * 批删除
	 *
	 * @param pickingApplyHeaders
	 */
	public void deleteInBatch(Set<PickingApplyHeader> pickingApplyHeaders) {
		pickingApplyHeaderRepository.deleteInBatch(pickingApplyHeaders);
	}

	/**
	 * 通过审核状态查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByAuditStatus(Integer auditStatus, Integer page, Integer size,
			String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByAuditStatus(auditStatus, pageable);
	}

	/**
	 * 通过审核状态查询
	 *
	 * @return
	 * @throws Exception
	 */
	public List<PickingApplyHeader> findByAuditStatus(Integer auditStatus) {
		if (auditStatus == GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT) {
			return pickingApplyHeaderRepository.findByAuditStatus(auditStatus);
		} else if (auditStatus == GlobalUtil.PickingApplyHeaderStatus.APPROVE
				|| auditStatus == GlobalUtil.PickingApplyHeaderStatus.NOT_APPROVE) {
			return pickingApplyHeaderRepository.findByAuditStatusOrAuditStatus(
					GlobalUtil.PickingApplyHeaderStatus.APPROVE, GlobalUtil.PickingApplyHeaderStatus.NOT_APPROVE);
		}

		return pickingApplyHeaderRepository.findByAuditStatus(auditStatus);
	}

	/**
	 * 通过领料状态查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByPickingStatus(Integer pickingStatus, Integer page, Integer size,
			String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByPickingStatus(pickingStatus, pageable);
	}

	/**
	 * 通过流程类型查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByProcessManage(ProcessManage processManage, Integer page, Integer size,
			String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByProcessManage(processManage, pageable);
	}

	/**
	 * 通过审核状态和流程类型查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByAuditStatusAndProcessManage(Integer auditStatus, ProcessManage processManage,
			Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByAuditStatusAndProcessManage(auditStatus, processManage, pageable);
	}

	/**
	 * 通过领料状态和流程类型查询-分页
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<PickingApplyHeader> findByPickingStatusAndProcessManage(Integer pickingStatus,
			ProcessManage processManage, Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByPickingStatusAndProcessManage(pickingStatus, processManage, pageable);
	}

	/**
	 * 通过部门, 流程, 领料状查询-分页
	 *
	 * @param department
	 * @param processManage
	 * @param pickingStatus
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByDepartmentAndProcessManageAndPickingStatus(Department department,
			ProcessManage processManage, Integer pickingStatus, Integer page, Integer size, String sortFieldName,
			Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartmentAndProcessManageAndPickingStatus(department, processManage,
				pickingStatus, pageable);
	}

	/**
	 * 通过部门查询-分页
	 *
	 * @param department
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByDepartment(Department department, Integer page, Integer size,
			String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartment(department, pageable);
	}

	/**
	 * 通过部门查询-分页
	 *
	 * @param department
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByDepartmentAndPickingStatus(Department department, Integer pickingStatus,
			Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartmentAndPickingStatusAndAuditStatusGreaterThan(department,
				pickingStatus, GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT, pageable);
	}

	/**
	 * 通过流程和领料状态查询-分页
	 *
	 * @param processManage
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByProcessManageAndPickingStatus(ProcessManage processManage,
			Integer pickingStatus, Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByProcessManageAndPickingStatusAndAuditStatusGreaterThan(processManage,
				pickingStatus, GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT, pageable);
	}

	/**
	 * 通过部门和流程查询-分页
	 *
	 * @param processManage
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByDepartmentAndProcessManage(Department department, ProcessManage processManage,
			Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByDepartmentAndProcessManage(department, processManage, pageable);
	}

	/**
	 * 通过领料时间查询-分页
	 *
	 * @param pickingTime
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<PickingApplyHeader> findByPickingTimeBetween(Long pickingTime, Integer page, Integer size,
			String sortFieldName, Integer asc) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTimeInMillis(pickingTime);
		calendar1.set(Calendar.HOUR, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);

		calendar2.setTimeInMillis(calendar1.getTimeInMillis());
		calendar2.add(Calendar.DAY_OF_MONTH, 1);

		// 判断字段名是否存在
		try {
			PickingApplyHeader.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return pickingApplyHeaderRepository.findByPickingTimeBetween(calendar1.getTime(), calendar2.getTime(),
				pageable);
	}

	/**
	 * 通过编码查询剩余审批人
	 *
	 * @param code
	 * @return
	 */
	public List<User> getRestAuditorByCode(Long code, String curAuditorCode) {

		List<User> users = new ArrayList<User>();

		PickingApplyHeader pickingApplyHeader = pickingApplyHeaderRepository.findOne(code);
		Set<String> userCodes = new HashSet<String>();

		Process process = pickingApplyHeader.getProcess();
		ProcessManage processManage = pickingApplyHeader.getProcessManage();

		if (process.getCode() == 0 && processManage != null) {
			// 紧急
			if (processManage.getLeader2() != null) {
				userCodes.add(processManage.getLeader2().getCode());
			}

			if (processManage.getLeader3() != null) {
				userCodes.add(processManage.getLeader3().getCode());
			}

			if (processManage.getLeader4() != null) {
				userCodes.add(processManage.getLeader4().getCode());
			}

			if (processManage.getLeader5() != null) {
				userCodes.add(processManage.getLeader5().getCode());
			}
		} else {
			// 正常时无剩余审批人
			return users;
		}

		// 去重当前审核人
		if (userCodes.contains(curAuditorCode)) {
			userCodes.remove(curAuditorCode);
		}

		List<PickingAudit> pickingAudits = pickingAuditRepository.findByPickingApplyHeader(pickingApplyHeader);
		if (pickingAudits != null) {
			for (PickingAudit pickingAudit : pickingAudits) {
				if (pickingAudit.getAuditor() != null) {
					if (userCodes.contains(pickingAudit.getAuditor().getCode())) {
						userCodes.remove(pickingAudit.getAuditor().getCode());
					}
				}
			}
		}

		return userRepository.findAll(userCodes);
	}

	/**
	 * 通过出库状态查询
	 *
	 * @param pickingStatus
	 * @return
	 */
	public List<PickingApplyHeader> getByPickingStatus(Integer pickingStatus) {
		return pickingApplyHeaderRepository.findByAuditStatusGreaterThanAndPickingStatus(
				GlobalUtil.PickingApplyHeaderStatus.PRE_AUDIT, pickingStatus);
	}
}
