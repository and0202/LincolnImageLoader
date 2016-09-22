package lincoln.imageframework.lincoln.imageloader.util;


public class StringUtil {

	/**
	 * 判断字符串是否可用
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isUseable(String content) {
		boolean result = false;
		if (content != null) {
			result = !content.equals("") && !content.equals("null");
		}
		return result;
	}

	/**
	 * 比较两个字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isTheSame(String str1, String str2) {
		boolean result = false;
		if (isUseable(str1) && isUseable(str2) && str1.equals(str2)) {
			result = true;
		}
		return result;
	}



}
