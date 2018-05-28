package com.xplusplus.security.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:47 2018/5/7
 * @Modified By:
 */
public class GlobalUtil {

	/**
	 * 计算周期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Period computePeriod(Date startDate, Date endDate) {
		// 计算周期
		if (startDate != null && endDate != null) {
			// Date -> LocalDate
			Instant startInstant = startDate.toInstant();
			Instant endInstant = endDate.toInstant();
			ZoneId zone = ZoneId.systemDefault();
			LocalDate start = LocalDateTime.ofInstant(startInstant, zone).toLocalDate();
			LocalDate end = LocalDateTime.ofInstant(endInstant, zone).toLocalDate();

			// 计算时间差
			return Period.between(start, end);
		}
		
		return null;
	}

}
