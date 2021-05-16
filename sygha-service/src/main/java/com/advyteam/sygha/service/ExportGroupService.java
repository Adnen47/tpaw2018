package com.advyteam.sygha.service;
import java.io.IOException;
import java.util.List;

import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.Group;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportGroupService {

    public static ByteArrayInputStream groupsToExcel(List<Group> groups) throws IOException {
        String[] COLUMNs = {"Code", "Label", "Adresse", "Entreprises", "Date"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            //CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Groups");

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
            for (Group group : groups) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(group.getCode());
                row.createCell(1).setCellValue(group.getLabel());
                row.createCell(2).setCellValue(group.getAddress());
                String chaine="";
                for(Company company: group.getCompanies()){
                    chaine += company.getLabel() + " ; ";
                }
                row.createCell(3).setCellValue(chaine);
                row.createCell(4).setCellValue(group.getReferentielAudit().getUpdateDate().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
