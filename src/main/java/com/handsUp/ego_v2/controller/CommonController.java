package com.handsUp.ego_v2.controller;


import com.handsUp.ego_v2.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/8
 */

@Slf4j
@RequestMapping("/common")
@RestController
public class CommonController {
    /**
     * 动态获取路径
     * @return
     * @throws IOException
     */
    private String getFileBasePath() throws IOException {
        File file = new File("");

        String path = file.getCanonicalPath() + "\\src\\main\\resources\\templates";

        File dir = new File(path);
        if(!dir.exists()) dir.mkdir();

        return path;
    }

    /**
     * 文件上传
     * 参数名字要和前端定义的相同，否则无法接受参数
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String basePath;
        try {
           basePath = getFileBasePath();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("获取路径时候出错");
        }

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newName = UUID.randomUUID().toString() + suffix;

        try {
            file.transferTo(new File(basePath + "\\" + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(newName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try{
            String basePath = getFileBasePath();

            FileInputStream inputStream = new FileInputStream(new File(basePath + "\\" + name));
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
