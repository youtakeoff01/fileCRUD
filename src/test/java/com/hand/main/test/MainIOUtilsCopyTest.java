package com.hand.main.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class MainIOUtilsCopyTest {
	public static void main(String[] args) {
		downloadDirector();
	}
	
	private static void downloadDirector(){
		File fileIn = new File("D:\\Introduction_to_Algorithms_3rd_Edition.pdf");
		File fileOut = new File("D:\\Introduction_to_Algorithms_3rd_Edition_xxx.pdf");
		if (fileIn != null) {
			OutputStream out = null;
			InputStream is = null;
			try {
//				out = new FileOutputStream(fileOut);
				is = new FileInputStream(fileIn);
				IOUtils.copy(is, out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(is!=null){
						is.close();
					}
					fileIn.delete();
//					if(out!=null){
//						out.close();
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
