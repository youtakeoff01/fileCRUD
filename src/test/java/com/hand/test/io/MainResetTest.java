package com.hand.test.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainResetTest {
	public void read(InputStream in) throws IOException {
		if(in == null) {
			return;
		}
		int len=0;
		in.mark(1);
		in.read(); // 第一次读取
		in.reset(); // 又可以重新读取
		in.read(); // 第二次读取跟第一次读取结果一样。因为只读了一个，没有超过mark设置的整数。所以mark有效
	}

	public static void main(String[] args) throws IOException {
		MainResetTest test = new MainResetTest();
		String fileName = "D:/temp/Google.txt";
		InputStream in1 = new FileInputStream(new File(fileName));
		if (!in1.markSupported()) {
			in1 = new BufferedInputStream(in1);
		}
		test.read(in1);
		byte[] buf = new byte[100];
		while (in1.read(buf, 0, buf.length) != -1) {
			System.out.println(buf);
		}
		System.out.println("Success!");
		
//		OutputStream out = new OutputStream();
//		out.write(4);
	}
}

