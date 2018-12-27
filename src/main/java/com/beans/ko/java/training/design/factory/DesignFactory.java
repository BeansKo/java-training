package com.beans.ko.java.training.design.factory;

/**
 * 工厂方法
 * 适用场景：
 * 所有的产品子类都有同一个父类（接口），属于同一个产品系列
 * 产品子类比较多，创建操作比较复杂。
 * 优点：
 * 客户端不负责对象的创建，而是由专门的工厂类完成
 * 客户端负责对象的调用，实现了创建和调用的分离，降低了客户端代码的难度
 * 若增加和减少产品的子类，不修改工厂类，只需增加产品子类和工厂子类，符合开闭原则
 * 即使产品子类过多，不会导致工厂类的庞大，利于后期维护
 *
 */
public class DesignFactory {

	public static void main(String[] args) {

	}

}
