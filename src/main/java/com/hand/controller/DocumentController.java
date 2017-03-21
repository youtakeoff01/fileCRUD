package com.hand.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hand.utils.ContractUtils;
import com.hand.utils.ServletUtils;

@Controller
public class DocumentController {
	
	@RequestMapping(value="uploadFile", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(HttpServletRequest request){
		File tempFile=null;
		String fileName="";
		HttpSession sesssion = ServletUtils.buildAppObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> getFiles = multipartRequest.getFiles("file");// 获取上传的文件
		JSONObject json = new JSONObject();
		for (MultipartFile multipartFile : getFiles) {
			 try {
				tempFile = ContractUtils.getFile(multipartFile);
				fileName = multipartFile.getOriginalFilename();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(tempFile == null){
			json.put("success", false);
			json.put("msg", "未获取到文件");
			return json.toString();
		}
		String fileType = ContractUtils.getFileType(fileName);
		Map map=new HashMap();
		json.put("success", true);
		return json.toString();
	}
	
	@RequestMapping(value="downloadFile", produces="text/plain;charset=UTF-8")
	public void connector(HttpServletRequest request, final HttpServletResponse response) throws IOException {
//		String target = request.getParameter("target");
//		String versionId = request.getParameter("versionId");
//		String revisionId = request.getParameter("revisionId");
//		String version = request.getParameter("version");
//		String revision = request.getParameter("revision");
//		String rawCommentValue = request.getParameter("comment")==null?"":request.getParameter("comment").trim();
//		String selectVersion = request.getParameter("versionSelect");
//		
//		boolean hasCheckoutComment = false;
//		if(rawCommentValue!=null){
//		    hasCheckoutComment = true;
//		    rawCommentValue = new String( rawCommentValue.getBytes("iso-8859-1"), "UTF-8");
//		}
//		
//		boolean download = true;
//		String mime = "application/oct-stream";
//		response.setCharacterEncoding("utf-8");
//		response.setContentType(mime);
//		String fileName = fsi.getName();
//		if (download) {
//			String fileNameOfVersion = fsi.getFileNameOfVersion(fsi,selectVersion);
//			if(StringUtils.isNotBlank(fileNameOfVersion)){
//				fileName = fileNameOfVersion;
//			}
//		    response.setHeader("Content-Disposition",
//		        "attachments; " + CommonUtils.getAttaFileName(getFileVersionName(fileName,selectVersion), request.getHeader("USER-AGENT")));
//		    //response.setHeader("Content-Location", fileUrlRelative);
//		    response.setHeader("Content-Transfer-Encoding", "binary");
//		}
//		
//		OutputStream out = response.getOutputStream();
//		InputStream is = null;
//		try
//		{
//		    // serve file
//		        File checkoutFile = fsi.openVersionInputFile(fsi,versionId,revisionId,hasCheckoutComment,version,revision,rawCommentValue,selectVersion);
//		        if (checkoutFile == null)
//		            return;
//		        
//		        response.setContentLength((int) checkoutFile.length());
//		        is = new FileInputStream( checkoutFile );
//		        IOUtils.copy(is, out);
//		        is.close();
//		        fsi.deleteInputFile();
//		        out.flush();
//		        out.close();
//		    }
//		    finally
//		    {
//		        if (is != null)
//		        {
//		            try
//		            {
//		                is.close();
//		                
//		            }
//		            catch (IOException e)
//		            {
//		                e.printStackTrace();
//		            }
//		        }
//		    }
		
	}

}

