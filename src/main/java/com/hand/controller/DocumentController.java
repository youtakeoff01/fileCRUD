package com.hand.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hand.utils.AleroFileUtils;
import com.hand.utils.ContractUtils;

import net.sf.json.JSONObject;
@Controller
public class DocumentController {
	
	public static String ELEMENTID = "";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, HttpServletRequest request, Model model) {
		session.invalidate();
		return "uploadFile";
	}
	
	
	/**
	 * 文件上传。并添加版本控制
	 * @param request  
	 * @return
	 */
	@RequestMapping(value="uploadFile", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(HttpServletRequest request){
		File tempFile=null;
		String fileName="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> getFiles = multipartRequest.getFiles("file");// 获取上传的文件
		JSONObject json = new JSONObject();
		int code = 11;
		for (MultipartFile multipartFile : getFiles) {
			 try {
				 
				tempFile = ContractUtils.getFile(multipartFile);
				fileName = multipartFile.getOriginalFilename();
				//上传文件
				code = AleroFileUtils.uploadFile(fileName, tempFile.getPath(),AleroFileUtils.getConnect());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(code != 0){
			json.put("success", false);
			json.put("msg", "上传文件失败");
			return json.toString();
		}else{
			json.put("success", true);
			json.put("msg","文件上传成功，并添加了版本控制。");
			return json.toString();
		}
	}
	/**
	 * 文件捡入
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="checkIn", produces="text/plain;charset=UTF-8")
	public @ResponseBody String checkIn(HttpServletRequest request, final HttpServletResponse response) throws IOException {
		String elementId = ELEMENTID;
		JSONObject json = new JSONObject();
		//判断当前文件的状态
		 if(!AleroFileUtils.judgeEleState(AleroFileUtils.getConnect(), elementId)){
			 json.put("msg", "当前文档的状态为签入状态，请先签出");
			 return json.toString();
		 }
		File tempFile=null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> getFiles = multipartRequest.getFiles("file");// 获取上传的文件
		int code = 11;
		for (MultipartFile multipartFile : getFiles) {
			 try {
				 
				tempFile = ContractUtils.getFile(multipartFile);
				byte[] bytes = tempFile.getPath().getBytes();
				//签入文件
				code = AleroFileUtils.checkIn(new ByteArrayInputStream(bytes),AleroFileUtils.getConnect(),elementId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(code != 0){
			json.put("success", false);
			json.put("msg", "签入文件失败");
			return json.toString();
		}else{
			json.put("success", true);
			json.put("msg","文件签入成功");
			return json.toString();
		}
		
	}
	/**
	 * 文件检出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="checkOut", produces="text/plain;charset=UTF-8")
	public @ResponseBody String  checkOut(HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String elementId = ELEMENTID;
		 JSONObject json = new JSONObject();
		 //判断当前文件的状态
		 if(AleroFileUtils.judgeEleState(AleroFileUtils.getConnect(), elementId)){
			 json.put("msg", "当前文档的状态为签出状态，请先取消签出");
			 return json.toString();
		 }
		 response.setCharacterEncoding("utf-8");
	     response.setContentType("text/plain");
	     response.setHeader("Content-Disposition","attachments; filename*=UTF-8''sql_1.0.txt");
	     response.setHeader("Content-Transfer-Encoding", "binary");
	     OutputStream out = response.getOutputStream();
	        InputStream is = null;
	        try
	        {
	            // serve file
	            File checkoutFile = AleroFileUtils.checkOut(AleroFileUtils.getConnect(), null,elementId);
	            if (checkoutFile == null){
	            	json.put("msg", "找不到要检出的文件");
	            	return json.toString();
	            }
	            	
	            
	            response.setContentLength((int) checkoutFile.length());
	            is = new FileInputStream( checkoutFile );
	            IOUtils.copy(is, out);
	            is.close();
	            out.flush();
	            out.close();
	        }
	        finally
	        {
	            if (is != null)
	            {
	                try
	                {
	                    is.close();
	                    
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	        json.put("success", true);
	        return json.toString();
	}
	
	/**
	 * 文件取消签出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="unCheckOut", produces="text/plain;charset=UTF-8")
	public @ResponseBody String  unCheckOut(HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String elementId = ELEMENTID;
		JSONObject json = new JSONObject();
		 //判断当前文件的状态
		 if(!AleroFileUtils.judgeEleState(AleroFileUtils.getConnect(), elementId)){
			 json.put("msg", "当前文档的状态为签入状态，不能取消签出");
			 return json.toString();
		 }
		 int code = AleroFileUtils.unCheckOut(AleroFileUtils.getConnect(), elementId);
		if(code!=0){
			json.put("msg","取消签出失败");
		}else{
			json.put("success", true);
		}
		return json.toString();
	}
	/**
	 * 从容器中删除元素
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="removeElement", produces="text/plain;charset=UTF-8")
	public @ResponseBody String  removeElement(HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String elementId = ELEMENTID;
		JSONObject json = new JSONObject();
		 int code = AleroFileUtils.deleteElement(AleroFileUtils.getConnect(), elementId);
		if(code!=0){
			json.put("msg","删除元素失败");
			
		}else{
			json.put("success", true);
		}
		return json.toString();
	}
}

