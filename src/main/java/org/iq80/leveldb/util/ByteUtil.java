package org.iq80.leveldb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteUtil {
	static Charset CHARSET = Charset.forName("UTF-8");

	public static String byteArrayToString(byte[] b) {
		if(b != null){
			return new String(b, CHARSET);
		}
		return null;
	}

	public static byte[] stringToByteArray(String s) {
		if(s == null){ return null; }
		return s.getBytes(CHARSET);
	}

	public static byte[] longToByte(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位
			temp = temp >> 8;// 向右移8位
		}
		return b;
	}

	// byte数组转成long
	public static long byteToLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 最低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 最低位
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;
		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	public static byte[] booleanToByte(boolean b) {
		return b ? new byte[] { 0x01 } : new byte[] { 0x00 };
	}

	public static boolean byteToBoolean(byte b) {
		return b == (byte) 0x01;
	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}

	public static byte[] merge(final byte[] array1, final byte... array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		final byte[] joinedArray = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static byte[] clone(final byte[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	public static byte[] merges(byte[]... bs) {
		byte[] result = bs[0];
		for (int i = 1; i < bs.length; i++) {
			byte[] b = bs[i];
			byte[] tmp = new byte[result.length + b.length];
			System.arraycopy(result, 0, tmp, 0, result.length);
			System.arraycopy(b, 0, tmp, result.length, b.length);
			result = tmp;
		}
		return result;
	}

	public static byte[] append(byte[] bs, byte b) {
		byte[] tmp = new byte[bs.length + 1];
		System.arraycopy(bs, 0, tmp, 0, bs.length);
		tmp[tmp.length - 1] = b;
		return tmp;
	}

	public static byte[] sub(byte[] src, int begin, int count) {
		return Arrays.copyOfRange(src, begin, begin + count);
	}

	public static byte[] sub(byte[] src, int begin) {
		return Arrays.copyOfRange(src, begin, src.length);
	}

	public static int compareTo(byte[] a, byte[] b) {
		if (a.length == b.length) {
			return new String(a).compareTo(new String(b));
		} else {
			return a.length > b.length ? 1 : -1;
		}
	}

	public static byte[] toBytes(Object datas) {
		try {
			try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
				try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
					oos.writeObject(datas);
					return os.toByteArray();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(byte[] dataBytes) {
		Object obj = null;      
        try {
        	try(ByteArrayInputStream bis = new ByteArrayInputStream (dataBytes)){
        		try(ObjectInputStream ois = new ObjectInputStream (bis)){
        			obj = ois.readObject();
        		}
        	}
        } catch (Exception e) {        
            e.printStackTrace();   
        }   
        return (T) obj;
	}
}
