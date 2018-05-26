package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lanyage on 2018/3/18.
 */
@Service
public class EqRepairApplicationService {

    @Autowired
    private EqRepairApplicationRepository eqRepairApplicationRepository;
    @Autowired
    private FlagDao flagDao;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EvaluationDao evaluationDao;
    @Autowired
    private ProductLineRepository productLineRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EqArchiveDao eqArchiveDao;
    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有分页
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @return
     */
    public Page<EqRepairApplication> getAllInPages(int i, int i1, String id, int i2) {
        Pageable pageable = getPageable(i, i1, id, i2);
        return eqRepairApplicationRepository.findAll(pageable);
    }

    /**
     * 按照类型查询
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param flag
     * @return
     */
    public Page<EqRepairApplication> findByFlagInPages(int i, int i1, String id, int i2, Flag flag) {
        Pageable pageable = getPageable(i, i1, id, i2);
        return eqRepairApplicationRepository.findByFlag(flag, pageable);
    }

    /**
     * 按照申请日期查询
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param date
     * @return
     */
    public Page<EqRepairApplication> findByApplicationTimeInPages(int i, int i1, String id, int i2, Date date) {
        Pageable pageable = getPageable(i, i1, id, i2);
        return eqRepairApplicationRepository.findByApplicationTime(date, pageable);
    }

    /**
     * 按照类型和申请日期查询
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param flag
     * @param date
     * @return
     */
    public Page<EqRepairApplication> findByFlagAndApplicationTimeInPages(int i, int i1, String id, int i2, Flag flag, Date date) {
        Pageable pageable = getPageable(i, i1, id, i2);
        return eqRepairApplicationRepository.findByFlagAndApplicationTime(flag, date, pageable);
    }

    /**
     * 按照设备名称查询
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param equipment
     * @return
     */
    public Page<EqRepairApplication> findByEquipmentNameInPages(int i, int i1, String id, int i2, Equipment equipment) {
        Pageable pageable = getPageable(i, i1, id, i2);
        Equipment exist = equipmentRepository.findByName(equipment.getName());
        return eqRepairApplicationRepository.findByEquipment(exist, pageable);
    }

    /**
     * 按照申请人查找
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param applicationPerson
     * @return
     */
    public Page<EqRepairApplication> findByApplicationPerson(int i, int i1, String id, int i2, User applicationPerson) {
        Pageable pageable = getPageable(i, i1, id, i2);
        User exist = userRepository.findOne(applicationPerson.getCode());
        return eqRepairApplicationRepository.findByApplicationPerson(exist, pageable);
    }

    /**
     * 按照维修人查找
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param applicationPerson
     * @return
     */
    public Page<EqRepairApplication> findByRepairMan(int i, int i1, String id, int i2, User applicationPerson) {
        Pageable pageable = getPageable(i, i1, id, i2);
        User exist = userRepository.findOne(applicationPerson.getCode());
        return eqRepairApplicationRepository.findByRepairMan(exist, pageable);
    }

    /**
     * 按照评价人查找
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @param applicationPerson
     * @return
     */
    public Page<EqRepairApplication> findByEvaluator(int i, int i1, String id, int i2, User applicationPerson) {
        Pageable pageable = getPageable(i, i1, id, i2);
        User exist = userRepository.findOne(applicationPerson.getCode());
        return eqRepairApplicationRepository.findByEvaluator(exist, pageable);
    }

    /**
     * 封装分页重复代码
     *
     * @param i
     * @param i1
     * @param id
     * @param i2
     * @return
     */
    private Pageable getPageable(int i, int i1, String id, int i2) {
        try {
            EqRepairApplication.class.getDeclaredField(id);
        } catch (Exception e) {
            throw new RuntimeException(EnumException.SORT_FIELD.getMessage());
        }
        Sort sort = null;
        if (i2 == 0) {
            sort = new Sort(Sort.Direction.DESC, id);
        } else {
            sort = new Sort(Sort.Direction.ASC, id);
        }
        Pageable pageable = new PageRequest(i, i1, sort);
        return pageable;
    }

    /**
     * 删除一条记录
     *
     * @param one
     */
    public void deleteOne(EqRepairApplication one) {
        eqRepairApplicationRepository.delete(one);
    }

    /**
     * 批量删除记录
     *
     * @param ones
     */
    public void deleteInBatch(List<EqRepairApplication> ones) {
        eqRepairApplicationRepository.deleteInBatch(ones);
    }

