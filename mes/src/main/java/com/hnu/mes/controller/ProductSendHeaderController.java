package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.domain.ProductSendHeader;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProductSendHeaderService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:17 2018/5/17
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/productSendHeader")
public class ProductSendHeaderController {
	@Autowired
	private ProductSendHeaderService productSendHeaderService;

	@Autowired
	private UserService userService;

	/**
	 * 新增
	 *
	 * @param productSendHeader
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<ProductSendHeader> add(@RequestBody ProductSendHeader productSendHeader,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().toString());
		}

		if (productSendHeader.getApplicant() == null
				|| userService.findOne(productSendHeader.getApplicant().getCode()) == null) {
			return ResultUtil.error(new MesException(EnumException.FAILED_APPLICANT_NOT_EXIST));
		}

		return ResultUtil.success(productSendHeaderService.save(productSendHeader));
	}

	/**
	 * 更新
	 *
	 * @param productSendHeader
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<ProductSendHeader> update(@RequestBody ProductSendHeader productSendHeader,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().toString());
		}

		if (productSendHeader == null || productSendHeader.getCode() == null
				|| productSendHeaderService.findOne(productSendHeader.getCode()) == null) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_PRODUCT_GODOWN_HEADER_NOT_EXIST));
		}

		ProductSendHeader one = productSendHeaderService.findOne(productSendHeader.getCode());
		if (one.getAuditStatus() != GlobalUtil.ProductSendStatus.SAVE) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_SUBMIT_NOT_ALLOW));
		}

		if (productSendHeader.getApplicant() == null
				|| userService.findOne(productSendHeader.getApplicant().getCode()) == null) {
			return ResultUtil.error(new MesException(EnumException.FAILED_APPLICANT_NOT_EXIST));
		}

		return ResultUtil.success(productSendHeaderService.update(productSendHeader));
	}

	/**
	 * 删除
	 *
	 * @param code
	 */
	@RequestMapping(value = "/deleteByCode")
	public Result<Object> delete(@RequestParam(value = "code") Long code) {

		ProductSendHeader one = productSendHeaderService.findOne(code);
		if (one == null) {
			return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
		}

		if (one.getAuditStatus() != GlobalUtil.ProductSendStatus.SAVE) {
			return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_SUBMIT_NOT_ALLOW));
		}

		productSendHeaderService.delete(code);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 *
	 * @param productSendHeaders
	 * @return
	 */
	@RequestMapping(value = "/deleteByCodeBatch")
	public Result<Object> deleteByBatchCode(@RequestBody Set<ProductSendHeader> productSendHeaders) {
		productSendHeaderService.deleteInBatch(productSendHeaders);
		return ResultUtil.success();
	}

	/**
	 * 通过编码查询
	 *
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getByCode")
	public Result<ProductSendHeader> getByCode(Long code) {
		return ResultUtil.success(productSendHeaderService.findOne(code));
	}

	/**
	 * 查找所有
	 *
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<ProductSendHeader>> findAll() {
		return ResultUtil.success(productSendHeaderService.findAll());
	}

	/**
	 * 查询所有-分页
	 *
	 * @param page
	 *            当前页,从0开始,默认是0
	 * @param size
	 *            每页的记录数,默认是10
	 * @param sort
	 *            排序的字段名,默认是code
	 * @param asc
	 *            排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@RequestMapping(value = "/getAllByPage")
	public Result<Page<ProductSendHeader>> findAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(productSendHeaderService.findAllByPage(page, size, sort, asc));
	}

	/**
	 * 通过审核状态查询-分页
	 *
	 * @param auditStatus
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByAuditStatusByPage")
	public Result<Page<ProductSendHeader>> findByAuditStatusByPage(Integer auditStatus,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(productSendHeaderService.findByAuditStatusByPage(auditStatus, page, size, sort, asc));
	}

	/**
	 * 通过创建日期查询-分页
	 *
	 * @param createDate
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByCreateDateByPage")
	public Result<Page<ProductSendHeader>> findByCreateDateByPage(
			long createDate,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		System.out.println(createDate);
		return ResultUtil.success(productSendHeaderService.findByCreateDateByPage(createDate, page, size, sort, asc));
	}

	/**
	 * 通过原料类型查询-分页
	 *
	 * @param rawTypeCode
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByRawTypeByPage")
	public Result<Page<ProductSendHeader>> findByRawTypeByPage(Long rawTypeCode,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		RawType rawType = new RawType();
		rawType.setCode(rawTypeCode);
		return ResultUtil.success(productSendHeaderService.findByRawTypeByPage(rawType, page, size, sort, asc));
	}

	/**
	 * 通过审核状态和创建时间查询-分页
	 *
	 * @param auditStatus
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByAuditStatusAndCreateDateByPage")
	public Result<Page<ProductSendHeader>> findByAuditStatusAndCreateDateByPage(Integer auditStatus, Long createDate,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(productSendHeaderService.findByAuditStatusAndCreateDateByPage(auditStatus, createDate,
				page, size, sort, asc));
	}

	/**
	 * 通过审核状态和原料类型查询-分页
	 *
	 * @param auditStatus
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByAuditStatusAndRawTypeByPage")
	public Result<Page<ProductSendHeader>> findByAuditStatusAndRawTypeByPage(Integer auditStatus, Long rawTypeCode,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		RawType rawType = new RawType();
		rawType.setCode(rawTypeCode);
		return ResultUtil.success(productSendHeaderService.findByAuditStatusAndRawTypeByPage(auditStatus, rawType, page,
				size, sort, asc));
	}

	/**
	 * 通过创建日期和原料类型查询-分页
	 *
	 * @param createDate
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByCreateDateAndRawTypeByPage")
	public Result<Page<ProductSendHeader>> findByCreateDateAndRawTypeByPage(Long createDate, Long rawTypeCode,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		RawType rawType = new RawType();
		rawType.setCode(rawTypeCode);
		return ResultUtil.success(
				productSendHeaderService.findByCreateDateAndRawTypeByPage(createDate, rawType, page, size, sort, asc));
	}

	/**
	 * 通过审核状态，创建日期，原料类型查询-分页
	 *
	 * @param createDate
	 * @param page
	 * @param size
	 * @param sort
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByAuditStatusAndRawTypeAndCreateDateByPage")
	public Result<Page<ProductSendHeader>> findByAuditStatusAndRawTypeAndCreateDateByPage(
			@RequestParam(value = "auditStatus", defaultValue = "-1") Integer auditStatus,
			@RequestParam(value = "rawTypeCode", defaultValue = "-1") Long rawTypeCode,
			@RequestParam(value = "createDate", defaultValue = "-1") Long createDate,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		if (auditStatus == -1 && rawTypeCode == -1 && createDate != -1) {
			return ResultUtil
					.success(productSendHeaderService.findByCreateDateByPage(createDate, page, size, sort, asc));
		} else if (auditStatus == -1 && rawTypeCode != -1 && createDate == -1) {
			RawType rawType = new RawType();
			rawType.setCode(rawTypeCode);
			return ResultUtil.success(productSendHeaderService.findByRawTypeByPage(rawType, page, size, sort, asc));
		} else if (auditStatus != -1 && rawTypeCode == -1 && createDate == -1) {
			return ResultUtil
					.success(productSendHeaderService.findByAuditStatusByPage(auditStatus, page, size, sort, asc));
		} else if (auditStatus == -1 && rawTypeCode != -1 && createDate != -1) {
			RawType rawType = new RawType();
			rawType.setCode(rawTypeCode);
			return ResultUtil.success(productSendHeaderService.findByCreateDateAndRawTypeByPage(createDate, rawType,
					page, size, sort, asc));
		} else if (auditStatus != -1 && rawTypeCode != -1 && createDate == -1) {
			RawType rawType = new RawType();
			rawType.setCode(rawTypeCode);
			return ResultUtil.success(productSendHeaderService.findByAuditStatusAndRawTypeByPage(auditStatus, rawType,
					page, size, sort, asc));
		} else if (auditStatus != -1 && rawTypeCode == -1 && createDate != -1) {
			return ResultUtil.success(productSendHeaderService.findByAuditStatusAndCreateDateByPage(auditStatus,
					createDate, page, size, sort, asc));
		} else if (auditStatus != -1 && rawTypeCode != -1 && createDate != -1) {
			RawType rawType = new RawType();
			rawType.setCode(rawTypeCode);
			return ResultUtil.success(productSendHeaderService.findByAuditStatusAndRawTypeAndCreateDateByPage(
					auditStatus, createDate, rawType, page, size, sort, asc));
		}

		return ResultUtil.success(productSendHeaderService.findAllByPage(page, size, sort, asc));
	}

	/**
	 * 通过审核状态和编号模糊查询
	 *
	 * @param auditStatus
	 * @param number
	 * @return
	 */
	@RequestMapping(value = "/getByAuditStatusAndNumberLike")
	public Result<List<ProductSendHeader>> findByAuditStatusAndNumberLike(Integer auditStatus, String number) {
		return ResultUtil.success(productSendHeaderService.findByAuditStatusAndNumberLike(auditStatus, number));
	}

	/**
	 * 通过编码更新审核状态
	 *
	 * @param auditStatus
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateAuditStatusByCode")
	public Result<Object> updateAuditStatusByCode(Integer auditStatus, String note, Long code, String auditCode) {

		ProductSendHeader productSendHeader = productSendHeaderService.findOne(code);
		if (productSendHeader == null) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_PRODUCT_GODOWN_HEADER_NOT_EXIST));
		}

		if (productSendHeader.getAuditStatus() == GlobalUtil.ProductSendStatus.APPROVE
				|| productSendHeader.getAuditStatus() == GlobalUtil.ProductSendStatus.NOT_APPROVE) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_AUDIT));
		}

		if (productSendHeader.getAuditStatus() == GlobalUtil.ProductSendStatus.AUDITTING) {
			productSendHeaderService.updateAuditStatusByCode(auditStatus, note, code, auditCode);
			return ResultUtil.success();
		}

		return ResultUtil.error("审核失败");
	}

	/**
	 * 通过出库状态和编号模糊查询
	 *
	 * @param outStatus
	 * @param number
	 * @return
	 */
	@RequestMapping(value = "/getByOutStatusAndNumberLike")
	public Result<List<ProductSendHeader>> getByOutStatusAndNumberLike(Integer outStatus, String number) {
		return ResultUtil.success(productSendHeaderService.findByOutStatusAndNumberLike(outStatus, number));
	}

	/**
	 * 产品出库
	 *
	 * @param outStatus
	 * @param senderCode
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateOutStatusByCode")
	public Result<Object> updateOutStatusAndApplicantAndApplyTimeByCode(Integer outStatus, String senderCode,
			Long code) {

		if (productSendHeaderService.findOne(code) == null) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_PRODUCT_SEND_HEADER_NOT_EXIST));
		}

		User sender = userService.findOne(senderCode);
		if (sender == null) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_SENDER_NOT_EXIST));
		}

		productSendHeaderService.updateOutStatusAndApplicantAndApplyTimeByCode(outStatus, sender, code);
		return ResultUtil.success();
	}
}
