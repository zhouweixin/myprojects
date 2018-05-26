package com.hnu.mes.repository;

import com.hnu.mes.domain.Menu2;
import com.hnu.mes.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhouweixin on 2018/3/18.
 */
public interface ModelRepository extends JpaRepository<Model, Integer> {
    /**
     * 通过编码更新名称
     * @param name
     * @param code
     */
    @Modifying
    @Query(value = "update Model m set m.name=?1 where m.code=?2")
    public void updateNameByCode(String name, Integer code);

    /**
     * 通过menu2查询model
     * @param menu2
     * @return
     */
    public Model findByMenu2(Menu2 menu2);

    /**
     * 查询最大code
     *
     * @return
     */
    @Query("select max(code) from Model")
    public Integer findMaxCode();

    /**
     * 查询最大rank
     *
     * @return
     */
    @Query("select max(rank) from Model")
    public Integer findMaxRank();
}
