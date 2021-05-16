package com.advyteam.sygha.batch.listener;

/**
 * Created by hboulahia on 05/11/19.
 */
import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.core.ItemReadListener;


public class StepItemReadListener implements ItemReadListener<DSNIBloctem> {

    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(DSNIBloctem item) {

    }

    @Override
    public void onReadError(Exception ex) {
    }
}

