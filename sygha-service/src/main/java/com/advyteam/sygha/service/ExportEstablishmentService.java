package com.advyteam.sygha.service;

import com.advyteam.sygha.entity.Establishment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportEstablishmentService {

    public static ByteArrayInputStream establishmentsToExcel(List<Establishment> establishments) throws IOException {
        String[] COLUMNs = {"Code", "Label", "Entreprise", "Siret", "Nic", "Fraction", "Taux", "Date"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            //CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Establishments");

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
            for (Establishment establishment : establishments) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(establishment.getCode());
                row.createCell(1).setCellValue(establishment.getLabel());
                if(establishment.getCompany()!=null){
                    row.createCell(2).setCellValue(establishment.getCompany().getLabel());
                }
                row.createCell(3).setCellValue(establishment.getSiret());
                row.createCell(4).setCellValue(establishment.getNic());
                row.createCell(5).setCellValue(establishment.getFraction().toString());
                row.createCell(6).setCellValue(establishment.getRateAT().toString());
                row.createCell(7).setCellValue(establishment.getReferentielAudit().getUpdateDate().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
