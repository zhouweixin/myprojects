package com.xplusplus.security.utils;

import java.time.*;
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


    /**
     * 计算工作时长
     * @param startTime
     * @param endTime
     * @param startBreakTime
     * @param endBreakTime
     * @return
     */
    public static Duration computeWorkPeriod(Date startTime, Date endTime,
                                             Date startBreakTime, Date endBreakTime) {
        // 计算休息时长-基于时间（时、分、秒等）
        if (startTime != null && endTime != null
                && startBreakTime != null && endBreakTime != null) {
            Instant ins1 = startTime.toInstant();
            Instant ins2 = endTime.toInstant();
            Instant ins3 = startBreakTime.toInstant();
            Instant ins4 = endBreakTime.toInstant();

            // 计算时间差
            return Duration.ofSeconds(Duration.between(ins1, ins2).getSeconds()-Duration.between(ins3, ins4).getSeconds());
        }

        return null;
    }

    /**
     * 计算休息时长
     * @param startTime
     * @param endTime
     * @return
     */
	public static Duration computeBreakPeriod(Date startTime, Date endTime) {
		// 计算工作时长-基于时间（时、分、秒等）
		if (startTime != null && endTime != null) {
			Instant startInstant = startTime.toInstant();
			Instant endInstant = endTime.toInstant();

			// 计算时间差
			return Duration.between(startInstant, endInstant);
		}

		return null;
	}

}
