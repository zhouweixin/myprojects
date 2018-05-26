package com.hnu.mes.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouweixin on 2018/3/18.
 */
public class FileUploadUtil {

    /**
     * 获得所有的文件类型
     *
     * @return
     */
    public static Map<Integer, String> getAllFileType() {
        Map<Integer, String> map = new HashMap<Integer, String>();

        for (FileType fileType : FileType.values()) {
            map.put(fileType.getCode(), fileType.getName());
        }

        return map;
    }

    public static FileType[] getFileTypes() {
        return FileType.values();
    }

    /**
     * 上传文件
     *
     * @param file     文件字节数组
     * @param filePath 保存路径
     * @param fileName 保存文件名称
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String filePath, String fileName) throws FileNotFoundException, IOException {

        // 保存路径
        if (filePath == null || filePath.equals("")) {
            filePath = "upload";
        }

        // 创建目录
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存文件名
        if (fileName == null || fileName.equals("")) {
            fileName = file.getOriginalFilename();
        }

        String path = filePath + "/" + fileName;

        // 定义输出流
        FileOutputStream out = new FileOutputStream(path);

        // 开始上传
        final byte[] bytes = file.getBytes();
        out.write(bytes);
        out.flush();
        out.close();

        return path;
    }

    /**
     * 文件类型
     */
    public enum FileType {
        PRODUCT(1, "产品"),
        RAW_PRESOMA(2, "原料-前驱体"),
        RAW_LITHIUM(3, "原料-碳酸锂"),
        PROCESS_PREMIX_11(4, "制程-预混-11"),
        PROCESS_PREMIX_15(5, "制程-预混-15"),
        PROCESS_SIZE(6, "制程-粉碎-粒度"),
        PROCESS_LITHIUM(7, "制程-粉碎-总锂"),
        PROCESS_BUCKLE(8, "制程-粉碎-扣电"),;
        private int code;
        private String name;

        private FileType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 根据value返回枚举类型,主要在switch中使用
        public static FileType getByValue(int value) {
            for (FileType fileType : values()) {
                if (fileType.getCode() == value) {
                    return fileType;
                }
            }
            return null;
        }
    }
}



