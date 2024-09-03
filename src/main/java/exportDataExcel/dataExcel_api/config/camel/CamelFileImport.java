package exportDataExcel.dataExcel_api.config.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelFileImport extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:first-timer?period=1000")
                .to("log:first-timer");
    }
}
