package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessSizeRepository;
import com.hnu.mes.service.*;
import com.hnu.mes.utils.FileUploadUtil;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouweixin on 2018/3/18.
 */
@RestController
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RawPresomaService rawPresomaService;

    @Autowired
    private RawLithiumService rawLithiumService;

    @Autowired
    private ProcessPremixService processPremixService;

    @Autowired
    private ProcessSizeService processSizeService;

    @Autowired
    private ProcessLithiumService processLithiumService;

    @Autowired
    private ProcessBuckleService processBuckleService;

    /**
     * 查询所有要上传的文件类型
     * @return
     */
    @GetMapping(value = "/getAllFileType")
    public Result<Map<Integer, String>> getAll(){
        return ResultUtil.success(FileUploadUtil.getAllFileType());
    }

    /**
     * 提交保存数据
     *
     * @param request
     * @param typeCode
     * @return
     */
    @PostMapping(value = "submit")
    public  Result<Object> submit(HttpServletRequest request, @RequestParam("typeCode") Integer typeCode){
        Integer num = 0;
        //  导入到数据库
        switch (FileUploadUtil.FileType.getByValue(typeCode)) {
            case PRODUCT:
                List<Product> products = (List<Product>)request.getSession().getAttribute("products");
                request.getSession().removeAttribute("products");
                return ResultUtil.success(productService.saveList(products));
            case RAW_PRESOMA:
                List<RawPresoma> rawPresomas = (List<RawPresoma>)request.getSession().getAttribute("rawPresomas");
                request.getSession().removeAttribute("rawPresomas");
                return ResultUtil.success(rawPresomaService.saveList(rawPresomas));
            case RAW_LITHIUM:
                List<RawLithium> rawLithiums = (List<RawLithium>)request.getSession().getAttribute("rawLithiums");
                request.getSession().removeAttribute("rawLithiums");
                return ResultUtil.success(rawLithiumService.saveList(rawLithiums));
            case PROCESS_PREMIX_11:
                List<ProcessPremix> processPremixes11 = (List<ProcessPremix>)request.getSession().getAttribute("processPremixes11");
                request.getSession().removeAttribute("processPremixes11");
                return ResultUtil.success(processPremixService.saveList(processPremixes11));
            case PROCESS_PREMIX_15:
                List<ProcessPremix> processPremixes15 = (List<ProcessPremix>)request.getSession().getAttribute("processPremixes15");
                request.getSession().removeAttribute("processPremixes15");
                return ResultUtil.success(processPremixService.saveList(processPremixes15));
            case PROCESS_SIZE:
                List<ProcessSize> processSizes = (List<ProcessSize>)request.getSession().getAttribute("processSizes");
                request.getSession().removeAttribute("processSizes");
                return ResultUtil.success(processSizeService.saveList(processSizes));
            case PROCESS_LITHIUM:
                List<ProcessLithium> processLithiums = (List<ProcessLithium>)request.getSession().getAttribute("processLithiums");
                request.getSession().removeAttribute("processLithiums");
                return ResultUtil.success(processLithiumService.saveList(processLithiums));
            case PROCESS_BUCKLE:
                List<ProcessBuckle> processBuckles = (List<ProcessBuckle>)request.getSession().getAttribute("processBuckles");
                request.getSession().removeAttribute("processBuckles");
                return ResultUtil.success(processBuckleService.saveList(processBuckles));
        }

        return ResultUtil.error(new MesException(EnumException.UNKOWN_ERROR));
    }
    /**
     * 上传文件
     *
     * @param file
     * @param typeCode
     * @return
     */
    @PostMapping(value = "/open")
    public Result<Object> open(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("typeCode") Integer typeCode) {

        // 判断是否是空文件
        if (file.isEmpty()) {
            // 空文件
            return ResultUtil.error(new MesException(EnumException.UPLOAD_FAILED_FILE_EMPTY));
        }

        //  开始上传
        String path = null;
        try {
            path = FileUploadUtil.uploadFile(file, null, null);
        } catch (FileNotFoundException e) {
            // 文件不存在
            return ResultUtil.error(new MesException(EnumException.UPLOAD_FAILED));
        } catch (IOException e) {
            // 未知IO异常
            return ResultUtil.error(new MesException(EnumException.UPLOAD_FAILED_UNKNOWN_ERROR));
        }

        if (path == null) {
            // 未知IO异常
            return ResultUtil.error(new MesException(EnumException.UPLOAD_FAILED_UNKNOWN_ERROR));
        } else if (!path.endsWith(".xlsx")) {
            // 未知IO异常
            return ResultUtil.error(new MesException(EnumException.FILE_FORMAT_ERROR));
        }

        Integer num = 0;
        //  导入到数据库
        switch (FileUploadUtil.FileType.getByValue(typeCode)) {
            case PRODUCT:
                List<Product> products = productService.findAllByExcel(path, null, null);
                request.getSession().setAttribute("products", products);
                return ResultUtil.success(products);
            case RAW_PRESOMA:
                List<RawPresoma> rawPresomas = rawPresomaService.findAllByExcel(path, null, null);
                request.getSession().setAttribute("rawPresomas", rawPresomas);
                return ResultUtil.success(rawPresomas);
            case RAW_LITHIUM:
                List<RawLithium> rawLithiums = rawLithiumService.findAllByExcel(path, null, null);
                request.getSession().setAttribute("rawLithiums", rawLithiums);
                return ResultUtil.success(rawLithiums);
            case PROCESS_PREMIX_11:
                List<ProcessPremix> processPremixes11 = processPremixService.findAllByExcel(path, null, null, "预混11");
                request.getSession().setAttribute("processPremixes11", processPremixes11);
                return ResultUtil.success(processPremixes11);
            case PROCESS_PREMIX_15:
                List<ProcessPremix> processPremixes15 = processPremixService.findAllByExcel(path, null, null, "预混15");
                request.getSession().setAttribute("processPremixes15", processPremixes15);
                return ResultUtil.success(processPremixes15);
            case PROCESS_SIZE:
                List<ProcessSize> processSizes = processSizeService.findAllByExcel(path, null, null, 11);
                request.getSession().setAttribute("processSizes", processSizes);
                return ResultUtil.success(processSizes);
            case PROCESS_LITHIUM:
                List<ProcessLithium> processLithiums = processLithiumService.findAllByExcel(path, null, null, 11);
                request.getSession().setAttribute("processLithiums", processLithiums);
                return ResultUtil.success(processLithiums);
            case PROCESS_BUCKLE:
                List<ProcessBuckle> processBuckles = processBuckleService.findAllByExcel(path, null, null, 11);
                request.getSession().setAttribute("processBuckles", processBuckles);
                return ResultUtil.success(processBuckles);
        }

        return ResultUtil.error(new MesException(EnumException.UNKOWN_ERROR));
    }
}
