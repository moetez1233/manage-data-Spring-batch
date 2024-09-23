package exportDataExcel.dataExcel_api.config.batchMetrics.processor;

import exportDataExcel.dataExcel_api.dto.MetricDto;
import exportDataExcel.dataExcel_api.mapper.MetricMapper;
import exportDataExcel.dataExcel_api.models.Metric;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MetricsItemProcessor implements ItemProcessor<MetricDto, Metric> {
    private final MetricMapper metricMapper;
    List<String> names= new ArrayList<>();

    public MetricsItemProcessor(MetricMapper metricMapper) {
        this.metricMapper = metricMapper;
    }

    @Override
    public Metric process(MetricDto m) throws Exception {
        if(names.contains(m.getNameUsine())){
            return null;
        }else{
            names.add(m.getNameUsine());
            return metricMapper.toMetric(m);
        }

    }
}
