package com.beans.ko.java.training.log;

import org.apache.log4j.Logger;

/**
 * Log4j的使用
 * 五个级别：
 * 	FATAL 致命的错误
 *  ERROR 错误（指的是错误，但是不致命的哪种）
 *  WARN  警告
 *  INFO  粗粒度信息事件
 *  DEBUG 细粒度信息事件
 * @author fl76
 *
 */
public class LogOne {

	//注册日志组件
	static Logger logger = Logger.getLogger(LogOne.class);
	static Logger extProfile  = Logger.getLogger("extProfile");
	public static void main(String[] args) {
		logger.info("test");
		extProfile.info("myself");
	}

}
