package com.beans.ko.java.training.innerclass;

//外部类能否直接访问内部类成员？ 不能直接访问
//内部类能否直接访问外部类成员？ 可以直接访问，即使是private修饰的也可以访问
//如何使用外部类访问内部类？创建内部类的对象就可以，且没有权限限制，private也可以访问
//如何构建内部类的对象？内部类是依附外部类的对象存在。内部类可以作为外部类的成员，是依附于对象存在的。一个类中的属性和方式是依附于对象，静态的成员依附于类。
//步骤一：必须先构建外部类对象  步骤二：使用外部类的对象来构建内部类的对象  步骤三:得到对象就可以访问了
//得到内部类的对象，外部类和不是外部类的类访问起来有和区别？对于外部类而言，权限没有限制，即便是parivate的也可以访问；对于不是外部类的类而言，要遵守权限的限制
//内部类能不能有静态的成员存在？不能，静态内部类除外
//内部类可不可以继承其他类？可以，内部类的出现才是真正解决了java单继承的缺陷，实现了多继承
public class OuterClassOne {//外部类
	
	public class InnerClassOne extends ParentClass{//成员内部类
		
		public String innerName;
		private int innerAge;
		
		public InnerClassOne(){}
		public InnerClassOne(String innerName, int innerAage) {
			super();
			this.innerName = innerName;
			this.innerAge = innerAage;
		}
		
		public String getInnerName() {
			return innerName;
		}
		public void setInnerName(String innerName) {
			this.innerName = innerName;
		}
		public int getInnerAge() {
			return innerAge;
		}
		public void setInnerAge(int innerAgeage) {
			this.innerAge = innerAgeage;
		}
		
		@Override
		public String toString() {
			return "InnerClassOne [innerName=" + innerName + ", innerAge=" + innerAge+ "]";
		}
		
		public void showInner(){
			showOuter();
			getName();
			System.out.print("showInner()");
		}
	}

	public String name;
	private int age;
	public OuterClassOne() {
		super();
	}
	public OuterClassOne(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		System.out.println("OuterClass.toString()");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void showOuter(){
		getName();
		toString();
	}
	
	@Override
	public String toString() {
		System.out.println("OuterClass.toString()");
		return "OuterClassOne [name=" + name + ", age=" + age + "]";
	}
	
	public static void main(String[] args){
		OuterClassOne outer = new OuterClassOne();
		outer.showOuter();
		
		//利用外部类构建内部类对象
		InnerClassOne inner = outer.new InnerClassOne();
		
		inner.setInnerName("name");
	}
}
