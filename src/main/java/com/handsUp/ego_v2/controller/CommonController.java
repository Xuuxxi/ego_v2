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

// for dbs final test

@Slf4j
@RequestMapping("/common")
@RestController
public class CommonController {
    // for dbs final test
    private String getFileBasePath() throws IOException {
//        File file = new File("");
//
//        String path = file.getCanonicalPath() + "\\src\\main\\resources\\templates";
//
//        File dir = new File(path);
//        if(!dir.exists()) dir.mkdir();

        return "/home/xuuxxi/egoFile/";
    }

    // for dbs final test
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String basePath = "/home/xuuxxi/egoFile/";

//        try {
//           basePath = getFileBasePath();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.error("获取路径时候出错");
//        }

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newName = UUID.randomUUID().toString() + suffix;

        try {
            file.transferTo(new File(basePath + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(newName);
    }

    // for dbs final test
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try{
            String basePath = "/home/xuuxxi/egoFile/";

            FileInputStream inputStream = new FileInputStream(new File(basePath + name));
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
