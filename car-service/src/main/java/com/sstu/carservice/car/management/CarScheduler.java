package com.sstu.carservice.car.management;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
public class CarScheduler implements Job {

    CarManager carManager = new CarManager();

    @Getter
    JobDetail job = JobBuilder.newJob(CarScheduler.class)
            .withIdentity("carHealthChecker", "carService")
            .build();

    @Getter
    Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("healthCheckTrigger", "carService")
            .startNow()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever())
            .forJob(job)
            .build();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Scheduled task was executed");
        carManager.checkCarsHealth();
    }
}
