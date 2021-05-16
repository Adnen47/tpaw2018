package com.advyteam.sygha.batch.config;

import com.advyteam.sygha.batch.listener.DSNJobListener;
import com.advyteam.sygha.batch.listener.StepItemProcessorListener;
import com.advyteam.sygha.batch.listener.StepItemReadListener;
import com.advyteam.sygha.batch.mapper.DSNBlocItemMapper;
import com.advyteam.sygha.batch.processor.DSNGenerateExcelFileProcessor;
import com.advyteam.sygha.batch.processor.DSNRecordDataProcessor;
import com.advyteam.sygha.batch.writer.DSNRecordDataWriter;
import com.advyteam.sygha.batch.writer.DSNGenerateExcelFileWriter;
import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DSNGenerateExcelFileWriter dsnGenerateExcelFileWriter;

    @Autowired
    private
    DSNRecordDataWriter dsnRecordDataWriter;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    public FlatFileItemReader<DSNIBloctem> reader() {
        return new FlatFileItemReaderBuilder<DSNIBloctem>()
                .name("dsnItemReader")
                .resource(new ClassPathResource("dsn.csv"))
                .delimited()
                .names("key", "value")
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<DSNIBloctem>() {{
                    setTargetType(DSNIBloctem.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<DSNIBloctem> lineMapper() {
        final DefaultLineMapper<DSNIBloctem> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("key", "value");
        final DSNBlocItemMapper fieldSetMapper = new DSNBlocItemMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public DSNGenerateExcelFileProcessor generateFileProcessor() {
        return new DSNGenerateExcelFileProcessor();
    }
    @Bean
    public DSNRecordDataProcessor recordDataProcessor() {
        return new DSNRecordDataProcessor();
    }
    @Bean
    public StepItemReadListener stepreder() {
        return new StepItemReadListener();
    }

    @Bean
    public Step stepGenerationExcelFile() {
        return stepBuilderFactory.get("stepGenerationExcelFile")
                .<DSNIBloctem, DSNIBloctem>chunk(100000)
                .reader(reader())
                .listener(stepreder())
                .listener(getItemWriteListener(null))
                .processor(generateFileProcessor())
                .writer(dsnGenerateExcelFileWriter)
                .listener(new StepItemProcessorListener())
                .build();
    }

    @Bean
    public Step stepRecordData() {
        return stepBuilderFactory.get("stepRecordData")
                .<DSNIBloctem, DSNIBloctem>chunk(100000)
                .reader(reader())
                .listener(stepreder())
                .processor(recordDataProcessor())
                .writer(dsnRecordDataWriter)
                .listener(new StepItemProcessorListener())
                .build();
    }

    @Bean
    public Job importDSNeJob(Step stepGenerationExcelFile, Step stepRecordData) {
        return jobBuilderFactory.get("importDSNeJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener())
                .flow(stepGenerationExcelFile)
                .next(stepRecordData)
                .end()
                .build();
    }

    @Bean
    @JobScope
    public ItemWriteListener<DSNIBloctem> getItemWriteListener(@Value(("#{jobParameters['absoluteFileName']}")) String absoluteFileName){
        return dSNJobListener(null);
    }

    @Bean
    @JobScope
    public JobExecutionListener jobExecutionListener(){
        return dSNJobListener(null);
    }

    @Bean
    @JobScope
    public DSNJobListener dSNJobListener(@Value(("#{jobParameters['absoluteFileName']}")) String absoluteFileName){
        DSNJobListener dSNJobListener =  new DSNJobListener();
        dSNJobListener.setFileName(absoluteFileName);
        dSNJobListener.setSimpMessagingTemplate(simpMessagingTemplate);
        return dSNJobListener;
    }
}
