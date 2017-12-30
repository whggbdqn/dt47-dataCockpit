/**
 * Project Name:DataCockpit
 * File Name:TableinfoService.java
 * Package Name:cn.bdqn.datacockpit.service
 * Date:2017年8月25日上午10:53:12
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.bdqn.datacockpit.entity.Tableinfo;

/**
 * Description: <br/>
 * Date: 2017年8月25日 上午10:53:12 <br/>
 * 
 * @author yuanX
 * @version
 * @see
 */
public interface TableinfoService {
    List<Tableinfo> selectAll(Integer id);

    /**
     * 
     * Description: <br/>
     *
     * @author YangLingYun
     * @param id 前台传过来的表信息,为字符串类型,包括内容如下(0,a,项目名称,1,所在区域,1,日期,0,到访人数,1,认筹人数,1,退订人数,1)
     *            第一个值代表表的显示类型(0位折线图,1为柱状图),第二个值就是表的名称,后面是就是字段名加上字段类型(1表示字符类型,2表示整数类型,3表示浮点类型,-1表示时间类型)
     * @param session 传递session对象是为了取出之前存在session对象里的企业用户的ID值
     * @return map集合给json进行响应,一定要返回键为flag值为1,否则前台页面不会刷新
     */
    Map<String, String> insert(String id, HttpSession session);
}
