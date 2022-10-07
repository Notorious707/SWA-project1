package org.projectbatch.projectBatch.batch;

import org.projectbatch.projectBatch.entity.Student;
import org.projectbatch.projectBatch.dto.StudentDto;
import org.projectbatch.projectBatch.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private StudentRepository studentRepository;


    @Bean
    public FlatFileItemReader<StudentDto> reader() {
        return new FlatFileItemReaderBuilder<StudentDto>()
                .name("personItemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName","gpa","age" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StudentDto>() {{
                    setTargetType(StudentDto.class);
                }})
                .build();
    }
    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Student> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Student>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO student (first, last,gpa,dob) VALUES ( :first, :last, :gpa, :dob)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Student> writer) {
        return stepBuilderFactory.get("step1")
                .<StudentDto, Student> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }



}
