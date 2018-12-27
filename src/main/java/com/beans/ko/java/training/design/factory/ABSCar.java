package com.beans.ko.java.training.design.factory;

public class ABSCar {
	protected int id;
	protected String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ABSCar [id=" + id + ", name=" + name + "]";
	}
}
