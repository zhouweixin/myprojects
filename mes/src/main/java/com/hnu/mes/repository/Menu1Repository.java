package com.hnu.mes.repository;

import com.hnu.mes.domain.Menu1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Menu1Repository extends JpaRepository<Menu1, Integer> {
    /**
     * 通过编码更新名称和图标
     * @param name
     * @param path
     * @param code
     */
    @Modifying
    @Query(value = "update Menu1 m set m.name=?1, m.path=?2 where m.code=?3")
    public void updateNameAndPathByCode(String name, String path, Integer code);

    /**
     * 查询最大code
     *
     * @return
     */
    @Query("select max(code) from Menu1")
    public Integer findMaxCode();

    /**
     * 查询最大rank
     *
     * @return
     */
    @Query("select max(rank) from Menu1")
    public Integer findMaxRank();
}

