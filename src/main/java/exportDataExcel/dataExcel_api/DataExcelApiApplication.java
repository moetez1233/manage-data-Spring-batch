package exportDataExcel.dataExcel_api;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DataExcelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataExcelApiApplication.class, args);
	}

}
