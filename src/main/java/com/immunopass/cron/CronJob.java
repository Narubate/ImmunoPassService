package com.immunopass.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.immunopass.service.OrderService;


@Component
@ConditionalOnProperty(value = "cronInstance", havingValue = "true", matchIfMissing = false)
public class CronJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronJob.class);

    private final OrderService orderService;

    @Value("${cronInstance}")
    private boolean isCronInstance;

    public CronJob(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void cronJobSch() {
        if (!isCronInstance) {
            LOGGER.debug("Not a cron instance");
            return;
        }
        LOGGER.debug("Running the cron...");
        LOGGER.debug("Creating vouchers...");
        orderService.createVouchers();
        LOGGER.debug("Processing orders...");
        orderService.processOrders();
    }
}
