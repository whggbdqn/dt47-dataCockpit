package cn.bdqn.datacockpit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.XsTableService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.ExcelHelper;
import cn.bdqn.datacockpit.utils.ImportExecl;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Created by ehsy_it on 2016/8/10.
 */
@Controller
public class UserTilesController {
    @Autowired
    private XsTableService xs;

    @Autowired
    private InfoService infoService;

    @Autowired
    private CompanyinfoService companyinfoService;

    /**
     * 
     * Description: <br/>
     * 转发跳转到修改密码模块
     * 
     * @author rongLei
     * @param model
     * @return
     */
    @RequestMapping("/user_pass")
    public String pass(Model model) {
        model.addAttribute("checks", "geren2");
        return "user_pass.pages";
    }

    /**
     * 验证之前密码是否正确 Description: <br/>
     *
     * @author rongLei
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/password")
    public boolean password(String password, HttpSession session) {
        String phone = (String) session.getAttribute("phone");
        System.out.println(phone);
        boolean flag = companyinfoService.selectpassword(password);
        System.out.println(flag);
        return flag;
    }

    @RequestMapping("/user_update")
    public String update(Model model) {
        model.addAttribute("checks", "geren1");
        return "user_update.pages";
    }

    /**
     * 
     * Description: 转发到用户首页<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    @RequestMapping("/user_index")
    public String index(Model model) {
        return "user_index.pages";
    }

    /**
     * 
     * Description: 取通知信息和系统信息并重定向到user_index<br/>
     *
     * @author dengJ
     * @param req
     * @return
     */
    @RequestMapping("/user_second")
    public String userSecond(HttpServletRequest req) {
        List<Info> infoList = infoService.selectAllInfo();
        if (infoList != null) {
            for (Info info : infoList) {
                Date date = info.getPublishDate();
                System.out.println(date);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("tongzhi", infoList);
        return "redirect:/user_index.shtml";
    }

    @RequestMapping("/user_shuju1")
    public String shuju1(Model model) {
        model.addAttribute("checks", "shuju1");
        return "user_shuju1.pages";
    }

    @RequestMapping("/user_shuju2")
    public String shuju2(Model model) {
        model.addAttribute("checks", "shuju2");
        return "user_shuju2.pages";
    }

    @RequestMapping("/user_shuju3")
    public String shuju3(Model model, HttpServletRequest req) {
        model.addAttribute("checks", "shuju3");
        String names = req.getParameter("id");
        ChineseToPinYin ctp = new ChineseToPinYin();
        String name = ctp.getPingYin(names);
        model.addAttribute("name2", names);
        model.addAttribute("name1", name);
        JdbcUtil jdbc1 = new JdbcUtil();
        ApplicationContext context = jdbc1.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = jdbc1.selectObj(jt, name);
        if (lists != null) {
            try {
                int shows = (int) lists.get(0).get("shows");
                model.addAttribute("shows", shows);
                String time = "'";
                Date date = null;
                for (int i = 0; i < lists.size(); i++) {
                    date = (Date) lists.get(i).get("times");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    time = time + sdf.format(date) + "','";
                    if (i == lists.size() - 1) {
                        date = (Date) lists.get(i).get("times");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        time = time + sdf2.format(date);
                    }
                }
                time = "[" + time + "']";
                model.addAttribute("lists", time);
                String fNums = "";
                for (int i = 0; i < lists.size(); i++) {
                    if (i == lists.size() - 1) {
                        fNums = fNums + lists.get(i).get("daofangrenshu");
                    } else {
                        fNums = fNums + lists.get(i).get("daofangrenshu") + ",";
                    }
                }
                fNums = "[" + fNums + "]";

                String rNums = "";
                for (int i = 0; i < lists.size(); i++) {

                    if (i == lists.size() - 1) {
                        rNums = rNums + lists.get(i).get("renchourenshu");
                    } else {
                        rNums = rNums + lists.get(i).get("renchourenshu") + ",";
                    }
                }
                rNums = "[" + rNums + "]";
                model.addAttribute("rNums", rNums);
                model.addAttribute("fNums", fNums);
                Set<String> sets = new HashSet<String>();

                for (int i = 0; i < lists.size(); i++) {
                    sets = lists.get(i).keySet();
                }
                List<String> lists3 = new ArrayList<String>();
                for (String string : sets) {
                    lists3.add(string);
                }
                model.addAttribute("lists3", lists3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "user_shuju3.pages";
    }

    @RequestMapping("/user_guanxitu")
    public String userGuanxitu(Model model) {
        model.addAttribute("checks", "shuju4");
        return "user_guanxitu.pages";
    }

    @RequestMapping("/user_uploads")
    public String upload(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        String tb1 = urls.substring(12);
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];
        ChineseToPinYin ctp = new ChineseToPinYin();
        String tableName = ctp.getPingYin(tbNames);
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(urls);
        List<String> head = list.get(0);
        List<String> heads = new ArrayList<String>();
        for (int i = 0; i < head.size(); i++) {
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ctp.getPingYin(head.get(i)));
            }
        }

        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {
            List<String> content = list.get(j);
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    sb.insert(4, "-");
                    sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        JdbcUtil jdbcs = new JdbcUtil();
        ApplicationContext context = jdbcs.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcs.saveObj(jt, tableName, contents);
        return null;
    }

    @SuppressWarnings("resource")
    @ResponseBody
    @RequestMapping("/user_uploadss")
    public Map<String, String> uploads(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        String tb1 = urls.substring(3);// 文件名****.xlsx
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];// 文件****
        String tableName = ChineseToPinYin.getPingYin(tbNames);// 转为拼音
        ImportExecl poi = new ImportExecl();// 创建了工具类
        List<List<String>> list = poi.read(urls);// 读取指定路径下的Excel文件
        List<String> head = list.get(0);// 获取Excel表头的信息
        List<String> heads = new ArrayList<String>();// 定义一个集合存
        for (int i = 0; i < head.size(); i++) {// 判断日期
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ChineseToPinYin.getPingYin(head.get(i)));
            }
        }
        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {// 循环表头
            List<String> content = list.get(j);// 把表头装进集合(除开主键)
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {// 装换日期格式
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    // sb.insert(4, "-");
                    // sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        /* 循环List<Map> */
        ListIterator<Map<String, Object>> it = contents.listIterator();
        for (Map<String, Object> map : contents) {
            map.remove("id");
            map.remove("bianhao");
            map.remove("xuhao");
        }

        ApplicationContext context = JdbcUtil.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        int flag = JdbcUtil.saveObj(jt, tableName, contents);// 动态插入
        Map<String, String> maps = new HashMap<String, String>();
        if (flag >= 1) {
            maps.put("flag", "1");
        }
        return maps;
    }

    @SuppressWarnings("resource")
    @RequestMapping("user_export") // excel导出
    public void user_export(HttpSession session, HttpServletRequest req, HttpServletResponse response) {
        /* 获取表头信息 */
        String id = req.getParameter("id");
        String xmmc = req.getParameter("xmmc");
        String szqy = req.getParameter("szqy");
        String rq = req.getParameter("rq");
        String dfrs = req.getParameter("dfrs");
        String rcrs = req.getParameter("rcrs");
        String tdrs = req.getParameter("tdrs");
        /* 封装表头 */
        List<String> headers = new ArrayList<String>();
        headers.add(id);// 编号
        headers.add(xmmc);// 项目名称
        headers.add(szqy);// 所在区域
        headers.add(rq);// 日期
        headers.add(dfrs);// 到访人数
        headers.add(rcrs);// 认筹人数
        headers.add(tdrs);// 退订人数

        String tableName = session.getAttribute("table_name").toString();// 获取要导出的表的表名
        String name = ChineseToPinYin.getPingYin(tableName);// 转为拼音
        ApplicationContext context = JdbcUtil.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = JdbcUtil.selectObj(jt, name);// 获取所有信息
        List<Object[]> bodyContent = new ArrayList<Object[]>();
        for (Map<String, Object> map : lists) {
            String id0 = map.get("id").toString();
            String xiangmumingcheng0 = map.get("xiangmumingcheng").toString();
            String suozaiquyu0 = map.get("suozaiquyu").toString();
            String times0 = map.get("times").toString();
            String daofangrenshu0 = map.get("daofangrenshu").toString();
            String renchourenshu0 = map.get("renchourenshu").toString();
            String tuidingrenshu0 = map.get("tuidingrenshu").toString();
            Object[] obj = { id0, xiangmumingcheng0, suozaiquyu0, times0, daofangrenshu0, renchourenshu0,
                    tuidingrenshu0, };
            bodyContent.add(obj);
        }

        /* 导出Excel */
        ExcelHelper.exportExcel(headers, bodyContent, tableName, response);// 表头名，内容，文件名，响应

    }

}
