package com.ruoyi.project.develop.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.develop.common.service.UploadFileService;


/**
 * 文件上传
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/develop/upload")
public class UploadFileController extends BaseController
{
	
	
	@Autowired
	private UploadFileService uploadFileService;

	
	/**
	 * summernote编辑器文件上传
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/summernoteUpload")
    @ResponseBody
	public AjaxResult summernoteUpload(@RequestParam("file") MultipartFile file) {
		return uploadFileService.summernoteUpload(file);
	}
	
	
	
	/**
	 * summernote编辑器文件上传
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/photopUpload")
    @ResponseBody
	public AjaxResult photopUpload(@RequestParam("file") MultipartFile file) {
		return uploadFileService.photopUpload(file);
	}
    
    
}
