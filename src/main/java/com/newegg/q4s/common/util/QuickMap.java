package com.newegg.q4s.common.util;
/**
 * QMap.java	1.0 2009-5-30 上午09:44:51,创建
 */


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于做各种参数时快速生成的map
 * 
 * <pre>
 * QMap map = new QMap(&quot;username&quot;, &quot;aaa&quot;, &quot;parameter2&quot;, &quot;bbb&quot;);
 * map.qadd(&quot;aaa&quot;, &quot;bbb&quot;);
 * map.qadd(&quot;key1&quot;, &quot;value1&quot;, &quot;key2&quot;, &quot;value2&quot;);
 * String params = map.getAsQueryString(&quot;utf-8&quot;);
 * System.out.println(params);
 * </pre>
 * 
 * @author Kenny
 */
public class QuickMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1L;

	public QuickMap() {
		super();
	}

	public QuickMap(Map<K, V> map) {
		super(map);
	}

	public QuickMap(Object... args) {
		super(args.length / 2 + 1);
		this.qadd(args);
	}

	public QuickMap qadd(Object... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("The arguments count must be even, current arguments length is "
					+ args.length);
		}
		for (int i = 0; i < args.length; i += 2) {
			this.put((K) args[i], (V) args[i + 1]);
		}
		return this;
	}

	public String getAsQueryString(String encoding) {
		StringBuilder sb = new StringBuilder(50);
		V value = null;
		for (Map.Entry<K, V> entry : this.entrySet()) {
			value = this.get(entry.getKey());
			if (value != null)
				appendValue(sb, String.valueOf(entry.getKey()), value, encoding);
		}
		String s = sb.toString();
		if (s.startsWith("&"))
			s = s.substring(s.indexOf('&') + 1);
		return s;
	}

	private void appendValue(StringBuilder sb, String key, Object value, String encoding) {
		String valueToUse = String.valueOf(value);
		try {
			valueToUse = URLEncoder.encode(valueToUse, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sb.append("&").append(key).append("=").append(valueToUse);
	}

	/**
	 * 创建一个新的HashMap
	 * 
	 * @param args
	 * @return
	 */
	public Map<K, V> buildHashMap(Object... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("The arguments count must be even, current arguments length is "
					+ args.length);
		}
		Map<K, V> map = null;
		if (args != null && args.length > 0) {
			map = new HashMap<K, V>();
			for (int i = 0; i < args.length; i += 2) {
				map.put((K) args[i], (V) args[i + 1]);
			}
		}
		return map;
	}

	public static void main(String[] args) {
		QuickMap map = new QuickMap("username", "aaa", "parameter2", "bbb");
		map.qadd("aaa", "bbb");
		map.qadd("key1", "value1", "key2", "value2");
		String params = map.getAsQueryString("utf-8");
		System.out.println(params);
	}
}
