package com.beans.ko.java.quartz.demo1;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExample {

	public static void main(String[] args) throws SchedulerException {
		SimpleExample example = new SimpleExample();
		example.run();
	}

	public void run() throws SchedulerException {
		Logger log = LoggerFactory.getLogger(SimpleExample.class);
		log.info("------- Initializing ----------------------");

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");

		//当前日期的下一分钟
		Date runTime = DateBuilder.evenMinuteDate(new Date());
		log.info("------- Scheduling Job  -------------------");

		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1").build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1").startAt(runTime).build();

		sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + runTime);

		sched.start();

		log.info("------- Started Scheduler -----------------");
		log.info("------- Waiting 65 seconds... -------------");
		try {
			// wait 65 seconds to show job
			Thread.sleep(65L * 1000L);
			// executing...
		} catch (Exception e) {
			//
		}
		// shut down the scheduler
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
	}

}
