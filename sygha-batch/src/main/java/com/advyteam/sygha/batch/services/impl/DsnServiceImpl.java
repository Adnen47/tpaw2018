package com.advyteam.sygha.batch.services.impl;

import com.advyteam.sygha.batch.constatnts.Constants;
import com.advyteam.sygha.entity.DSNIBloctem;
import com.advyteam.sygha.batch.services.DsnService;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.DsnRecordedEstablishment;
import com.advyteam.sygha.entity.Establishment;
import com.advyteam.sygha.repository.CompanyRepository;
import com.advyteam.sygha.repository.DsnRecordedEstablishmentRepository;
import com.advyteam.sygha.repository.EstablishmentRepository;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by hboulahia on 04/11/19.
 */
@Component("dsnService")
public class DsnServiceImpl implements DsnService {

    @Value("${dsn.input.path}")
    private String dsnInputPath;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private DsnRecordedEstablishmentRepository dsnRecordedEstablishmentRepository ;

    private final static Logger logger = Logger.getLogger(DsnServiceImpl.class.getName());

    static final String ESTABLISHMENT = "S20.G00.05.001";
    static final String ESTABLISHMENT_DATABASE = "ESTABLISHMENT";
    static final String COMPANY_DATABASE = "COMPANY";
    static final String DSN_SIREN = "S21.G00.06.001";
    static final String DSN_NIC = "S21.G00.11.001";
    static final String FRACTION = "S20.G00.05.003";


    /**
     * creation de l'entité après la lecture de chaque ligne
     * @param dsniBloctem
     * @return
     * @throws ParseException
     */
    @Override
    public DsnRecordedEstablishment createEntity(DSNIBloctem dsniBloctem) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.FRANCE);
        DsnRecordedEstablishment dsnRecordedEstablishment = new DsnRecordedEstablishment();

        if(ESTABLISHMENT.equals(dsniBloctem.getKey())){
            dsnRecordedEstablishment = new DsnRecordedEstablishment();
            Constants.idEstablishmentDsn = null;
            logger.info("------------------------- nouvel Etablissement -------------------- ");
            // nouvel etablissement
            List<DSNIBloctem> listDsn = new ArrayList<>();
            listDsn.add(dsniBloctem);
            dsnRecordedEstablishment.setValues(listDsn);
            dsnRecordedEstablishment.setDate(new Date());
            DBRef dsnFile = new DBRef("files",Constants.idFimeCsv);
            dsnRecordedEstablishment.setDsnFile(dsnFile);
            dsnRecordedEstablishmentRepository.insert(dsnRecordedEstablishment);
            Constants.idEstablishmentDsn = dsnRecordedEstablishment.getId();
        }else if(Constants.idEstablishmentDsn != null){
            dsnRecordedEstablishment = dsnRecordedEstablishmentRepository.findById(new ObjectId(Constants.idEstablishmentDsn));
            if(DSN_SIREN.equals(dsniBloctem.getKey())){
                //recuperer la societe
                Constants.siren = dsniBloctem.getValue();
                Company company = companyRepository.findBySiren(Constants.siren);
                logger.info("company "+company);
                if(company != null){
                    DBRef companyDbref = new DBRef(COMPANY_DATABASE,company.getId());
                    dsnRecordedEstablishment.setCompany(companyDbref);
                    updateDsnEstablishment(dsnRecordedEstablishment,dsniBloctem);
                }
            }else if(FRACTION.equals(dsniBloctem.getKey())){
                Constants.fraction = new BigDecimal(dsniBloctem.getValue());
            } else if(DSN_NIC.equals(dsniBloctem.getKey())){
                Constants.nic = dsniBloctem.getValue();
                String siret = Constants.siren.concat(Constants.nic);
                //recuperer l'etablissement
                Establishment establishment = establishmentRepository.findBySiretAndFraction(siret, Constants.fraction);
                logger.info("etablissement "+establishment);
                if(establishment != null){
                    DBRef etablishmentdbref = new DBRef(ESTABLISHMENT_DATABASE,establishment.getId());
                    dsnRecordedEstablishment.setEstablishment(etablishmentdbref);
                    updateDsnEstablishment(dsnRecordedEstablishment,dsniBloctem);
                }
            }


        }
        return dsnRecordedEstablishment;
    }

    private void updateDsnEstablishment(DsnRecordedEstablishment dsnRecordedEstablishment,DSNIBloctem dsniBloctem){
        List<DSNIBloctem> listValues = dsnRecordedEstablishment.getValues();
        listValues.add(dsniBloctem);
        dsnRecordedEstablishment.setValues(listValues);
        dsnRecordedEstablishmentRepository.save(dsnRecordedEstablishment);
    }

    /**
     * insert DSN document into database.
     *
     */
    @Override
    public ObjectId insertDocumentCsv() {
        ObjectId fileId = null;
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("SYGHA_DB");
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDatabase,"files");

        try{
            InputStream inputStream = new FileInputStream(dsnInputPath);
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(3584000).metadata(new Document("type","paie"));
            fileId = gridFSBucket.uploadFromStream("dsn File",inputStream,options);

        }catch(FileNotFoundException e){
            logger.warning(e.toString());
        }
        return fileId;

    }
}
