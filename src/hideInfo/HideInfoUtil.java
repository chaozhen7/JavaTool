package hideInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年9月22日 上午10:59:55
 * @version 1.0
 * @deprecated 隐藏信息 例如手机号、email等信息
 */
public class HideInfoUtil {
	
	/**
	 *@param info 输入信息
	 *@param left 左边保留位数
	 *@param right 右边保留位数
	 *@param basedOnLeft 位数不够时保留左边还是右边
	 * 
	 */
	public static String hideInfo(String info, int left, int right,
			boolean basedOnLeft) {
		if (info == null || info.equals("") || info == "" || info.isEmpty()) {
			return "";
		}
		StringBuilder sbResult = new StringBuilder();
		int hideCount = info.length() - left - right;
		if (hideCount > 0) { // 位数够
			String preStr = info.substring(0, left);
			String endStr = info.substring(info.length() - right);
			sbResult.append(preStr);
			for (int i = 0; i < hideCount; i++) {
				sbResult.append("*");
			}
			sbResult.append(endStr);
		} else { // 位数不够
			if (basedOnLeft) { // 显示左边
				if (info.length() > left && left > 0) {
					sbResult.append(info.substring(0, left) + "****");
				} else {
					sbResult.append(info.substring(0, 1) + "****");
				}
			} else { // 显示右边
				if (info.length() > right && right > 0) {
					sbResult.append("****"
							+ info.substring(info.length() - right));
				} else {
					sbResult.append("****" + info.substring(info.length() - 1));
				}
			}
		}

		return sbResult.toString();
	}

	
	/**
	 * 隐藏手机号
	 * 中间4位隐藏
	 */
	public static String hidePhone(String phoneNum) {
		return hideInfo(phoneNum, 3, 4, true);
	}

	// 
	/**
	 * 隐藏邮箱
	 * 首位＊＊＊@xxx
	 */
	public static String hideEmail(String email) {
		if (email == null || email.isEmpty() || email == "" || email.equals("")) {
			return "";
		}
		if (checkEmail(email)) {
			return hideInfo(email, 1, email.length() - email.indexOf("@"),
					false);
		}
		return email;
	}

	/**
	 * 邮件检测正则
	 * */
	private static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