    /**
     * 按code查询
     *
     * @param code
     */
    public EqRepairApplication findByCode(String code) {
        EqRepairApplication exist = eqRepairApplicationRepository.findOne(code);
        return exist;
    }

    /**
     * 申请维修
     *
     * @param webBean
     */
    public void apply(EqRepairApplication webBean) {
        EqRepairApplication application = new EqRepairApplication();

        String code = UUID.randomUUID().toString().replaceAll("-", "");//设置主键
        application.setCode(code);
        if (webBean.getDepartment() == null ||  webBean.getDepartment().getCode() == null || webBean.getDepartment().getCode().equals("") || webBean.getDepartment().getCode().trim().equals(""))

            throw new MesException(EnumException.DEPARTMENT_NOT_EXISTS);
        Department department = departmentRepository.findOne(webBean.getDepartment().getCode());
        if (department == null) {
            throw new MesException(EnumException.DEPARTMENT_NOT_EXISTS);
        }
        application.setDepartment(department);//设置部门


        if (webBean.getProductLine() == null || webBean.getProductLine().getCode() == null || webBean.getProductLine().getCode().equals("") || webBean.getProductLine().getCode().trim().equals(""))
            throw new MesException(EnumException.PRODUCTLINE_NOT_EXISTS);
        ProductLine productLine = productLineRepository.findOne(webBean.getProductLine().getCode());
        if (productLine == null) {
            throw new MesException(EnumException.PRODUCTLINE_NOT_EXISTS);
        }
        application.setProductLine(productLine);//设置生产线

        application.setApplicationDescription(webBean.getApplicationDescription());//设置故障描述

        application.setApplicationTime(new Date());//设置申请时间

        Flag flag = flagDao.findByCode(0);
        application.setFlag(flag);//设置类型为0


        if (webBean.getEqArchive() == null || webBean.getEqArchive().getCode() == null || webBean.getEqArchive().getCode().equals("") || webBean.getEqArchive().getCode().trim().equals(""))
            throw new MesException(EnumException.ARCHIVE_NOT_EXISTS);
        EqArchive archive = eqArchiveDao.findOne(webBean.getEqArchive().getCode());
        if (archive == null) {
            throw new MesException(EnumException.ARCHIVE_NOT_EXISTS);
        }
        application.setEqArchive(archive);//设置设备archive

        if (webBean.getApplicationPerson() == null || webBean.getApplicationPerson().getCode() == null || webBean.getApplicationPerson().getCode().equals("") || webBean.getApplicationPerson().getCode().trim().equals(""))
            throw new MesException(EnumException.APPLICATIONPERSON_NOT_EXISTS);
        User applicationPerson = userRepository.findOne(webBean.getApplicationPerson().getCode());
        if (applicationPerson == null) {
            throw new MesException(EnumException.APPLICATIONPERSON_NOT_EXISTS);
        }
        application.setApplicationPerson(applicationPerson);//设置申请人

        application.setApplicationPersonContact(applicationPerson.getContact());//设置申请人电话

        eqRepairApplicationRepository.save(application);
    }

    /**
     * 接受申请
     *
     * @param webBean
     */
    public void accept(EqRepairApplication webBean) {
        EqRepairApplication exist = findByCode(webBean.getCode());
        Flag newFlag = flagDao.findByCode(1);
        User repairMan = userRepository.findOne(webBean.getRepairMan().getCode());
        exist.setRepairMan(repairMan);
        exist.setFlag(newFlag);
        exist.setOrderTime(new Date());
        eqRepairApplicationRepository.save(exist);
    }

    /**
     * 维修完成
     *
     * @param webBean
     */
    public void finish(EqRepairApplication webBean) {
        EqRepairApplication exist = findByCode(webBean.getCode());
        exist.setRepairmanDescription(webBean.getRepairmanDescription());
        Flag newFlag = flagDao.findByCode(2);
        exist.setFlag(newFlag);
        exist.setFinishTime(new Date());
        eqRepairApplicationRepository.save(exist);
    }

    /**
     * 评价
     *
     * @param webBean
     */
    public void evaluate(EqRepairApplication webBean) {
        EqRepairApplication exist = findByCode(webBean.getCode());
        Flag newFlag = flagDao.findByCode(3);
        User evaluator = userRepository.findOne(webBean.getEvaluator().getCode());
        exist.setEvaluator(evaluator);
        exist.setFlag(newFlag);
        Evaluation evaluation = evaluationDao.findOne(webBean.getEvaluation().getCode());
        exist.setEvaluation(evaluation);
        eqRepairApplicationRepository.save(exist);
    }


}
