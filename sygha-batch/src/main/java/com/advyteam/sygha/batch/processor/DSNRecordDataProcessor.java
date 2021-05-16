package com.advyteam.sygha.batch.processor;

import com.advyteam.sygha.batch.services.impl.DsnServiceImpl;
import com.advyteam.sygha.entity.DSNIBloctem;
import com.advyteam.sygha.entity.DsnRecordedEstablishment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class DSNRecordDataProcessor implements ItemProcessor<DSNIBloctem, DSNIBloctem> {
    @Autowired
    DsnServiceImpl dsnService;
    @Override
    public DSNIBloctem process(final DSNIBloctem dsniBloctem) throws ParseException {
        DsnRecordedEstablishment dsnRecordedEstablishment = dsnService.createEntity(dsniBloctem);
        return dsniBloctem;
    }
}
