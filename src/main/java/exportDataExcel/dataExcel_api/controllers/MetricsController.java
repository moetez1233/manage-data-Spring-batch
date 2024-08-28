package exportDataExcel.dataExcel_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/metric/v1")
public class MetricsController {

    @GetMapping
    public ResponseEntity<String> getMetrics() {
        return new ResponseEntity<>("metrics Ok", HttpStatus.OK);
    }
}
