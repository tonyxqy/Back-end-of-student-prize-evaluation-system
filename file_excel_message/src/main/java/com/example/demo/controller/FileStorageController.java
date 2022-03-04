package com.example.demo.controller;

import ch.qos.logback.core.util.FileUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.example.demo.entity.FileStorage;
import com.example.demo.mapper.FileStorageMapper;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * 文件上传
 *
 * @author dyh
 */
@RestController
@CrossOrigin
public class FileStorageController {
    @Value("${file.upload.dir}")
    private String uploadFilePath;
    private String path = "files/";

    @Autowired
    FileStorageMapper mapper;

    @GetMapping("/")
    public String index() {
        return "file";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@RequestParam ("number") String number) throws IOException {



        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "文件为空,请选择你的文件上传");
            //return "redirect:uploadStatus";
        }

        String filename=number+file.getOriginalFilename();

        //File fileTempObject=new File(uploadFilePath+File.separator+file.getOriginalFilename());
        File fileTempObject=new File( new File(path).getAbsolutePath()+File.separator+filename);

        if(!fileTempObject.getParentFile().exists()){
            fileTempObject.getParentFile().mkdirs();
        }

        if(fileTempObject.exists()){
            return "文件已存在";
        }

        try{
            file.transferTo(fileTempObject);
        }
        catch (Exception e){
            return "发生错误！存储失败！请重试或联系管理员!";
        }

        FileStorage _file=new FileStorage();
        _file.setFilename(filename);
        _file.setNumber(number);
        mapper.insert(_file);

        return fileTempObject.getAbsolutePath();
    }

    private void saveFile(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadFilePath + "/" + file.getOriginalFilename());
        file.transferTo(path);
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/getPng")
    public String getPng(@RequestBody HashMap<String, Object> map)
    {
        String p=new File(path).getAbsolutePath()+File.separator;
        List<FileStorage> lst = mapper.selectByMap(map);
        String filename=lst.get(0).getFilename();
        if(lst.isEmpty()){
            return "500";
        }
        return p+filename;
    }

}
