package product.ma.batchservice.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import product.ma.batchservice.model.ReportCard;

import product.ma.batchservice.repository.ReportCardRepository;

import javax.xml.transform.Result;
import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class ReportCardBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ReportCardRepository reportCardRepository;

    @Bean
    public ItemReader<Result> resultReader() {
        RepositoryItemReader<Result> reader = new RepositoryItemReader<>();
        reader.setRepository(resultRepository);
        reader.setMethodName("findAll");
        reader.setSort(Collections.singletonMap("studentId", Sort.Direction.ASC));
        return reader;
    }

    @Bean
    public ItemProcessor<Result, ReportCard> resultProcessor() {
        return result -> {
            double gpa = calculateGPA(result.getGrade());
            String remarks = generateRemarks(gpa);

            ReportCard reportCard = new ReportCard();
            reportCard.setStudentId(result.getStudentId());
            reportCard.setSemester("Fall 2023");
            reportCard.setGpa(gpa);
            reportCard.setRemarks(remarks);

            return reportCard;
        };
    }

    @Bean
    public ItemWriter<ReportCard> reportCardWriter() {
        RepositoryItemWriter<ReportCard> writer = new RepositoryItemWriter<>();
        writer.setRepository(reportCardRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step generateReportCardsStep() {
        return stepBuilderFactory.get("generateReportCardsStep")
                .<Result, ReportCard>chunk(10)
                .reader(resultReader())
                .processor(resultProcessor())
                .writer(reportCardWriter())
                .build();
    }

    @Bean
    public Job generateReportCardsJob() {
        return jobBuilderFactory.get("generateReportCardsJob")
                .start(generateReportCardsStep())
                .build();
    }

    private double calculateGPA(double grade) {
        return grade / 25.0;
    }

    private String generateRemarks(double gpa) {
        if (gpa >= 3.5) return "Excellent";
        else if (gpa >= 2.5) return "Good";
        else return "Needs Improvement";
    }
}