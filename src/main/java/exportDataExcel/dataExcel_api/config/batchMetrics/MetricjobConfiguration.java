package exportDataExcel.dataExcel_api.config.batchMetrics;


import exportDataExcel.dataExcel_api.config.batchMetrics.processor.MetricsItemProcessor;
import exportDataExcel.dataExcel_api.dto.MetricDto;
import exportDataExcel.dataExcel_api.models.Metric;
import exportDataExcel.dataExcel_api.repositories.MetricRepository;
import jakarta.transaction.Transactional;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Transactional
public class MetricjobConfiguration {

    private JobBuilder jobBuilder;

    private StepBuilder stepBuilder;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private MetricsItemProcessor metricsItemProcessor;

    @Bean
    public FlatFileItemReader<MetricDto> readerCsv() {

        FlatFileItemReader<MetricDto> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/data/camelOut/metrics.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<MetricDto> lineMapper() {
        DefaultLineMapper<MetricDto> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("nameUsine","typeMetric", "device", "value","created_at");
        lineTokenizer.setStrict(false);
        BeanWrapperFieldSetMapper<MetricDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(MetricDto.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }



    @Bean
    public RepositoryItemWriter<Metric> itemWriter()

    {
        RepositoryItemWriter<Metric> itemWriter1 = new RepositoryItemWriter<Metric>();
        itemWriter1.setRepository(metricRepository);
        itemWriter1.setMethodName("save");
        return itemWriter1;
    }

    @Bean
    public Step step1(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-step",jobRepository).<MetricDto, Metric>chunk(10,transactionManager)
                .reader(readerCsv())
                .processor(metricsItemProcessor)
                .writer(itemWriter())
                .faultTolerant()
                //.skipLimit(100)
                //.skip(FlatFileParseException.class)
                .skipPolicy(skipPolicy())
                .listener(skipListener())
                .build();
    }
    @Bean
    public Job jobEmploye(JobRepository jobRepository,Step step1) {
        return new JobBuilder("importMetrics",jobRepository)
                .flow(step1).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    @Bean
    public SkipPolicy skipPolicy(){
        return new ExceptionSkipPolicy();
    }
    public SkipListener skipListener(){
        return new StepSkipListener();
    }


}
