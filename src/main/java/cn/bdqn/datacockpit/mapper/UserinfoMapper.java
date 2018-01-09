package cn.bdqn.datacockpit.mapper;

import java.util.List;
import java.util.Set;

import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.entity.Userinfo1;

public interface UserinfoMapper {

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
    public String getRoles(String phone);
    
    /**
     * shiro通过电话号查询权限信息
     * @param userName
     * @return
     */
    public String getPermissions(String phone);
}
