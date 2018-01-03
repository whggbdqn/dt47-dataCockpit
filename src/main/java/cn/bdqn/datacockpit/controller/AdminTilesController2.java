package cn.bdqn.datacockpit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Datarelation;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.DatarelationService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.RelevanceTableService;
import cn.bdqn.datacockpit.service.TableinfoService;
import cn.bdqn.datacockpit.service.UserinfoService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.JdbcUtil;

/**
 * Created by ehsy_it on 2016/8/10.
 */
@Controller
public class AdminTilesController2 {
    @Autowired
    private TableinfoService ts;// 表信息业务

    @Autowired
    private UserinfoService us;

    @Autowired
    private InfoService is;// 通知信息业务

    @Autowired
    private CompanyinfoService companyinfo;

    @Autowired
    private DatarelationService dataService;// 关联关系表业务

    @Autowired
    private RelevanceTableService releTable;

    // @RequestMapping("/admin_index")
    public String index(Model model) {
        return "admin_index.page";
    }

    /**
     * 
     * Description: 管理员通知页面<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    // @RequestMapping("/admin_tongzhi1")
    public String tongzhi1(Model model) {
        model.addAttribute("menus", "1");
        return "admin_tongzhi1.page";
    }

    /**
     * 
     * Description: 添加通知页面<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    // @RequestMapping("/admin_tongzhi2")
    public String tongzhi2(Model model) {
        return "admin_tongzhi2.page";
    }

    @RequestMapping("/admin_tongzhi3")
    public String tongzhi3(Model model, HttpServletRequest req) {
        is.selectByPrimaryKey(Integer.parseInt(req.getParameter("id")), model);
        return "admin_tongzhi3.page";
    }

    // @RequestMapping("/tongzhi_update")
    public String tongzhi_update(Info info) {
        // 获取实体类信息
        is.updateByPrimaryKeySelective(info);
        return "admin_tongzhi1.page";
    }

    /**
     * 
     * Description: 添加通知<br/>
     *
     * @author dengJ
     * @param info
     * @return
     */
    // @RequestMapping("/tongzhi_insert")
    public String tongzhi_insert(Info info) {
        // 获取实体类信息，将新增数据存入数据库
        is.insertSelective(info);
        // 获取本地时间与数据库时间格式一致
        java.util.Date date = new java.util.Date();
        java.sql.Date data1 = new java.sql.Date(date.getTime());
        info.setPublishDate(data1);
        // 获取最新一条记录ID
        Integer infoMax = is.selectMaxId();
        info.setId(infoMax);
        // 将时间存入最后一条记录中
        is.updateByPrimaryKey(info);
        return "admin_tongzhi1.page";
    }

