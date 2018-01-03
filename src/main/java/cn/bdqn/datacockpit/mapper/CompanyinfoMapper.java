package cn.bdqn.datacockpit.mapper;

import java.util.List;
import java.util.Map;

import cn.bdqn.datacockpit.entity.Companyinfo;

public interface CompanyinfoMapper {

    List<Companyinfo> selectAllCompanies();

    int deleteByPrimaryKey(Integer id);

    int insert(Companyinfo record);

    int insertSelective(Companyinfo record);

    Companyinfo selectByPrimaryKey(Integer id);

    Companyinfo selectByPhone(String phone);

    int  selectpassword(Map  map);//根据密码是否重复
    
    int updateByPrimaryKeySelective(Companyinfo record);

    int updateByPrimaryKey(Companyinfo record);

    int selectPhoneNum(String phone);
}