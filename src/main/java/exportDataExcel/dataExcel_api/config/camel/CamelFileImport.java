package exportDataExcel.dataExcel_api.config.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CamelFileImport extends RouteBuilder {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Override
    public void configure() throws Exception {
        from("file://C:\\Users\\moete\\OneDrive\\Bureau\\DataIn?delete=true")
                .to("file://src/main/resources/data/camelOut")
                .process(exchange -> {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addLong("startAt", System.currentTimeMillis())
                            .toJobParameters();

                    jobLauncher.run(job, jobParameters);

                })
                .log("Completed Process 0")
                .to("direct:aggregate");


        from("direct:process1")
                .log("Starting Process 1").process(exchange -> {
                    // After the batch process completes, delete the file from the output directory
                    File file = new File("src/main/resources/data/camelOut/" + exchange.getIn().getHeader("CamelFileName"));
                    if (file.exists()) {
                        file.delete();
                    }
                });
    }
}