    // @RequestMapping("/tongzhi_delete")
    public String tongzhi_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        is.deleteByPrimaryKey(id);
        return "admin_tongzhi1.page";
    }

    // @RequestMapping("/admin_delete")
    public String admin_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        us.deleteByPrimaryKey(id);
        return "admin_shuju4.page";
    }

    // @RequestMapping("/adminus_delete")
    public String adminus_delete(HttpServletRequest req) {
        // 获取id
        Integer id = Integer.parseInt(req.getParameter("id"));
        companyinfo.deleteByPrimaryKey(id);
        return "admin_userDsh.page";
    }

    // @RequestMapping("/aduser_update")
    public String aduser_update(Model model, HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        model.addAttribute("comp", comp);
        return "aduser_update.page";
    }

    /***
     * 
     * @param req:通过id更新approval状态
     * @return
     */
    // @RequestMapping("/adminuss_updatee")
    public String adminuss_updatee(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setApproval(1);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userDsh.page";
    }

    /***
     * 
     * @param req:通过id更新approval状态
     * @return
     */
    // @RequestMapping("/adminuss_updatee0")
    public String adminuss_updatee0(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setApproval(0);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userDsh.page";
    }

    /***
     * 
     * @param req:通过id更新state状态:1
     * @return
     */
    // @RequestMapping("/aduser_update1")
    public String aduser_update1(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setState(1);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userMan.page";
    }

    /***
     * 
     * @param req:通过id更新state状态:0
     * @return
     */
    // @RequestMapping("/aduser_update0")
    public String aduser_update0(HttpServletRequest req) {
        // 获取实体类信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Companyinfo comp = companyinfo.selectByPrimaryKey(id);
        comp.setState(0);
        companyinfo.updateByPrimaryKey(comp);
        return "admin_userMan.page";
    }

    // @RequestMapping("/aduser_update2")
    public String aduser_insert(Companyinfo comps) {
        // 获取实体类信息
        int flag = companyinfo.updateByPrimaryKey(comps);

        System.out.println(flag);
        return "admin_userMan.page";
    }

    @RequestMapping("/admin_cominfo")
    public String cominfo(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("menus", "3");
        model.addAttribute("lists", lists);
        return "admin_cominfo.page";
    }

    @RequestMapping("/admin_shuju1") // 在企业名单页面下点击查看查询所有企业下的表
    public String shuju1(Model model, HttpServletRequest req) throws Exception {
        model.addAttribute("menus", "3");
        String id = req.getParameter("id");
        req.getSession().setAttribute("No1", id);
        List<Map<String, Object>> lists = releTable.selectAllTables();
        model.addAttribute("lists", lists);
        return "admin_shuju1.page";
    }

    @RequestMapping("/admin_shuju2") // 任务中的项目列表-----------------没实现的
    public String shuju2(Model model, HttpServletRequest req) {
        return "admin_shuju2.page";
    }

    @RequestMapping("/admin_shuju3") // 分析任务管理下的---查看任务情况-----------------------没实现的

    public String shuju3(Model model) {
        return "admin_shuju3.page";
    }

    @RequestMapping("/admin_shuju4") // ----------------这里是访问后台用户界面
    public String shuju4(Model model) {
        model.addAttribute("menus", "2");
        return "admin_shuju4.page";
    }

    @RequestMapping("insertAdminReg") // 后台管理员添加用户-----------------------注册用户
    public String insertAdminReg(Userinfo record) {
        System.out.println("立即注册");
        int flag = us.insertSelective(record);
        // 转发
        return "admin_shuju4.page";
    }

    @RequestMapping("/selectAllCompanyinfo") // 点击数据驾驶舱或者登录成功
    public String selectAllCompanyinfo(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();// 查询所有企业
        model.addAttribute("lists", lists);
        List<Info> infoList = is.selectAllInfo();// 查询所有通知
        model.addAttribute("tongzhi", infoList);
        // req.setAttribute("tongzhi", infoList);
        return "admin_index.page";
    }

    @RequestMapping("/admin_userDsh") // 点击待审核用户
    public String dshCompanyinfo(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("lists", lists);
        model.addAttribute("menus", "5");
        return "admin_userDsh.page";
    }

    @RequestMapping("/admin_userMan") // 点击用户管理
    public String userMan(Model model) {
        List<Companyinfo> lists = companyinfo.selectAllCompanies();
        model.addAttribute("lists", lists);
        model.addAttribute("menus", "4");
        return "admin_userMan.page";
    }

    @ResponseBody
    @RequestMapping("/admin_create") // 新建数据表
    public Map<String, String> creats(@RequestParam("values") String id, HttpSession session) {
        return ts.insert(id, session);// -----------创建了一个map集合给json进行响应
    }

    // @RequestMapping("/admin_selects")//这个目前不知道干啥用的
    // public String selects(Model model) {
    // List<Companyinfo> lists = companyinfo.selectAllCompanies();
    // model.addAttribute("menus", "4");
    // model.addAttribute("lists", lists);
    // // 转发
    // return "admin_userMan.page";
    // }

    @RequestMapping("/admin_gongGao") // 公告详情
    public String gongGao1(Integer id, Model model) {
        is.selectByPrimaryKey(id, model);// 查询点击的公告标题，显示公告信息
        return "admin_gongGao.page";
    }

    @RequestMapping("/insert_guanlian") // 添加表关联关系表信息
    public String insertGL(Datarelation record) {
        return dataService.insert(record) ? "redirect:/admin_shuju1.shtml?id=1" : "admin_shuju1.page";// 添加成功或添加失败到：企业数据管理页面
    }

    // @RequestMapping("/admin_adds")//目前没用
    // public String adds(Model model) {
    // List<Companyinfo> lists = companyinfo.selectAllCompanies();
    // model.addAttribute("menus", "4");
    // model.addAttribute("lists", lists);
    //
    // // 转发
    // return null;
    // }

    @SuppressWarnings("resource")
    @RequestMapping("/admin_shujus") // 后台用户点击查看数据,查看企业指定表数据
    public String shuju3(Model model, HttpServletRequest req) {
        model.addAttribute("menus", "3");
        String names = req.getParameter("id");// 获取前段传来的表名(如销售数据表)
        String name = ChineseToPinYin.getPingYin(names);// 转为拼音
        model.addAttribute("name2", names);// 存是中文表名
        model.addAttribute("name1", name);// 存的拼音表名
        req.getSession().setAttribute("table_name", names);
        ApplicationContext context = JdbcUtil.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = JdbcUtil.selectObj(jt, name);// 查询这张表的信息select
                                                                       // * from
                                                                       // biaoming
        if (lists != null) {
            try {
                int shows = (int) lists.get(0).get("shows");
                model.addAttribute("shows", shows);// 显示方式
                String time = "'";
                Date date = null;
                for (int i = 0; i < lists.size(); i++) {// 获取时间进行格式化
                    date = (Date) lists.get(i).get("times");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    time = time + sdf.format(date) + "','";
                    if (i == lists.size() - 1) {
                        date = (Date) lists.get(i).get("times");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        time = time + sdf2.format(date);
                    }
                }
                time = "[" + time + "']";// 新的时间格式
                model.addAttribute("lists", time);// 将时间存进域
                String fNums = "";// 到访人数
                for (int i = 0; i < lists.size(); i++) {// 获取到访人数
                    if (i == lists.size() - 1) {
                        fNums = fNums + lists.get(i).get("daofangrenshu");
                    } else {
                        fNums = fNums + lists.get(i).get("daofangrenshu") + ",";
                    }
                }
                fNums = "[" + fNums + "]";

                String rNums = "";// 认筹人数
                for (int i = 0; i < lists.size(); i++) {// 获取认筹人数
                    if (i == lists.size() - 1) {
                        rNums = rNums + lists.get(i).get("renchourenshu");
                    } else {
                        rNums = rNums + lists.get(i).get("renchourenshu") + ",";
                    }
                }
                rNums = "[" + rNums + "]";
                model.addAttribute("rNums", rNums);// 存认筹人数
                model.addAttribute("fNums", fNums);// 存到访人数
                Set<String> sets = new HashSet<String>();
                /* 获取表头信息 */
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

        return "admin_shujus.page";
    }

    // @RequestMapping("/admin_uppassword")
    public String admin_uppassword(Model model) {
        model.addAttribute("checks", "geren2");
        return "admin_pass.page";
    }
}
