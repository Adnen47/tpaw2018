package com.advyteam.sygha.batch.services;

import com.advyteam.sygha.entity.DSNIBloctem;
import com.advyteam.sygha.entity.DsnRecordedEstablishment;
import org.bson.types.ObjectId;

import java.text.ParseException;

/**
 * Created by hboulahia on 04/11/19.
 */
public interface DsnService {

    DsnRecordedEstablishment createEntity(DSNIBloctem dsniBloctem) throws ParseException;

    ObjectId insertDocumentCsv();
}
