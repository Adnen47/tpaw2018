package com.advyteam.sygha.batch.mapper;

import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class DSNBlocItemMapper implements FieldSetMapper<DSNIBloctem> {

    @Override
    public DSNIBloctem mapFieldSet(FieldSet fieldSet) {
        String fieldValue = fieldSet.readString("value").toString().substring(1,fieldSet.readString("value").toString().length()-1);
        return new DSNIBloctem(fieldSet.readString("key"), fieldValue);
    }
}
