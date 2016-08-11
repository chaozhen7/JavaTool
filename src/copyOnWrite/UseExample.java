package copyOnWrite;

import java.util.Map;

public class UseExample {
	private static CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<String, Boolean>(
			1000);	//最好初始化容量，避免扩容影响效率

	public static boolean isBlackList(String id) {
		return blackListMap.get(id) == null ? false : true;
	}

	public static void addBlackList(String id) {
		blackListMap.put(id, Boolean.TRUE);
	}

	/**
	 * 批量添加黑名单
	 * 
	 * @param ids
	 */
	public static void addBlackList(Map<String, Boolean> ids) {
		blackListMap.putAll(ids);
	}
}
