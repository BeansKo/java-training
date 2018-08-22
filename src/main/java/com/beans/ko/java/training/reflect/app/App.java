package com.beans.ko.java.training.reflect.app;

/**
 * 反射是什么？
 * 	反射是一门动态创建对象的技术。反射是指在程序运行期间可以访问对象，可以动态修改对象的值
 * java的缺陷？
 * 	java是静态的，所有对象都是预先创建好的，都是写死的，不够动态。
 * 放射的作用？
 * 	可以动态的创建对象，使的java变成半动态语言。
 * 反射的缺陷？
 * 	操作麻烦
 * 	效率低
 * 
 * 1.如果项目完成之后，需要改成微信支付，应该怎么办？
 * 	修改源码，改成微信支付
 * 	问题1
 * 	   我们开发的项目不能经常去停，我们要保证项目长久运行
 *   每次修改源码，需要重新编译，服务就会停
 *   我们要尽量保证源码不被修改
 *  问题2
 *    我们是实现微信支付宝支付的奇幻，而不需要修改源码
 *    增加类不算修改源码，增加类似的jar包的形式传到服务器就可以了，服务是不需要停的
 *    
 * 3.此时服务上线，用的微信支付改正支付宝支付，应该怎么写代码比较合适？
 *    使用多态
 *    使用io流进行创建对象的判断
 */
public class App {
	private static String name="wx";
	private static String className="com.beans.ko.java.training.reflect.app.ZFZFB";
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ZF zf= null;
		//多态方式
		if(name.equals("wx")){
			zf= new ZFWS();
		} else if(name.equals("zfb")){
			zf = new ZFZFB();
		}
		zf.zf();
		
		//使用反射代替
		Class clazz = Class.forName(className);
		
		Object obj = clazz.newInstance();
		zf = (ZF)obj;
		zf.zf();
	}
}

class ZFWS implements ZF{
	@Override
	public void zf() {
		TestWX wx = new TestWX();
		wx.vx();
	}
	
}

class ZFZFB implements ZF{
	@Override
	public void zf() {
		TestZFB zfb = new TestZFB();
		zfb.zfb();
	}
	
}

class TestZFB implements ZFB{
	@Override
	public void zfb() {
		System.out.println("使用支付宝支付！");
	}
	
}

class TestWX implements WX{

	@Override
	public void vx() {
		System.out.println("微信支付");
	}
}
