package com.beans.ko.java.quartz.demo2;


import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTriggerExample {
	
	Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);
	
	public void run() throws SchedulerException {
		log.info("------- Initializing -------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		log.info("------- Initialization Complete --------");
		log.info("------- Scheduling Jobs ----------------");
		Date startTime = DateBuilder.nextGivenMinuteDate(null, 10);
		JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
		SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
		
		Date ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
	             + trigger.getRepeatInterval() / 1000 + " seconds");
	}
	
	public static void main(String[] args) throws SchedulerException {
		SimpleTriggerExample simple = new SimpleTriggerExample();
		simple.run();
	}

}
