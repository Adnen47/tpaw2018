package com.advyteam.sygha.batch.writer;

import com.advyteam.sygha.batch.constatnts.Constants;
import com.advyteam.sygha.batch.services.impl.DsnServiceImpl;
import com.advyteam.sygha.entity.CtpDsn;
import com.advyteam.sygha.entity.DSNIBloctem;
import com.advyteam.sygha.entity.Establishment;
import com.advyteam.sygha.entity.Insee;
import com.advyteam.sygha.repository.CtpDsnRepository;
import com.advyteam.sygha.repository.EstablishmentRepository;
import com.advyteam.sygha.repository.InseeRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.advyteam.sygha.batch.utils.UtilsExcelFile.*;

@Component
@StepScope
public class DSNGenerateExcelFileWriter implements ItemWriter<DSNIBloctem> {

    @Value("${dsn.export.path}")
    private String dsnExportPath;

    @Autowired
    CtpDsnRepository ctpDsnRepository;
    @Autowired
    EstablishmentRepository establishmentRepository;
    @Autowired
    InseeRepository inseeRepository;
    @Autowired
    DsnServiceImpl dsnService;
    private final SXSSFSheet sheet;
    private SXSSFRow row;
    private SXSSFRow calculRow;
    private SXSSFWorkbook workbook;
    private FileOutputStream outputStream;
    private List<CtpDsn> ctpDsns = new ArrayList<>();

    public DSNGenerateExcelFileWriter() {
        workbook = new SXSSFWorkbook();
        this.sheet = workbook.createSheet("DSN");
        createCalculRow();
        createHeader();
        sheet.addMergedRegion(new CellRangeAddress(0, 5, 0, 2));
        row = sheet.createRow(7);
    }

