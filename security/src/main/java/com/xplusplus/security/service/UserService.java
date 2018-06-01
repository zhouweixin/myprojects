package com.xplusplus.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.repository.AttendanceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.DepartmentRepository;
import com.xplusplus.security.repository.JobNatureRepository;
import com.xplusplus.security.repository.UserRepository;
import com.xplusplus.security.utils.GlobalUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:35 2018/5/7
 * @Modified By:
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobNatureRepository jobNatureRepository;

    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    public User save(User user) {

        // 验证是否存在
        if (user == null || (user.getId() != null && userRepository.findOne(user.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        // 验证部门
        Department department = null;
        if (user.getDepartment() == null
                || (department = departmentRepository.findOne(user.getDepartment().getId())) == null) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DEPARTMENT_NOT_EXIST);
        }

        // 生成id: 部门简称+5位数字
        String maxId = userRepository.findIdMaxByIdLike(department.getShortName());
        int id = 0;
        try {
            id = Integer.parseInt(maxId.replace(department.getShortName(), ""));
        } catch (Exception e) {
        }
        id++;
        user.setId(department.getShortName() + String.format("%05d", id));

        // 验证手机
        if (user.getContact() == null || Pattern.matches("^1[0-9]{10}$", user.getContact()) == false) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_PHONE_NOT_LAWER);
        }

        // 设置默认密码
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        // 实习周期
        user.setPeriod(GlobalUtil.computePeriod(user.getEmployDate(), user.getPracticeEndDate()));

        return userRepository.save(user);
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    public User update(User user) {

        // 验证是否存在
        if (user == null || user.getId() == null || userRepository.findOne(user.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        // 验证部门
        if (user.getDepartment() == null || departmentRepository.findOne(user.getDepartment().getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_DEPARTMENT_NOT_EXIST);
        }

        // 验证手机
        if (user.getContact() == null || Pattern.matches("^1[0-9]{10}$", user.getContact()) == false) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_PHONE_NOT_LAWER);
        }

        // 实习周期
        user.setPeriod(GlobalUtil.computePeriod(user.getEmployDate(), user.getPracticeEndDate()));

        return userRepository.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(String id) {

        // 验证是否存在
        if (userRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        userRepository.delete(id);
    }

    /**
     * 批量删除
     *
     * @param users
     */
    public void deleteInBatch(Collection<User> users) {
        userRepository.deleteInBatch(users);
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<User> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            User.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<User> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            User.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findByNameLike("%" + name + "%", pageable);
    }

    /**
     * 通过工作性质查询-分页
     *
     * @param jobNature
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<User> findByJobNatureByPage(JobNature jobNature, Integer page, Integer size, String sortFieldName,
                                            Integer asc) {

        // 判断排序字段名是否存在
        try {
            User.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findByJobNature(jobNature, pageable);
    }

    /**
     * 通过工作性质查询-分页
     *
     * @param department
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<User> findByDepartmentAndNameLikeByPage(Department department, String name, Integer page, Integer size,
                                                        String sortFieldName, Integer asc) {

        if (department == null || department.getId() == null) {
            return findByNameLikeByPage(name, page, size, sortFieldName, asc);
        }

        // 判断排序字段名是否存在
        try {
            User.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findByDepartmentAndNameLike(department, "%" + name + "%", pageable);
    }

    /**
     * 通过id更新工作性质
     *
     * @param jobNatureId
     * @param id
     */
    @Transactional
    public void updateJobNatureById(Integer jobNatureId, String id) {
        JobNature jobNature = jobNatureRepository.findOne(jobNatureId);

        if (jobNature == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_JOB_NATURE_NOT_EXIST);
        }

        userRepository.updateJobNatureById(jobNature, id);
    }

    /**
     * 更新密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Transactional
    public void updatePasswordById(String id, String oldPassword, String newPassword) {

        User user = userRepository.findOne(id);
        if (user == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_USER_NOT_EXIST);
        }

        if (oldPassword == null || newPassword == null || "".equals(oldPassword) || "".equals(newPassword)) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_PASSWORD_NULL);
        }

        // md5加密
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());

        if (!oldPassword.equals(user.getPassword())) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_PASSWORD_NOT_EQUALS);
        }

        userRepository.updatePasswordById(newPassword, id);
    }

    /**
     * 通过用户名模糊查询
     *
     * @param name
     * @return
     */
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike("%" + name + "%");
    }

    /**
     * 通过部门查询
     *
     * @param department
     * @return
     */
    public List<User> findByDepartment(Department department) {
        return userRepository.findByDepartment(department);
    }

    /**
     * 通过工作性质查询
     *
     * @param jobNature
     * @return
     */
    public List<User> findByJobNature(JobNature jobNature) {
        return userRepository.findByJobNature(jobNature);
    }

    /**
     * 通过部门,工作性质,名称模糊查询
     *
     * @param departmentId
     * @param jobNatureId
     * @param name
     * @return
     */
    public List<User> findByDepartmentAndJobNatureAndNameLike(Integer departmentId, Integer jobNatureId, String name) {
        Department department = new Department();
        department.setId(departmentId);
        JobNature jobNature = new JobNature();
        jobNature.setId(jobNatureId);

        if (departmentId == -1 && jobNatureId == -1) {
            return userRepository.findByNameLike("%" + name + "%");
        }

        if (departmentId != -1 && jobNatureId == -1) {
            return userRepository.findByDepartmentAndNameLike(department, "%" + name + "%");
        }

        if (departmentId == -1 && jobNatureId != -1) {
            return userRepository.findByJobNatureAndNameLike(jobNature, "%" + name + "%");
        }

        return userRepository.findByDepartmentAndJobNatureAndNameLike(department, jobNature, "%" + name + "%");
    }

    /**
     * 分配用户到考勤组
     *
     * @param attendanceGroupId
     * @param userIds
     */
    @Transactional
    public void assignUsersToAttendanceGroup(Integer attendanceGroupId, Set<String> userIds) {
        // 判断考勤组是否存在
        AttendanceGroup attendanceGroup = attendanceGroupRepository.findOne(attendanceGroupId);
        if (attendanceGroup == null) {
            throw new SecurityExceptions(EnumExceptions.ASSIGN_FAILED_ATTENDANCE_GROUP_NOT_EXIST);
        }

        // 清空考勤组的员工
        userRepository.nullAttendanceGroupByAttendanceGroup(attendanceGroup);

        // 分别分配每个用户
        for (String userId : userIds) {
            if (userRepository.findOne(userId) == null) {
                EnumExceptions.ASSIGN_FAILED_USER_NOT_EXIST.setMessage("分配失败, 员工" + userId + "不存在");
                throw new SecurityExceptions(EnumExceptions.ASSIGN_FAILED_USER_NOT_EXIST);
            }

            userRepository.updateAttendanceGroupById(attendanceGroup, userId);
        }
    }

    /**
     * 通过考勤组查询
     *
     * @param attendanceGroup
     * @return
     */
    public List<User> findByAttendanceGroup(AttendanceGroup attendanceGroup){
        return userRepository.findByAttendanceGroup(attendanceGroup);
    }
}
