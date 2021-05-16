package com.advyteam.sygha.batch.writer;

import com.advyteam.sygha.batch.services.impl.DsnServiceImpl;
import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
@Component
@StepScope
public class DSNRecordDataWriter implements ItemWriter<DSNIBloctem> {


    @Override
    public void write(List<? extends DSNIBloctem> list) throws Exception {
    }
}
