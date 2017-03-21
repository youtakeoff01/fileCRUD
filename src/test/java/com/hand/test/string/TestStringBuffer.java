package com.hand.test.string;


public class TestStringBuffer{
	public static void main(String[] args) {
		String str1 = "haha1";
		StringBuffer bs1 = new StringBuffer("hehe1");
		if (!"".equals(str1.trim())) {
			if (str1.trim().endsWith("union")) {
				str1 = str1.substring(0, str1.lastIndexOf("union "));
			}
		}
	}
	
}