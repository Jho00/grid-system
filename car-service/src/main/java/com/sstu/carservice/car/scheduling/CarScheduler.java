package com.sstu.carservice.car.scheduling;

import com.sstu.carservice.appconfig.ApplicationConfig;
import com.sstu.carservice.appconfig.ConfigModel;

import com.sstu.carservice.car.management.CarManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.quartz.*;

@Slf4j
public class CarScheduler implements Job {

    CarManager carManager = new CarManager();
    ConfigModel configModel = ApplicationConfig.getConfig();

    @Getter
    JobDetail job = JobBuilder.newJob(CarScheduler.class)
            .withIdentity("carHealthChecker", "carService")
            .build();

    @Getter
    Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("healthCheckTrigger", "carService")
            .startNow()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(configModel.getSchedulerInterval()))
                    .repeatForever())
            .forJob(job)
            .build();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Scheduled task was executed");
        carManager.checkCarsHealth();
    }
}
