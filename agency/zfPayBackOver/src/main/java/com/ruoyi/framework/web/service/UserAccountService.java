package com.ruoyi.framework.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.deveagent.account.domain.AgentUserAccount;
import com.ruoyi.project.deveagent.account.service.AgentUserAccountService;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * 
 * @author ruoyi
 */
@Service("account")
public class UserAccountService
{
    @Autowired
    private AgentUserAccountService agentUserAccountService;

   
   /**
    * 查询代理有效的中付账号列表
    * @param params
    * @return
    */
    public List<AgentUserAccount> getUserAccount()
    {
        return agentUserAccountService.getAgentValidUserAccountList();
    }

}
