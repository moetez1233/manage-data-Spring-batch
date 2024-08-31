package exportDataExcel.dataExcel_api.mapper;

import exportDataExcel.dataExcel_api.dto.MetricDto;
import exportDataExcel.dataExcel_api.models.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper {
    MetricDto toDto(Metric metric);
    Metric toMetric(MetricDto metricDto);
    List<Metric> toList(List<MetricDto> metricDtoList);
    List<MetricDto> toDtoList(List<Metric> metricList);
}
