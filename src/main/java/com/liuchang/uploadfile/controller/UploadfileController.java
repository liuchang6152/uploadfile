package com.liuchang.uploadfile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：liuchang
 * @ Date       ：Created in 17:23 2020/2/5
 * @ Description：
 * @ Modified By：
 */
@CrossOrigin
@RestController
@RequestMapping(value = "file/imgUpload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadfileController {
    /**
     * 文件写入路径，取自properties文件
     */
    @Value("${upload.file}")
    private String filePath;

    /**
     * 上传文件
     * @param file
     * @param req
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @throws InterruptedException
     */
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException, InterruptedException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取文件存储路径（绝对路径）
        String path = filePath;
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);


        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return "success";
    }

    /**
     * 获取上传文件百分比，
     * @param request
     * @return 返回10，即为上传进度的10%
     */
    @GetMapping(value = "/uploadStatus")
    public Integer uploadStatus(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object percent = session.getAttribute("upload_percent");
        return null != percent ? (Integer) percent : 0;
    }
}
