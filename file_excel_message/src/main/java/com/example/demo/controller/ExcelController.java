package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Excel;
import com.example.demo.entity.Temp;
import com.example.demo.mapper.ExcelMapper;
import com.example.demo.mapper.TempMapper;
import org.apache.catalina.connector.Request;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.Query;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


/**
 * 文件上传
 *
 * @author dyh
 */
@RestController
@CrossOrigin
public class ExcelController {
    @Value("${file.upload.dir}")
    private String uploadFilePath;
    private String path = "files/";

    @Autowired
    ExcelMapper mapper;

    @GetMapping("/uploadExcel")
    public String index() {
        return "file";
    }

    @PostMapping("/addexcel")
    public String singleFileAdd(@RequestParam("file") MultipartFile file,
                                RedirectAttributes redirectAttributes) throws IOException {



        if(file.isEmpty()){
            return "文件为空";
        }
        String filePath = System.currentTimeMillis()+file.getOriginalFilename();
        String savePath = new File(path).getAbsolutePath()+File.separator+filePath;
        File targetFile = new File(savePath);
        if(!targetFile.getParentFile().exists()){
            targetFile.mkdirs();
        }

        if(targetFile.exists()){
            return "文件已存在";
        }
        file.transferTo(targetFile);
        InputStream is = new FileInputStream(savePath);

        Workbook hssfWorkbook = null;


        if (filePath.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        }else if (filePath.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }

        QueryWrapper<Excel> query=new QueryWrapper<>();
        query.isNotNull("id");
        mapper.delete(query);

        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    Cell a = hssfRow.getCell(0);
                    Cell b = hssfRow.getCell(1);
                    Cell c = hssfRow.getCell(2);
                    Cell d = hssfRow.getCell(3);
                    Excel tmp=new Excel();
                    tmp.setHdmc(a.toString());
                    tmp.setJx(b.toString());
                    tmp.setRdjb(c.toString());
                    tmp.setFz((int) d.getNumericCellValue());
                    mapper.insert(tmp);
                }
            }
        }
        return "success";
    }

    private void saveFile(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadFilePath + "/" + file.getOriginalFilename());
        file.transferTo(path);
    }

    @GetMapping("/excelStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/getRewardName")
    public List<Object> getRewardName() {
        QueryWrapper<Excel> newquery=new QueryWrapper<>();
        newquery.select("hdmc").groupBy("hdmc");
        return mapper.selectObjs(newquery);
    }

    @PostMapping("/getRewardGrade")
    public List<Object> getRewardGrade(@RequestBody Map<String,String> map) {
        QueryWrapper<Excel> newquery=new QueryWrapper<>();
        newquery.select("jx").eq("hdmc",map.get("name"));
        return mapper.selectObjs(newquery);
    }
}