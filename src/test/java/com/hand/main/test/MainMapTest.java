package com.hand.main.test;

import java.util.HashMap;
import java.util.Map;

public class MainMapTest {
	public static void main(String[] args) {
		Map map=new HashMap();
		map.put("abc", null);
		map = null;
		String str=(String) map.get("gfh");
		System.out.println(str);
	}

}
