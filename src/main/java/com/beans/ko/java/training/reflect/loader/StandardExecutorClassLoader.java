package com.beans.ko.java.training.reflect.loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class StandardExecutorClassLoader extends URLClassLoader{

	private final static String baseDir = System.getProperty("user.dir") + File.separator + "ext" + File.separator;
	
	public StandardExecutorClassLoader(String version) {
		//将parent设置为null
		super(new URL[]{},null); 
		loadResource(version);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println("Class loader:" + name);
		return super.findClass(name);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			return super.loadClass(name);
		} catch (ClassNotFoundException e) {
			return StandardExecutorClassLoader.class.getClassLoader().loadClass(name);
		}
	}

	private void loadResource(String version) {
		String jarPath = baseDir + version;
		//加载对应版本目录下的jar包
		tryLoadJarInDir(jarPath);
		//加载对应版本目录下的lib目录下的jar包
		tryLoadJarInDir(jarPath + File.separator + "lib");
	}
	
	private void tryLoadJarInDir(String dirPath) {
		File dir = new File(dirPath);
		//自动加载目录下的jar包
		if(dir.exists() && dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				if(file.isFile() && file.getName().endsWith(".jar")) {
					this.addURL(file);
					continue;
				}
			}
		}
	}
	private void addURL(File file) {
		try {
			super.addURL(new URL("file",null,file.getCanonicalPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
