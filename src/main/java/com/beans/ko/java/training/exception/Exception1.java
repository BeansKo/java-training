package com.beans.ko.java.training.exception;

/**
 * JAVA中所有异常都继承与Throwable
 * Throwable主要包含两个大类：Error和Exception
 * Error类中包含虚拟机错误VirtralMachineError和线程死锁ThreadDeath，一旦Error出现了，程序就挂了,被称为程序终结者。
 * Exception类，就是通常所说的异常。主要包含两大类RuntimeException和检查异常（IOException,SQLException）
 * RuntimeException，由JAVA虚拟机自动抛出并自动捕获（就算我们没有写异常捕获语句运行时也会抛出错误）.
 * 	包括NullPointerException,ArrayIndexOutOfBoundsException,ClassCastException,ArithmeticException
 * 检查异常，该异常我们必须要手动在代码里添加捕获语句来处理异常
 * @author fl76
 *
 */
public class Exception1 {

}
