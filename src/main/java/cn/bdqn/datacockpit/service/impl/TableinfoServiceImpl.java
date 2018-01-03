/**
 * Project Name:DataCockpit
 * File Name:TableinfoServiceImpl.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月25日上午10:53:43
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Tableinfo;
import cn.bdqn.datacockpit.mapper.TableinfoMapper;
import cn.bdqn.datacockpit.service.TableinfoService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Description: <br/>
 * Date: 2017年8月25日 上午10:53:43 <br/>
 * 
 * @author yuanX
 * @version
 * @see
 */
@Service
public class TableinfoServiceImpl implements TableinfoService {
    @Autowired
    private TableinfoMapper tm;

    @Override
    public List<Tableinfo> selectAll(Integer id) {

        // Auto-generated method stub
        return tm.selectAll(id);
    }

    @SuppressWarnings("resource")
    @Override
    public Map<String, String> insert(String id, HttpSession session) {
        /*
         * 比如创建一个表，表名叫a，字段为默认，取到的id的值为：
         * 0,a,项目名称,1,所在区域,1,日期,0,到访人数,1,认筹人数,1,退订人数,1
         */
        String[] attr = id.split(",");
        System.out.println("创建表传进来的字符串" + id);
        Map<String, Object> map = new HashMap<String, Object>();// 创建一个map集合,用来装每个字段
        String tbName = null;// 创建一个空字符串,用来准备存放表名
        /*
         * 进行遍历,分别把字段存进map集合
         */
        for (int i = 0; i < attr.length; i++) {
            if (i == 0) {// 循环的第一次
                map.put("shows", attr[0]);// 传进来第一个值为表的显示方式,是柱状还是折线图
            } else if (i == 1) {// 循环的第二次
                tbName = ChineseToPinYin.getPingYin(attr[1]);// 使用工具类(处理中文转拼音),传进来第二个值为表的名字,调用工具类,使传进来的装换为拼音作为表名
            } else if (2 * i - 1 <= attr.length) {// 循环的最后
                // 把剩下的表的各个字段转换成拼音,为map集合的键,字段的属性为值(0为时间类型,1为文本类型,2为整数型,3为浮点类型)
                map.put(ChineseToPinYin.getPingYin(attr[2 * i - 2]), attr[2 * i - 1]);
            }
        }
        /*------------------以上将前台要创建的表的信息转为了map集合-----------------*/
        ApplicationContext context = JdbcUtil.getContext();// 创建数据库连接
        context = new ClassPathXmlApplicationContext("spring-common.xml");// 读取spring框架的配置文件
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");// 使用spring框架里面的一个类,获取数据库连接信息(driver,url,username,passwrod)
        int flag1 = JdbcUtil.createTable(jt, tbName, map);// 如果创建表成功,返回1,否则返回0
        // System.out.println("创建表的返回结果" + flag);

        Date dt = new Date();// 创建时间,准备再进行对另外表的操作
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 定义一个格式
        String date = sdf.format(dt);// 试日期按照自己的格式显示
        Tableinfo record = new Tableinfo();// 创建一个表信息的表
        record.setName(attr[1]);// 设置的表名
        record.setUpdatetime(date);// 设置的创建时间
        record.setShowtype(attr[0]);// 设置的表的显示方式(柱状,折线)
        // 此处多此一举
        // HttpSession session = req.getSession();// 获得session
        String ids = (String) session.getAttribute("No1");// 取出之前存进session里面的企业的ID
        Integer cid = Integer.parseInt(ids);// 转成Integer类型
        record.setCid(cid);// 存进表信息的对象里面去
        /*
         * 调用dao层的方法,对数据库中表信息的表进行添加操作
         */
        Map<String, String> maps = new HashMap<String, String>();// -----------创建了一个map集合给json进行响应
        int flag2 = tm.insert(record);
        if (flag1 == 1 && flag2 == 1) {
            maps.put("flag", "1");
            return maps;
        } else {
            maps.put("flag", "0");
            return maps;
        }

    }

}
