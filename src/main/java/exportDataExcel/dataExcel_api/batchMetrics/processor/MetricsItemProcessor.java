package exportDataExcel.dataExcel_api.batchMetrics.processor;

import exportDataExcel.dataExcel_api.dto.MetricDto;
import exportDataExcel.dataExcel_api.mapper.MetricMapper;
import exportDataExcel.dataExcel_api.models.Metric;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricsItemProcessor implements ItemProcessor<MetricDto, Metric> {
    private final MetricMapper metricMapper;

    public MetricsItemProcessor(MetricMapper metricMapper) {
        this.metricMapper = metricMapper;
    }

    @Override
    public Metric process(MetricDto m) throws Exception {
        return m.getTypeMetric() != null ?metricMapper.toMetric(m) : null;
    }
}
