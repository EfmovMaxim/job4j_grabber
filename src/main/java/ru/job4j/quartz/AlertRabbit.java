package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;


public class AlertRabbit {
    public static Properties readProperties() {
        Properties cfg = new Properties();

        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            cfg.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cfg;
    }

    public static Connection initConnection(Properties properties) {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {

            Class.forName(properties.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static void main(String[] args) {
        Properties properties = readProperties();

        if (properties.getProperty("rabbit.interval") == null) {
            System.out.println("Incorrect interval");
            return;
        }

        try (Connection connection = initConnection(properties)) {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            store.add(System.currentTimeMillis());
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");

            try (PreparedStatement statement = cn.prepareStatement("insert into rabbit (created_data) values(current_timestamp)")) {
                statement.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

