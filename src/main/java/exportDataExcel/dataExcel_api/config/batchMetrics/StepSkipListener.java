package exportDataExcel.dataExcel_api.config.batchMetrics;


import exportDataExcel.dataExcel_api.models.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.annotation.OnSkipInRead;

public class StepSkipListener implements SkipListener<Metric,Number> {
    Logger logger= LoggerFactory.getLogger(StepSkipListener.class);
    @OnSkipInRead
    public void onSkipInRead(Throwable t) {
        logger.info("Item skipped during reading due to: " + t.getMessage());
        throw new RuntimeException("Item skipped during reading due to: " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(Metric item, Throwable t) {
        logger.info(("Item skipped during processing: " + item.toString() + ", due to: " + t.getMessage()));
    }

    
    public void onSkipInWrite(Metric item, Throwable t) {
        logger.info(("Item skipped during writing: " + item.toString() + ", due to: " + t.getMessage()));
    }
}
