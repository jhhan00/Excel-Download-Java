package com.example.back.controller;

import com.example.back.model.IntAndDouble;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test1")
    public String getString() {
        return "This is an example!!!";
    }

    @PostMapping("/binding")
    public void showData(@RequestBody List<IntAndDouble> iad) {
        for (IntAndDouble ia : iad) {
            System.out.println(ia.getMode() + " " + ia.getAffinity());
            System.out.println(ia.getLb() + " " + ia.getUb());
            System.out.println(ia.getpUrl() + " " + ia.getqUrl());
            System.out.println("--");
        }
    }

    @PostMapping("/excel")
    public void getExcelFile(@RequestBody List<IntAndDouble> iads, HttpServletResponse response) throws IOException {
        for (IntAndDouble iad : iads) {
            System.out.println(iad);
        }

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row;
//        Cell cell;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Mode");
        row.createCell(1).setCellValue("Affinity");
        row.createCell(2).setCellValue("lb");
        row.createCell(3).setCellValue("ub");
        row.createCell(4).setCellValue("purl");
        row.createCell(5).setCellValue("qurl");

        // Body
        for (IntAndDouble iad : iads) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(iad.getMode());
            row.createCell(1).setCellValue(iad.getAffinity());
            row.createCell(2).setCellValue(iad.getLb());
            row.createCell(3).setCellValue(iad.getUb());
            row.createCell(4).setCellValue(iad.getpUrl());
            row.createCell(5).setCellValue(iad.getqUrl());
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=example.xls");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