    private void createCalculRow() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLUE_GREY.getIndex());
        style.setFont(font);
        Row row3 = sheet.createRow(3);
        calculRow = sheet.createRow(4);
        Cell cellSumCotisationTotal = calculRow.createCell(MONTANT_TOTAL_COTISATION);
        cellSumCotisationTotal.setCellValue("0");
        cellSumCotisationTotal.setCellStyle(style);
        Cell cellNombreSumCotisationTotal = row3.createCell(MONTANT_TOTAL_COTISATION);
        cellNombreSumCotisationTotal.setCellValue("Nombre");
        cellNombreSumCotisationTotal.setCellStyle(style);
        Cell cellSumTaux = calculRow.createCell(TAUX);
        cellSumTaux.setCellValue("0");
        cellSumTaux.setCellStyle(style);
        Cell cellNombreSumTaux = row3.createCell(TAUX);
        cellNombreSumTaux.setCellValue("Nombre");
        cellNombreSumTaux.setCellStyle(style);
        Cell cellSumMontantAssiette = calculRow.createCell(MONTANT_ASSIETTE);
        cellSumMontantAssiette.setCellValue("0");
        cellSumMontantAssiette.setCellStyle(style);
        Cell cellNombreSumMontantAssiette = row3.createCell(MONTANT_ASSIETTE);
        cellNombreSumMontantAssiette.setCellValue("Nombre");
        cellNombreSumMontantAssiette.setCellStyle(style);
        Cell cellSumMontantCotisation = calculRow.createCell(MONTANT_COTISATION);
        cellSumMontantCotisation.setCellValue("0");
        cellSumMontantCotisation.setCellStyle(style);
        Cell cellNombreSumMontantCotisation = row3.createCell(MONTANT_COTISATION);
        cellNombreSumMontantCotisation.setCellValue("Nombre");
        cellNombreSumMontantCotisation.setCellStyle(style);
        Cell cellSumCotisationCTP = calculRow.createCell(COTISATION_CTP);
        cellSumCotisationCTP.setCellValue("0");
        cellSumCotisationCTP.setCellStyle(style);
        Cell cellNombreSumCotisationCTP = row3.createCell(COTISATION_CTP);
        cellNombreSumCotisationCTP.setCellValue("Nombre");
        cellNombreSumCotisationCTP.setCellStyle(style);
    }

    private void createHeader() {
        Row rowHeader = sheet.createRow(6);
        sheet.createFreezePane(3, 7);
        rowHeader.setHeight((short) 1000);
        for (int rn = 0; rn < headers.size(); rn++) {
            Cell cell = rowHeader.createCell(rn);
            cell.setCellValue(headers.get(rn).getName());
            cell.setCellStyle(createStyle(headers.get(rn).getColor()));
            sheet.setColumnWidth(rn, headers.get(rn).getWidth());
        }
    }

    @AfterStep
    public void afterStep() throws IOException {
        this.outputStream = new FileOutputStream(dsnExportPath);
        workbook.write(this.outputStream);
        this.outputStream.close();
    }

    @BeforeStep
    public void prepareData() throws IOException {
        // todo optimisation
        ctpDsns = ctpDsnRepository.findAll();
        Constants.idFimeCsv = dsnService.insertDocumentCsv();
    }

    @Override
    public void write(List<? extends DSNIBloctem> list) {
        list.forEach(this::writeDSNBloc);
    }

    private void writeDSNBloc(DSNIBloctem dsniBloctem) {
        if (newRow.contains(dsniBloctem.getKey().trim())) {
            calculateSumMontantTotalCotisation(MONTANT_TOTAL_COTISATION);
            calculateSumMontantTotalCotisation(TAUX);
            calculateSumMontantTotalCotisation(MONTANT_ASSIETTE);
            calculateSumMontantTotalCotisation(MONTANT_COTISATION);
            calculateSumMontantTotalCotisation(COTISATION_CTP);
            addNewRow();
        }
        addNewCell(dsniBloctem);
        fillCellPERPAI();
        fillCellSiret();
        fillCellLibelleEtablissment();
        fillCellLibelleTaux();
        fillCellCotisationCTP();
        fillCellMontantExtraitCTP();
        fillCellEcartCTP();
        fillCellTauxAt();
        fillCellEcartAT();
        fillCellTauxTransport();
        fillCellEcartTransport();
    }

    private void calculateSumMontantTotalCotisation(int cellNum) {
        if (chechCellNotNull(cellNum, row)) {
            BigDecimal currentValue = new BigDecimal(row.getCell(cellNum).getStringCellValue());
            BigDecimal sum = new BigDecimal(calculRow.getCell(cellNum).getStringCellValue());
            BigDecimal newSum = sum.add(currentValue);
            calculRow.getCell(cellNum).setCellValue(newSum.toString());
        }
    }

    private void addNewRow() {
        row = this.sheet.createRow(row.getRowNum() + 1);
        if (row.getRowNum() > 2) {
            for (int i = 0; i < 15; i++) {
                fillEmptyCell(i);
            }
        }
    }

    private void addNewCell(DSNIBloctem dsniBloctem) {
        if (this.getCellNumber(dsniBloctem.getKey()) != null) {
            Cell cell = row.createCell(this.getCellNumber(dsniBloctem.getKey()));
            cell.setCellValue(dsniBloctem.getValue());
        }
    }

    private void fillCellSiret() {
        if (chechCellNotNull(SIREN_ETA, row) && chechCellNotNull(NIC_ETAB, row)) {
            String siren = row.getCell(SIREN_ETA).getStringCellValue();
            String nic = row.getCell(NIC_ETAB).getStringCellValue();
            Cell cell = row.createCell(ETABLISSEMENT_SIRET);
            cell.setCellValue(siren + nic);
        }

    }

    private void fillCellLibelleEtablissment() {
        if (chechCellNotNull(ETABLISSEMENT_SIRET, row) && chechCellNotNull(FRACTION, row)) {
            Row lastRow = this.sheet.getRow(this.sheet.getRowNum(row) - 1);
            if (chechCellNotNull(ETABLISSEMENT_SIRET, lastRow) && chechCellNotNull(FRACTION, lastRow) &&
                    chechCellNotNull(ETABLISSEMENT_Libelle, lastRow)) {
                String lastSiret = lastRow.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
                String currentSiret = row.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
                String lastFraction = lastRow.getCell(FRACTION).getStringCellValue();
                String currentFraction = row.getCell(FRACTION).getStringCellValue();
                if (lastSiret.equals(currentSiret) && lastFraction.equals(currentFraction)) {
                    String lastLibelle = lastRow.getCell(ETABLISSEMENT_Libelle).getStringCellValue();
                    Cell cell = row.createCell(ETABLISSEMENT_Libelle);
                    cell.setCellValue(lastLibelle);
                    return;
                }
            }
            String siret = row.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
            String fraction = row.getCell(FRACTION).getStringCellValue();
            Establishment establishment = establishmentRepository.findBySiretAndFraction(siret, new BigDecimal(fraction));
            Cell cell = row.createCell(ETABLISSEMENT_Libelle);
            cell.setCellValue(establishment != null ? establishment.getLabel() : "Total");
        }

    }

    private void fillCellLibelleTaux() {
        if (chechCellNotNull(IDENTIFIANT, row) && chechCellNotNull(QUALIFIANT_ASSIETTE, row)) {
            String identifiant = "U" + row.getCell(IDENTIFIANT).getStringCellValue() + row.getCell(QUALIFIANT_ASSIETTE).getStringCellValue();
            CtpDsn ctpDsn = ctpDsns.stream().filter(ctp -> ctp.getCode().equals(identifiant)).findAny().orElse(null);
            if (ctpDsn != null) {
                Cell cellLibelle = row.createCell(LIBELLE);
                cellLibelle.setCellValue(ctpDsn.getLabel());
                Cell cellTaux = row.createCell(TAUX_CTP);
                cellTaux.setCellValue(ctpDsn.getRate());
            }
        }
    }

    private void fillCellCotisationCTP() {
        if (chechCellNotNull(MONTANT_ASSIETTE, row) && chechCellNotNull(TAUX_CTP, row)) {
            Cell cell = row.createCell(COTISATION_CTP);
            cell.setCellValue("" + (Double.parseDouble(row.getCell(MONTANT_ASSIETTE).getStringCellValue())
                    * Double.parseDouble(row.getCell(TAUX_CTP).getStringCellValue()) / 100));
        }
    }

    // todo champ historique
    private void fillCellMontantExtraitCTP() {
        Cell cell = row.createCell(MONTANT_CTP_EXTRAIT);
        cell.setCellValue("1000");
    }

    private void fillCellEcartCTP() {
        if (chechCellNotNull(MONTANT_CTP_EXTRAIT, row) && chechCellNotNull(COTISATION_CTP, row)) {
            Cell cell = row.createCell(ECART_CTP);
            cell.setCellValue("" + (Double.parseDouble(row.getCell(COTISATION_CTP).getStringCellValue()) -
                    Double.parseDouble(row.getCell(MONTANT_CTP_EXTRAIT).getStringCellValue()))
            );
        }
    }

    private void fillCellTauxAt() {
        if (chechCellNotNull(ETABLISSEMENT_SIRET, row) && chechCellNotNull(FRACTION, row)) {
            Row lastRow = this.sheet.getRow(this.sheet.getRowNum(row) - 1);
            if (chechCellNotNull(ETABLISSEMENT_SIRET, lastRow) && chechCellNotNull(TAUX_AT, lastRow)) {
                String lastSiret = lastRow.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
                String currentSiret = row.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
                String lastFraction = lastRow.getCell(FRACTION).getStringCellValue();
                String currentFraction = row.getCell(FRACTION).getStringCellValue();
                if (lastSiret.equals(currentSiret) && lastFraction.equals(currentFraction)) {
                    String lastLibelle = lastRow.getCell(TAUX_AT).getStringCellValue();
                    Cell cell = row.createCell(TAUX_AT);
                    cell.setCellValue(lastLibelle);
                    return;
                }
            }
            String siret = row.getCell(ETABLISSEMENT_SIRET).getStringCellValue();
            String fraction = row.getCell(FRACTION).getStringCellValue();
            Establishment establishment = establishmentRepository.findBySiretAndFraction(siret, new BigDecimal(fraction));
            Cell cell = row.createCell(TAUX_AT);
            cell.setCellValue(establishment != null ? establishment.getRateAT().toString() : "12");
        }
    }

    private void fillCellEcartAT() {
        if (chechCellNotNull(IDENTIFIANT, row) && chechCellNotNull(TAUX, row)
                && chechCellNotNull(MONTANT_ASSIETTE, row) && chechCellNotNull(TAUX_AT, row)) {
            boolean b1 = Double.parseDouble(row.getCell(MONTANT_ASSIETTE).getStringCellValue()) == 920;
            boolean b2 = Arrays.asList("100", "726", "626", "336").contains(row.getCell(IDENTIFIANT).getStringCellValue());
            Cell cell = row.createCell(ECART_AT);
            cell.setCellValue(b1 && b2 ? "" + (Double.parseDouble(row.getCell(TAUX_AT).getStringCellValue()) -
                    Double.parseDouble(row.getCell(TAUX).getStringCellValue())) : "0");
        }
    }

    private void fillCellTauxTransport() {
        if (chechCellNotNull(CODE_INSEE_COMMUNE, row)) {
            Row lastRow = this.sheet.getRow(this.sheet.getRowNum(row) - 1);
            if (chechCellNotNull(CODE_INSEE_COMMUNE, lastRow) && chechCellNotNull(TAUX_TRANSPORT, lastRow)) {
                String lastCodeInsee = lastRow.getCell(CODE_INSEE_COMMUNE).getStringCellValue();
                String currentCodeInsee = row.getCell(CODE_INSEE_COMMUNE).getStringCellValue();
                if (lastCodeInsee.equals(currentCodeInsee)) {
                    String tauxTransport = lastRow.getCell(TAUX_TRANSPORT).getStringCellValue();
                    Cell cell = row.createCell(TAUX_TRANSPORT);
                    cell.setCellValue(tauxTransport);
                    return;
                }
            }
            String code = row.getCell(CODE_INSEE_COMMUNE).getStringCellValue();
            Insee insee = inseeRepository.findByCode(code);
            Cell cell = row.createCell(TAUX_TRANSPORT);
            cell.setCellValue(insee != null ? insee.getTransportRate() : "32");
        }
    }

    // todo champ PERPAI
    private void fillCellPERPAI() {
        Cell cell = row.createCell(PERIOPAI);
        cell.setCellValue("201911");
    }

    private void fillCellEcartTransport() {
        if (chechCellNotNull(IDENTIFIANT, row) && chechCellNotNull(TAUX, row) && chechCellNotNull(TAUX_TRANSPORT, row)) {
            boolean b = row.getCell(IDENTIFIANT).getStringCellValue().equals("900");
            Cell cell = row.createCell(ECART_TRANSPORT);
            cell.setCellValue(b ? "" + (Double.parseDouble(row.getCell(TAUX_TRANSPORT).getStringCellValue()) -
                    Double.parseDouble(row.getCell(TAUX).getStringCellValue())) : "0");
        }
    }

    private boolean chechCellNotNull(int cellnum, Row rowCell) {
        return rowCell != null && rowCell.getCell(cellnum) != null &&
                StringUtils.isNotEmpty(StringUtils.trim(rowCell.getCell(cellnum).getStringCellValue()));
    }

    private Integer getCellNumber(String celluleValue) {
        return mapping.get(celluleValue.trim());
    }

    private void fillEmptyCell(int cellNumber) {
        Row lastRow = this.sheet.getRow(this.sheet.getRowNum(row) - 1);
        if (chechCellNotNull(cellNumber, lastRow)) {
            Cell cell = row.createCell(cellNumber);
            cell.setCellValue(lastRow.getCell(cellNumber).getStringCellValue());
        }
    }

    private boolean checkNewRow(DSNIBloctem dsniBloctem) {
        if (this.getCellNumber(dsniBloctem.getKey()) != null) {
            Row lastRow = this.sheet.getRow(this.sheet.getRowNum(row) - 1);
            if (lastRow != null && lastRow.getCell(this.getCellNumber(dsniBloctem.getKey())) != null
                    && StringUtils.isNotEmpty(lastRow.getCell(this.getCellNumber(dsniBloctem.getKey())).getStringCellValue())) {
                return true;
            }
        }
        return false;
    }

    private CellStyle createStyle(short indexColor) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(indexColor);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        return style;
    }
}
