package com.hnu.mes.service;

import com.hnu.mes.domain.Menu2;
import com.hnu.mes.domain.Model;
import com.hnu.mes.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhouweixin on 2018/3/18.
 */
@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    /**
     * 保存
     *
     * @param model
     * @return
     */
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public Model findOne(Integer code) {
        return modelRepository.findOne(code);
    }

    /**
     * 通过编码更新名称和路径
     *
     * @param name
     * @param code
     */
    @Transactional
    public void updateNameAndPathByCode(String name, Integer code) {
        modelRepository.updateNameByCode(name, code);
    }

    /**
     * 通过编码删除
     *
     * @param code
     */
    public void delete(Integer code) {
        modelRepository.delete(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    /**
     * 通过menu2查询model
     *
     * @param menu2
     * @return
     */
    public Model findByMenu2(Menu2 menu2) {
        return modelRepository.findByMenu2(menu2);
    }

    /**
     * 查询最大编码
     *
     * @return
     */
    public Integer findMaxCode() {
        return modelRepository.findMaxCode();
    }

    /**
     * 查询最大排序
     *
     * @return
     */
    public Integer findMaxRank() {
        return modelRepository.findMaxRank();
    }
}
