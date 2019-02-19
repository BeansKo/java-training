package com.beans.ko.java.training.script;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Nashorn是在java中高效动态执行JS代码的运行环境。
 * @author Frank
 *
 */
public class ScriptEngineUtil {

	public static void main(String[] args) throws Exception {
		String script = "print('hello')";
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		engine.eval(script);
		System.out.println("----------------------------");
		File file = new File(System.getProperty("user.dir")+"/script/script.js");
		engine.eval(new FileReader(file));
		Invocable invocable = (Invocable) engine;
		Object sum = invocable.invokeFunction("add", 1, 2);
        System.out.println(sum);
	}

}
