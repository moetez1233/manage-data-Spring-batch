package exportDataExcel.dataExcel_api.config.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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

                    JobExecution jobExecution=jobLauncher.run(job, jobParameters);

                    // Wait for the job to complete
                    while (jobExecution.isRunning()) {
                        System.out.println("Job is still running...");
                        Thread.sleep(1000); // Check every second
                    }

                    // Check the job status
                    if (jobExecution.getStatus().isUnsuccessful()) {
                        System.out.println("Job failed with status: " + jobExecution.getStatus());
                    } else {
                        System.out.println("Job completed successfully with status: " + jobExecution.getStatus());

                        // Proceed to delete the file only after the job completes successfully
                        File file = new File("src/main/resources/data/camelOut/" + exchange.getIn().getHeader("CamelFileName"));
                        if (file.exists()) {
                            file.delete();
                            System.out.println("File deleted: " + file.getName());
                        }
                    }


                })
                .log("Completed Process 0");



    }
}
