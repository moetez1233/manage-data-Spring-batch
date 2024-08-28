package exportDataExcel.dataExcel_api.repositories;

import exportDataExcel.dataExcel_api.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
