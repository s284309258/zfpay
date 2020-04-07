package com.example.longecological.controller.message;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.message.MessageService;

/**
 * 通知 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 通知记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMessageRecordList")
	public R getMessageRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return messageService.getMessageRecordList(map);
	}
	
	/**
	 * 通知详情======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMessageRecordDetail")
	public R getMessageRecordDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return messageService.getMessageRecordDetail(map);
	}
}
