/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 
 */
public class SessionBean {
    private Map<String,Object> dataMap;
    private static SessionBean bean = null;
    private SessionBean(){
       dataMap = new HashMap<String,Object>();
    }
    
    public static SessionBean getSession(){
        if(bean==null){
            bean = new SessionBean();
        }
        return bean;
    }
    public void setAttribute(String name,Object data){
        if(dataMap.containsKey(name)){
            dataMap.remove(name);
        }
        dataMap.put(name,data);
    }
    public Object getAttribute(String name){
        return dataMap.get(name);
    }
    public Object removeAttribute(String name){
        return dataMap.remove(name);
    }
}
