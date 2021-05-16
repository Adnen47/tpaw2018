package com.advyteam.sygha.service;

import com.advyteam.sygha.entity.Company;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportCompanyService {

    public static ByteArrayInputStream companiesToExcel(List<Company> companies) throws IOException {
        String[] COLUMNs = {"Code", "Label", "Siren", "Date"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            //CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Companies");

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
            for (Company company : companies) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(company.getCode());
                row.createCell(1).setCellValue(company.getLabel());
                row.createCell(2).setCellValue(company.getSiren());
                row.createCell(3).setCellValue(company.getReferentielAudit().getUpdateDate().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
