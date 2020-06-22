package com.ruoyi.framework.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;
import com.ruoyi.project.devemana.param.service.ManaSysParamRateService;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * 
 * @author ruoyi
 */
@Service("paramrate")
public class ParamRateService
{
    @Autowired
    private ManaSysParamRateService manaSysParamRateService;

   
   /**
    * 根据类型查询参数列表
    * @param params
    * @return
    */
    public List<ManaSysParamRate> getParamRate(String type,String pos_type)
    {
        return manaSysParamRateService.getManaParamRateListByType(type,pos_type);
    }

}
