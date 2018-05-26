package com.hnu.mes.utils;

import com.hnu.mes.aspect.HttpAspect;
import com.hnu.mes.domain.Judge;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * Created by zhouweixin on 2018/3/19.
 */
public class MyExcelUtil {

    /**
     * 获取日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    // MMdd
    private static Pattern MMdd = Pattern.compile("\\d{2}\\d{2}");
    // MM_dd
    private static Pattern MM_dd = Pattern.compile("\\d{2}[-]\\d{2}");
    // MM/dd
    private static Pattern MM_1dd = Pattern.compile("\\d{2}[/]\\d{2}");
    // MM.dd
    private static Pattern MM_2dd = Pattern.compile("\\d{2}[.]\\d{2}");
    // yyyyMM
    private static Pattern yyyyMM = Pattern.compile("\\d{4}\\d{2}");
    // yyyy-MM
    private static Pattern yyyy_MM = Pattern.compile("\\d{4}[-]\\d{2}");
    // yyyy/MM
    private static Pattern yyyy_1MM = Pattern.compile("\\d{4}[/]\\d{2}");
    // yyyy.MM
    private static Pattern yyyy_2MM = Pattern.compile("\\d{4}[.]\\d{2}");
    // yyyyMMdd
    private static Pattern yyyyMMdd = Pattern.compile("\\d{4}\\d{2}\\d{2}");
    // yyyy-MM-dd
    private static Pattern yyyy_MM_dd = Pattern.compile("\\d{4}[-]\\d{2}[-]\\d{2}");
    // yyyy/MM/dd
    private static Pattern yyyy_1MM_1dd = Pattern.compile("\\d{4}[/]\\d{2}[/]\\d{2}");
    // yyyy.MM.dd
    private static Pattern yyyy_2MM_2dd = Pattern.compile("\\d{4}[.]\\d{2}[.]\\d{2}");

    /**
     * 从cell里解析Double
     *
     * @param cell
     * @return
     */
    public static Double cell2Double(XSSFCell cell) {
        if (cell == null) {
            return null;
        }

        String str = cell.getRawValue();
        if (str != null) {
            try {
                return Double.parseDouble(str);
            } catch (Exception e) {
                return new Double(0);
            }
        }

        return new Double(0);
    }

    /**
     * 从cell里解析判定
     *
     * @param cell
     * @return
     */
    public static Judge cell2Judge(XSSFCell cell, List<Judge> judges) {
        if (cell == null) {
            return null;
        }

        String result = "";

        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.STRING) {
            String str = cell.getStringCellValue();
            if (str.trim().equals("合格")) {
                result  =  "合格";
            } else if (str.trim().equals("不合格")) {
                result = "不合格";

            } else if (str.trim().equals("让步接收")) {
                result = "让步接收";
            }
        }

        // 判定类型
        for(int i=0; i<judges.size(); i++){
            Judge judge = judges.get(i);
            if(judge.getName().equals(result)){
                return judge;
            }
        }

        return null;
    }

    /**
     * 从cell里解析整数
     *
     * @param cell
     * @return
     */
    public static Integer cell2Integer(XSSFCell cell) {
        if (cell == null) {
            return null;
        }

        String num = cell.getRawValue();
        if (num != null) {
            try {
                if (num.length() > 0) {
                    return (int) Double.parseDouble(num);
                }
            } catch (Exception e) {
                return 0;
            }
        }

        return 0;
    }

    /**
     * 从cell里解析日期
     *
     * @param cell
     * @return
     */
    public static String cell2String(XSSFCell cell) {
        if (cell == null) {
            return null;
        }

        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.STRING) {
            String str = cell.getStringCellValue();
            if (str != null && !str.equals("")) {
                return str;
            }
        }

        return "";
    }

    /**
     * 从cell里解析日期
     *
     * @param cell
     * @return
     */

    public static Date cell2Date(XSSFCell cell) {

        if (cell == null) {
            return null;
        }

        // 获得数据类型
        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.NUMERIC) {// 数字型
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                if (date != null) {
                    return date;
                }
            } else {
                return new Date((long) cell.getNumericCellValue());
            }
        } else if (cellType == CellType.STRING) {// 字符串型
            String str = cell.getStringCellValue();
            return MyExcelUtil.str2Date(str);
        }

        return null;
    }

    /**
     * 字符串转成日期
     *
     * @param str
     * @return
     */
    public static Date str2Date(String str) {

        if (str == null || str == "") {
            return null;
        }

        Matcher matcherMMdd = MMdd.matcher(str);
        Matcher matcherMM_dd = MM_dd.matcher(str);
        Matcher matcherMM_1dd = MM_1dd.matcher(str);
        Matcher matcherMM_2dd = MM_2dd.matcher(str);
        Matcher matcheryyyyMM = yyyyMM.matcher(str);
        Matcher matcheryyyy_MM = yyyy_MM.matcher(str);
        Matcher matcheryyyy_1MM = yyyy_1MM.matcher(str);
        Matcher matcheryyyy_2MM = yyyy_2MM.matcher(str);
        Matcher matcheryyyyMMdd = yyyyMMdd.matcher(str);
        Matcher matcheryyyy_MM_dd = yyyy_MM_dd.matcher(str);
        Matcher matcheryyyy_1MM_1dd = yyyy_1MM_1dd.matcher(str);
        Matcher matcheryyyy_2MM_2dd = yyyy_2MM_2dd.matcher(str);

        Date date = null;
        if (matcherMMdd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcherMM_dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            try {
                date = (Date) sdf.parse(str);
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcherMM_1dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            try {
                date = (Date) sdf.parse(str);
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcherMM_2dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyyMM.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_MM.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_1MM.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_2MM.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyyMMdd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_MM_dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_1MM_1dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        } else if (matcheryyyy_2MM_2dd.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            try {
                date = new Date(sdf.parse(str).getTime());
            } catch (ParseException e) {
                logger.error("【系统异常】 {}", e.getMessage());
            }
        }

        return date;
    }
}

