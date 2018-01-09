/**
 * Project Name:DataCockpit
 * File Name:UserinfoServiceImpl.java
 * Package Name:service.impl
 * Date:2017年8月21日下午1:58:39
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.entity.Userinfo1;
import cn.bdqn.datacockpit.mapper.UserinfoMapper;
import cn.bdqn.datacockpit.service.UserinfoService;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午1:58:39 <br/>
 * 
 * @author caoS
 * @version
 * @see
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired
    UserinfoMapper userinfoMapper;

	@Override
    public List<Userinfo> selectAllUserinfo() {
        return userinfoMapper.selectAllUserinfo();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int flag = userinfoMapper.deleteByPrimaryKey(id);
        return flag;
    }

    @Override
    public int insert(Userinfo1 record) {
        int flag = userinfoMapper.insert(record);
        return flag;
    }

    @Override
    public int insertSelective(Userinfo record) {
        int flag = userinfoMapper.insertSelective(record);
        return flag;
    }

    @Override
    public Userinfo1 selectByPrimaryKey(Integer id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Userinfo1 record) {
        int flag = userinfoMapper.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int updateByPrimaryKey(Userinfo1 record) {
        int flag = userinfoMapper.updateByPrimaryKey(record);
        return flag;
    }

    @Override
    public Userinfo1 getByPhone(String phone) {
    	Userinfo1 userinfo =userinfoMapper.getByPhone(phone);
    	System.out.println(userinfo);
        return userinfo;
    }

    @Override
    public Set<String> getRoles(String phone) {
    	String rolesSet=userinfoMapper.getRoles(phone);
    	Set<String> returnset =new HashSet<String>();
    	returnset.add(rolesSet);
    	System.out.println(returnset);
        return returnset;
    }

    @Override
    public Set<String> getPermissions(String phone) {
    	String permissionsSet=userinfoMapper.getPermissions(phone);
    	Set<String> returnset1 =new HashSet<String>();
    	returnset1.add(permissionsSet);
    	System.out.println(returnset1);
        return returnset1;
    }

}
