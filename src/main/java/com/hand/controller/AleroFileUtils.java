package com.hand.controller;

import com.alerotech.apis.altConnectBase;
import com.alerotech.apis.altConnectData;
import com.alerotech.apis.alt.altUsrElement;

public class AleroFileUtils {
	public static void main(String[] args) {
		String server = "localhost";
		int port = 2102;
		String client = "alero sdk test";
		String user = "SUPER"; 
		String pswd = "SUPER";
		altConnectBase con = new altConnectData(server, port, client, user, pswd);
		altUsrElement element = new altUsrElement(con);
//		element.m_cClassId = "";
		element.m_archive = "MAIN";
		element.setInfo("testfile.txt", "PUBLIC", "TEXT", "C:\\Users\\chenjing\\Desktop\\合同优化.txt");
		int code = element.create("ALERO_MAIN");
		if(code !=0){
			System.out.println(element.getLastError());
		}else{
			System.out.println(element.m_elementId);
			System.out.println("success");
//			int code2 = element.insertContainer("ALERO_MAIN::201703221154341::FOLDER");
//			System.out.println(code2+"---"+element.getLastError());
			System.out.println("over");
		}
	}
}
