package com.hand.text;


import java.io.ByteArrayInputStream;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alerotech.apis.altConnectBase;
import com.alerotech.apis.altConnectData;
import com.alerotech.apis.alt.altUsrElement;
import com.alerotech.apis.alt.altUsrVersionElement;

public class AleroFileUtils {
	/**
	 * 文件的上传，并添加到版本库
	 */
	public static int uploadFile(String fileName,String filePath,altConnectBase con){
		altUsrElement element = new altUsrElement(con);
		element.m_cClassId = "";
		element.m_archive = "MAIN";
		element.setInfo(fileName, "PUBLIC", "TEXT", filePath);
		int code = element.create("ALERO_MAIN");
		//将新创建的元素添加到版本控制
		altUsrVersionElement version = new altUsrVersionElement(con);
		version.addRevisionControl(element.m_elementId,"1","0","the first");
		if(code !=0){
			System.out.println(element.getLastError());
		}else{
			System.out.println(element.m_elementId);
			System.out.println("success");
			int code2 = element.insertContainer("ALERO_MAIN::201703221335361::FOLDER");
			System.out.println(code2+"---"+element.getLastError());
		}
		return code;
	}
	
	/**
	 * 文件的签出
	 * return File
	 */
	public static File checkOut(altConnectBase con,String selectVersion,String elementId) throws Exception{
		int code = 11;
		altUsrVersionElement versionElement = new altUsrVersionElement(con);
		altUsrElement element = new altUsrElement(con);
		//该文件目前的最高版本
		versionElement.retrieveRevisionTree(elementId);
	//	versionElement.m_currentRev = "0";
	//	versionElement.m_currentVer = "1";
		//要检出文件的id
	//	versionElement.m_elementId = elementId;
		//versionElement.m_op = "2";
		//  versionElement.m_status = "1";
		 //签出的文件名称例如 ***。txt
		element.retrieveProps(elementId, true, true);
		//  element.m_descr = "firstDemo.txt";
		//  element.m_eClassId = "TEXT";
		 //要检出文件的id
		//  element.m_elementId = elementId;
		//element.m_op = "4";
		//  element.m_userSClass = "PUBLIC";
		 //默认检出的版本是当前最高版本。
		if(!StringUtils.isNotBlank(selectVersion)){
			selectVersion = versionElement.m_currentVer+"."+versionElement.m_currentRev;
		}
		  //如果有检出描述的话则执行下面的代码,如果没有签出描述，则不执行下面的代码
		  int resultCode = versionElement.checkOut("", versionElement.m_currentVer, "jdkdjk", 0, false);
		  
		  int rCode = versionElement.retrieveRevisionTree(versionElement.m_elementId);
		  File tempFile = null;
		  if (rCode != 0) {
				System.err.println("获取版本树出错");
			} else {
				// Eid sent by click action over breadcrumbs
				String versionIdCode = null;
				String revisionIdCode = null;

				// 判断是否为 选择的版本
				if (selectVersion != null) {
					String[] arr = selectVersion.split("\\.");
					versionIdCode = arr[0];
					revisionIdCode = arr[1];
				}

		  		String eid = versionElement.getCompId(versionIdCode, revisionIdCode);
				
				ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				HttpSession session = attrs.getRequest().getSession();
				
				ServletContext sc = session.getServletContext();
				String path = sc.getInitParameter("TEMPFILEPATH");
				
				String abultePath = sc.getRealPath(path);
				
				tempFile = File.createTempFile("xtorm", ".tmp", new File(abultePath));
				
				int code01 = element.getContent(tempFile.getAbsolutePath());
			} 
		  return tempFile;
	}
	
	/**
	 * 文件的签入
	 * return code  if code is 0,success
	 */
	public static int checkIn(ByteArrayInputStream fileData1,altConnectBase con,String elementId){
		int code = 11;
		altUsrVersionElement alt = new altUsrVersionElement(con);
		alt.retrieveStatus(elementId);
		//alt.m_checkedUser = "SUPER";
		//当前的版本
        String ver = alt.m_currentVer;
        //需要签入的版本
        String newVer = Integer.parseInt(ver)+1+"";
        String rev = "";
        if(Integer.parseInt(alt.m_currentRev) == 0){
        	rev = "0";
        }else{
        	rev = Integer.parseInt(alt.m_currentRev)+1+"";
        }
        String comment = "qianru111111111111";
        ByteArrayInputStream fileData = fileData1;
        String cClass = "";
        String archive = "MAIN";
        boolean keepChecked = true;
	    code = alt.checkIn(elementId, ver, newVer, rev, comment, fileData, cClass, archive, keepChecked);
	    //改变版本信息。
	    alt.changeCurrent(elementId,newVer,rev, comment);
		return code;
	}
	/**
	 * 取消签出
	 * @return
	 */
	public static int unCheckOut(altConnectBase con,String elementId){
		int code = 11;
		altUsrVersionElement alt = new altUsrVersionElement(con);
		alt.retrieveStatus(elementId);
		code = alt.unCheckOut(elementId, alt.m_currentVer, "取消签出", true);
		return code;
		
	}
	/**
	 * 删除元素
	 */
	public static int deleteElement(altConnectBase con,String elementId){
		int code = 11;
		altUsrElement element = new altUsrElement(con);
		
	    code = element.remove(elementId);
		return code;
	}
	
	
	/**
	 * 获取连接对象
	 */
	public static altConnectBase getConnect(){
		int code = 11;
		String server = "localhost";
		int port = 2102;
		String client = "alero sdk test";
		String user = "SUPER"; 
		String pswd = "SUPER";
		altConnectBase con = new altConnectData(server, port, client, user, pswd);
		return con;
	}
	/**
	 * 判断当前操作的元素的当前的签入签出状态
	 * return  true  表示元素是签出状态
	 * return false  表示元素的签入状态
	 * @param con
	 * @param elementId
	 * @return
	 */
	public static boolean judgeEleState(altConnectBase con,String elementId){
		altUsrVersionElement version = new altUsrVersionElement(con);
		int code = version.retrieveStatus(elementId);
		if(StringUtils.isNotBlank(version.m_checkedUser)){
			return true;
		}
		return false;
	}
}
