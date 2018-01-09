/**
 * Project Name:DataCockpit
 * File Name:UserinfoService.java
 * Package Name:service
 * Date:2017年8月21日下午1:57:45
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.service;

import java.util.List;
import java.util.Set;

import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.entity.Userinfo1;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午1:57:45 <br/>
 * 
 * @author caoS
 * @version
 * @see
 */
public interface UserinfoService {
    
    List<Userinfo> selectAllUserinfo();

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo1 record);

    int insertSelective(Userinfo record);

    Userinfo1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo1 record);

    int updateByPrimaryKey(Userinfo1 record);
    
    /**
     * shiro通过电话号查询用户
     * @param userName
     * @return
     */
    public Userinfo1 getByPhone(String phone);
    
    /**
     * shiro通过电话号查询角色信息
     * @param userName
     * @return
     */
    public Set<String> getRoles(String phone);
    
    /**
     * shiro通过电话号查询权限信息
     * @param userName
     * @return
     */
    public Set<String> getPermissions(String phone);
}
