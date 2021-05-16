package com.advyteam.sygha.batch.processor;

import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class DSNGenerateExcelFileProcessor implements ItemProcessor<DSNIBloctem, DSNIBloctem> {

    @Override
    public DSNIBloctem process(final DSNIBloctem dsniBloctem) throws ParseException {
        return dsniBloctem;
    }
}