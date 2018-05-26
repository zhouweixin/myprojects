package com.hnu.mes.service;

import com.hnu.mes.domain.MaterialsTotal;
import com.hnu.mes.repository.MaterialsTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:09 2018/5/9
 * @Modified By:
 */
@Service
public class MaterialsTotalService {
    @Autowired
    private MaterialsTotalRepository materialsTotalRepository;

    /**
     * 保存
     *
     * @param materialsTotal
     * @return
     */
    public MaterialsTotal save(MaterialsTotal materialsTotal){
        MaterialsTotal materialsTotal1 = materialsTotalRepository.findFirstByRawType(materialsTotal.getRawType());
        if(materialsTotal1 != null){
            materialsTotal.setWeight(materialsTotal1.getWeight() + materialsTotal.getWeight());
            materialsTotal.setCode(materialsTotal1.getCode());
        }

        return materialsTotalRepository.save(materialsTotal);
    }
}
