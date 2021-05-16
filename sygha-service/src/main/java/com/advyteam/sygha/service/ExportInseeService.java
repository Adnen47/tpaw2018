package com.advyteam.sygha.service;

import com.advyteam.sygha.entity.Insee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportInseeService {

    public static ByteArrayInputStream inseesToExcel(List<Insee> insees) throws IOException {
        String[] COLUMNs = {"Code", "transportRate", "Date"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            //CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Insees");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (Insee insee : insees) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(insee.getCode());
                row.createCell(1).setCellValue(insee.getTransportRate());
                row.createCell(2).setCellValue(insee.getReferentielAudit().getUpdateDate().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
