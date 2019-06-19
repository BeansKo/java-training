package com.beans.ko.java.quartz.demo2;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job{

	private static Logger log = LoggerFactory.getLogger(SimpleJob.class);
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
	}
}
