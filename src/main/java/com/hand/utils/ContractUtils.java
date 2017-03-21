package com.hand.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ContractUtils {
	
	// 上传合同支持类型
    public static final String DOC_SUFIX = ".doc";
    public static final String DOCX_SUFIX = ".docx";
    public static final String PDF_SUFIX = ".pdf";
	
	public static File getFile(MultipartFile multipartFile) throws Exception {
		File tempFile = File.createTempFile("addDoc", ".tmp", new File(ServletUtils.getTempFilesPath()));
		InputStream is = multipartFile.getInputStream();
		OutputStream os = new FileOutputStream(tempFile);
		IOUtils.copy(is, os);
		os.close();
		is.close();
		return tempFile;
	}
	
	/***
	 * 根据文件名获取 文档类型, 默认 类型 docx
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName){
		
		if(StringUtils.isBlank(fileName)){
			return ContractUtils.DOCX_SUFIX;
		}
		
		String fileType = null;
		if(fileName.endsWith(ContractUtils.PDF_SUFIX)){
			fileType = ContractUtils.PDF_SUFIX;
		}else if(fileName.endsWith(ContractUtils.DOC_SUFIX)){
			fileType = ContractUtils.DOC_SUFIX;
		}else{
			fileType = ContractUtils.DOCX_SUFIX;
		}
		return fileType;
	}

}
