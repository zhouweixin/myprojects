package com.hnu.mes.repository;

import com.hnu.mes.domain.Menu1;
import com.hnu.mes.domain.Menu2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Menu2Repository extends JpaRepository<Menu2, Integer> {
    /**
     * 通过编码更新名称
     * @param name
     * @param code
     */
    @Modifying
    @Query(value = "update Menu2 m set m.name=?1 where m.code=?2")
    public void updateNameByCode(String name, Integer code);
    /**
     * 通过menu1查询menu2
     * @param menu1
     * @return
     */
    public Menu2 findByMenu1(Menu1 menu1);

    /**
     * 查询最大code
     *
     * @return
     */
    @Query("select max(code) from Menu2")
    public Integer findMaxCode();

    /**
     * 查询最大rank
     *
     * @return
     */
    @Query("select max(rank) from Menu2")
    public Integer findMaxRank();
}

