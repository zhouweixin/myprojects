package com.hnu.mes.repository;

import com.hnu.mes.domain.EnergyRealData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:08 2018/4/15
 * @Modified By:
 */
@Repository
public interface EnergyRealDataRepository extends JpaRepository<EnergyRealData, Integer> {
    @Query(value = "select * from realdata_h_dec where ino=?1 and ((idate=?2 and ihour>?3) or (idate=?4 and ihour<=?5) or (idate > ?2 and idate < ?4)) ORDER BY  IDate ASC,ihour ASC", nativeQuery = true)
    public List<EnergyRealData> findEnergyRealDatasByDateTime(Integer ino, String startDate, int startHour, String endDate, int endHour);
}
