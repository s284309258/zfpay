package com.ruoyi.common.utils;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BasicSerivce {

    private static final Logger log = LoggerFactory.getLogger(BasicSerivce.class);

    @Autowired
    public DataSource dataSource;


    List<Map<String,Object>> list = new ArrayList<>();

    protected AgentUserInfo getShiroUserInfo(){
        log.info("BasicSerivce-getShiroUserInfo():SecurityUtils.getSubject().getSession()==="+SecurityUtils.getSubject().getSession());
        log.info("BasicSerivce-getShiroUserInfo():SecurityUtils.getSubject().getSession().getAttribute(Constants.AgentUserInfo)==="+
                SecurityUtils.getSubject().getSession().getAttribute(Constants.AgentUserInfo));
        return (AgentUserInfo) SecurityUtils.getSubject().getSession().getAttribute(Constants.AgentUserInfo);
    }

    protected BasicSerivce MapChainParams(Map<String,Object> params){
        String chain = StringUtil.nullToEmptySymbol(getShiroUserInfo().getParent_chain());
        params.put("chain",chain+getShiroUserInfo().getId());
        params.put("uid",getShiroUserInfo().getId());
        return this;
    }

    protected Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private int SqlTotal(Connection connection,String sql){

        String total = Sqlca.getString(connection,"select count(*) from (" +sql+") ttt");

        return Integer.parseInt(total);
    }

    private String SqlPageLimit(String sql,Map<String,Object> map){
        String order = map.get("order").toString();
        String sidx = String.valueOf(map.get("sidx"));
        if("null".equals(sidx) || "".equals(sidx)){
            sidx="id";
        }
        int page = Integer.parseInt(map.get("page").toString());
        int limit = Integer.parseInt(map.get("limit").toString());
        int startNum = (page-1)*limit;
        sql += " order by "+sidx+" "+order;
        sql += " limit "+startNum+","+limit;

        return sql;
    }

    private String SqlParamQuery(String sql,Map<String,Object> mm){
        StringBuffer buffer = new StringBuffer("select * from ("+sql+") tt where 1=1   ");
        String dd = sql.substring(6,sql.lastIndexOf(" from"));
        String[] ss = dd.split(" ,");
        if(mm.get("keyword")!=null && !"".equals(mm.get("keyword"))){
            buffer.append("and (");
        }
        for(String s : ss){
            if(s.contains("as ")){
                s=s.substring(s.lastIndexOf("as ")+2);
            }
            if(null!=mm.get("keyword") && !"".equals(mm.get("keyword"))){
                if(StringUtil.isContainChinese(mm.get("keyword").toString())){
                    if(s.contains("money") || s.contains("benefit") || s.contains("price") || s.contains("rate") || s.contains("num") || s.contains("performance") || s.contains("id") || s.contains("date")){

                    }else{
                        if(String.valueOf(mm.get("keyword")).startsWith("2020")
                                || String.valueOf(mm.get("keyword")).startsWith("2021")
                                || String.valueOf(mm.get("keyword")).startsWith("2022")
                                || String.valueOf(mm.get("keyword")).startsWith("2023")
                                || String.valueOf(mm.get("keyword")).startsWith("2024")
                                || String.valueOf(mm.get("keyword")).startsWith("2025")
                                || String.valueOf(mm.get("keyword")).startsWith("2026")
                                || String.valueOf(mm.get("keyword")).startsWith("2027")
                                || String.valueOf(mm.get("keyword")).startsWith("2028")
                                || String.valueOf(mm.get("keyword")).startsWith("2019")
                                || String.valueOf(mm.get("keyword")).startsWith("2018")
                                || String.valueOf(mm.get("keyword")).startsWith("2017")
                                || String.valueOf(mm.get("keyword")).startsWith("2016")
                                || String.valueOf(mm.get("keyword")).startsWith("2015")){
                            buffer.append(s.trim()+" like '"+mm.get("keyword")+"%'" +" or ");
                        }else{
                            buffer.append(s.trim()+"='"+mm.get("keyword")+"'" +" or ");
                        }
                    }
                }else{
                    if(String.valueOf(mm.get("keyword")).startsWith("202")){
                        buffer.append(s.trim()+" like '"+mm.get("keyword")+"%'" +" or ");
                    }else{
                        buffer.append(s.trim()+"='"+mm.get("keyword")+"'" +" or ");
                    }
                }
            }
        }
        buffer = new StringBuffer(buffer.substring(0,buffer.length()-3));
        if(mm.get("keyword")!=null && !"".equals(mm.get("keyword"))){
            buffer.append(")");
        }
        return buffer.toString();
    }

    protected List<Map<String,Object>> SqlQueryNonePage(String sql, Map params){
        try{

            list = Sqlca.getArrayListFromMap(SqlParamQuery(sql,params),dataSource.getConnection());

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
