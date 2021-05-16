package com.advyteam.sygha.batch.listener;

import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * Created by hboulahia on 05/11/19.
 */
@Component("stepItemProcessorListener")
public class StepItemProcessorListener implements ItemProcessListener<DSNIBloctem,DSNIBloctem> {
    @Override
    public void beforeProcess(DSNIBloctem o) {

    }

    @Override
    public void afterProcess(DSNIBloctem o, DSNIBloctem o2) {

    }

    @Override
    public void onProcessError(DSNIBloctem o, Exception e) {

    }
}
