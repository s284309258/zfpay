package com.ruoyi.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.ruoyi.common.utils.string.StringUtil;

/**
 * 错误信息处理类。
 *
 * @author ruoyi
 */
public class ExceptionUtil
{
	public static String getExceptionAllinformation(Exception e){
        String sOut = "";
        sOut += e.getMessage() + "\r\n";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
	}
}
