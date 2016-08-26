package random;

import java.util.Random;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年8月26日 上午10:58:42
 * @version 1.0
 */
public class RandomPassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(randomNumPwd(8));
		System.out.println(randomPassword(8));
	}

	/**
	 * 生成随机的密码 纯数字
	 **/
	public static String randomNumPwd(int size) {
		char[] chars = new char[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			chars[i] = (char) ('0' + random.nextInt(10));
		}
		return new String(chars);
	}

	/**
	 * 生成复杂密码含大小写字母、数字、特殊字符
	 **/
	public static String randomPassword(int size) {
		char[] chars = new char[size];
		Random rnd = new Random();

		chars[nextIndex(chars, rnd)] = nextSpecialChar(rnd);
		chars[nextIndex(chars, rnd)] = nextUpperlLetter(rnd);
		chars[nextIndex(chars, rnd)] = nextLowerLetter(rnd);
		chars[nextIndex(chars, rnd)] = nextNumLetter(rnd);

		for (int i = 0; i < size; i++) {
			if (chars[i] == 0) {
				chars[i] = nextChar(rnd);
			}
		}
		return new String(chars);
	}

	private static int nextIndex(char[] chars, Random rnd) {
		int index = rnd.nextInt(chars.length);
		while (chars[index] != 0) {
			index = rnd.nextInt(chars.length);
		}
		return index;
	}

	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

	private static char nextChar(Random rnd) {
		switch (rnd.nextInt(4)) {
		case 0:
			return (char) ('a' + rnd.nextInt(26));
		case 1:
			return (char) ('A' + rnd.nextInt(26));
		case 2:
			return (char) ('0' + rnd.nextInt(10));
		default:
			return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
		}
	}

	private static char nextSpecialChar(Random rnd) {
		return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
	}

	private static char nextUpperlLetter(Random rnd) {
		return (char) ('A' + rnd.nextInt(26));
	}

	private static char nextLowerLetter(Random rnd) {
		return (char) ('a' + rnd.nextInt(26));
	}

	private static char nextNumLetter(Random rnd) {
		return (char) ('0' + rnd.nextInt(10));
	}

}
