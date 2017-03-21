package com.hand.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTestDate {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateLastYear = sdf.parse("2016-06-06 23:23:23");
		Date dateNow = new Date();
		// before
		System.out.println("before结果是："+dateNow.before(dateLastYear));
		System.out.println("测试dateNow:"+sdf.format(dateNow)+"日期是否在dateLastYear:"+sdf.format(dateLastYear)+"日期之前。"); 
		System.out.println("返回：当且仅当此 Date 对象表示的瞬间比 when 表示的瞬间早，才返回 true；否则返回 false。");
		System.out.println();
		// after
		System.out.println("after结果是："+dateNow.after(dateLastYear));
		System.out.println("测试dateNow:"+sdf.format(dateNow)+"日期是否在dateLastYear:"+sdf.format(dateLastYear)+"日期之后。"); 
		System.out.println("返回：当且仅当此 Date 对象表示的瞬间比 when 表示的瞬间晚，才返回 true；否则返回 false。");
	}

}
