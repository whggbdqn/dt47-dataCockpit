package cn.bdqn.datacockpit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.mapper.CompanyinfoMapper;
import cn.bdqn.datacockpit.service.CompanyinfoService;

@Service
public class CompanyinfoServiceImpl implements CompanyinfoService {

    @Autowired
    private CompanyinfoMapper companyinfo;

    @Override
    public int checkPhone(String phone) {
        return companyinfo.checkPhone(phone);
    }

    /**
     * 
     * 查询所有公司信息.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#selectAllCompanies()
     */
    @Override
    public List<Companyinfo> selectAllCompanies() {
        return companyinfo.selectAllCompanies();
    }

    /**
     * 
     * 根据指定的公司id删除公司客户.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#deleteByPrimaryKey(java.lang.Integer)
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int flag = companyinfo.deleteByPrimaryKey(id);
        return flag;
    }

    /**
     * 
     * 添加新的公司客户.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#insert(cn.bdqn.datacockpit.entity.Companyinfo)
     */
    @Override
    public int insert(Companyinfo record) {
        int flag = companyinfo.insert(record);
        return flag;
    }

    /**
     * 
     * 动态查询公司信息.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#insertSelective(cn.bdqn.datacockpit.entity.Companyinfo)
     */
    @Override
    public int insertSelective(Companyinfo record) {
        int flag = companyinfo.insertSelective(record);
        return flag;
    }

    /**
     * 
     * 查询指定的公司信息.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#selectByPrimaryKey(java.lang.Integer)
     */
    @Override
    public Companyinfo selectByPrimaryKey(Integer id) {
        return companyinfo.selectByPrimaryKey(id);
    }

    /**
     * 
     * 动态修改公司客户信息.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#updateByPrimaryKeySelective(cn.bdqn.datacockpit.entity.Companyinfo)
     */
    @Override
    public int updateByPrimaryKeySelective(Companyinfo record) {
        int flag = companyinfo.updateByPrimaryKeySelective(record);
        return flag;
    }

    /**
     * 
     * 修改指定公司的信息.
     * 
     * @see cn.bdqn.datacockpit.service.CompanyinfoService#updateByPrimaryKey(cn.bdqn.datacockpit.entity.Companyinfo)
     */
    @Override
    public int updateByPrimaryKey(Companyinfo record) {
        int flag = companyinfo.updateByPrimaryKey(record);
        return flag;
    }

    /**
     * 根据phone查询登录状况
     */
    @Override
    public Companyinfo selectByPhone(String phone) {

        return companyinfo.selectByPhone(phone);
    }

    /**
     * 根据phone查注册号码是否存在
     */
    @Override
    public int selectPhoneNum(String phone) {

        return companyinfo.selectPhoneNum(phone);
    }

    /**
     * 
     * Description: <br/>
     * ajax验证论文名字是否存在
     * 
     * @author rongLei
     * @param title
     * @return
     */
    @Override
    public boolean selectpassword(String password, String phone) {
        boolean flag = false;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("password", password);
        map.put("phone", phone);
        int i = companyinfo.selectpassword(map);
        System.out.println("i=" + i);
        if (i > 0) {// 密码正确，定义标记位为true
            flag = true;
        }
        System.out.println(flag);
        return flag;
    };

}
