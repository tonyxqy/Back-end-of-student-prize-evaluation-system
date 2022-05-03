package com.example.demo.controller;
import com.example.demo.entity.Temp;
import com.example.demo.mapper.FileStorageMapper;
import com.example.demo.mapper.TempMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 *
 * @author dyh
 */
@RestController
@CrossOrigin
public class TempController {
    @Value("${file.upload.dir}")
    private String uploadFilePath;
    private String path = "files/";

    @Autowired
    TempMapper mapper;

    @GetMapping("/uploadExcel2")
    public String index() {
        return "file";
    }

    @PostMapping("/addexcel2")
    public String singleFileAdd(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {



        if(file.isEmpty()){
            return "文件为空";
        }
        String filePath = file.getOriginalFilename();
        String savePath = new File(path).getAbsolutePath()+File.separator+filePath;
        File targetFile = new File(savePath);
        if(!targetFile.exists()){
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
                    Temp tmp=new Temp();
                    tmp.setA(a.toString());
                    tmp.setB(b.toString());
                    tmp.setC(c.toString());
                    tmp.setD(d.toString());
                    mapper.insert(tmp);
                }
            }
        }
        return "hello";
    }

    private void saveFile(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadFilePath + "/" + file.getOriginalFilename());
        file.transferTo(path);
    }

    @GetMapping("/excelStatus2")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
