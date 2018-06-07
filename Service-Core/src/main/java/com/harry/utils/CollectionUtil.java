package com.harry.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtil {

	private CollectionUtil() {
		super();
	}

	// 判断一个集合是否为空
	public static <T> boolean isEmpty(Collection<T> col) {

		return (col == null || col.isEmpty());
	}

	// 判断一个集合是否不为空
	public static <T> boolean isNotEmpty(Collection<T> col) {
		return !isEmpty(col);
	}

	// 判断Map是否为空
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return (map == null || map.isEmpty());
	}

	// 判断Map是否不为空为空
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	// 去除list中的重复数据
	public static <T> List<T> removeRepeat(List<T> list) {
		if (isEmpty(list)) {
			return list;
		}

		List<T> result = new ArrayList<T>();
		for (T e : list) {
			if (!result.contains(e)) {
				result.add(e);
			}
		}

		return result;
	}

	// 将集合转换为String数组
	public static <T> String[] toArray(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}

		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = String.valueOf(list.get(i));
		}

		return result;
	}

}
