package com.ruoyi.project.develop.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.SysSecurityKeyConstant;
import com.ruoyi.common.utils.encryption.SignUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.interfaces.HttpUtils;
import com.ruoyi.framework.config.properties.ChatServerProperties;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.develop.common.service.UploadFileService;


/**
 * 文件上传
 * @author Administrator
 *
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
	
	Configuration cfg = new Configuration(); // zong1() 代表华北地区
	UploadManager uploadManager = new UploadManager(cfg);
	
	@Autowired
	private ChatServerProperties chatServerProperties;
	
	@Autowired
	RedisUtils redisUtils;

	
	/**
	 * summernote编辑器文件上传
	 */
	@Override
	public AjaxResult summernoteUpload(MultipartFile file) {
		try {
			//（1）调取http请求获取七牛云rdeis的token值
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("sys", "renegade");
			jsonRequest.put("sign", SignUtil.getSign(jsonRequest, false, false, SysSecurityKeyConstant.md5Key_app));
			String jsonString = HttpUtils.sendHttpPostRequestJson(chatServerProperties.get_qiniu_token_url, jsonRequest, false);
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,((JSONObject) jsonObject.get("data")).getString("qiniu_token"));
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = SysParamConstant.qiniu_domain + "/" + putRet.key;
			
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}


	/**
	 * 普通图片上传
	 */
	@Override
	public AjaxResult photopUpload(MultipartFile file) {
		try {
			//（1）调取http请求获取七牛云rdeis的token值
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("sys", "renegade");
			jsonRequest.put("sign", SignUtil.getSign(jsonRequest, false, false, SysSecurityKeyConstant.md5Key_app));
			String jsonString = HttpUtils.sendHttpPostRequestJson(chatServerProperties.get_qiniu_token_url, jsonRequest, false);
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,((JSONObject) jsonObject.get("data")).getString("qiniu_token"));
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = putRet.key;
			
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}
	
	
	
}
