package com.ruoyi.project.develop.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 文件上传
 * @author Administrator
 *
 */
public interface UploadFileService {


	/**
	 * summernote编辑器文件上传
	 * @param file
	 * @return
	 */
	AjaxResult summernoteUpload(MultipartFile file);

	
	/**
	 * 普通图片上传
	 * @param file
	 * @return
	 */
	AjaxResult photopUpload(MultipartFile file);

	
	
}
