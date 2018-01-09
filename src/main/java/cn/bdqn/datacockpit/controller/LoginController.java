/**
 * Project Name:DataCockpit
 * File Name:LoginController.java
 * Package Name:cn.bdqn.datacockpit.controller
 * Date:2017年8月23日上午9:44:48
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.entity.Userinfo1;
import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.UserinfoService;
import cn.bdqn.datacockpit.utils.LoggerUtils;
import cn.bdqn.datacockpit.utils.VerifyCodeUtils;

/**
 * Description: <br/>
 * Date: 2017年8月23日 上午9:44:48 <br/>
 * 
 * @author jiaoHJ
 * @version
 * @see
 */
@Controller
@Scope("prototype")
public class LoginController {
    private static final Exception IncorrectCredentialsException = null;

    @Autowired
    private CompanyinfoService companyinfo;

    @Autowired
    private UserinfoService userinfo;

    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "getYzm")
    public @ResponseBody List<String> getYzm(HttpServletResponse response, HttpServletRequest request) {
        List<String> lists = new ArrayList<String>();
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            // 生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            System.out.println(verifyCode);
            // 存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("code", verifyCode);
            // 生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
            lists.add("OK");
            return lists;
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
        }
        return lists;

    }

    /**
     * 登录
     * 此方法已经过时
     * @param phone
     * @param password
     * @param onLine
     * @param res
     * @param req
     * @return
     */
   @SuppressWarnings("finally")
	/* @RequestMapping("/login2")
    public String login(HttpSession session, HttpServletResponse res, HttpServletRequest req) {
        session = req.getSession();
        String phone = (String) session.getAttribute("phone");
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Companyinfo compi = companyinfo.selectByPhone(phone);
        Userinfo ui = userinfo.getByPhone(phone);
        // 从session获取验证码方法中存入的验证码
        @SuppressWarnings("unused")
        String trueCode = (String) session.getAttribute("code");
        // 对比验证码
        
         * if(!trueCode.equals(code2)){ //验证码不正确则返回不正确
         * req.setAttribute("erroMessage", "*验证码不正确"); }
         
        // 根据账号判断该用户属于公司还是管理员
        List<Info> infoList = infoService.selectAllInfo();
        Date time = new Date();
        Date ti1 = new Date(time.getTime() - 2 * 24 * 60 * 60 * 1000);
        for (Info info : infoList) {
            Date date = info.getPublishDate();
            Map<String, Object> map = new HashMap<String, Object>();
            if (ti1.before(date)) {
                map.put("date", 1);
            } else {
                map.put("date", 0);
            }

            map.put("info", info);
            lists.add(map);

        }
        if (compi != null) {
            session.setAttribute("infos", compi);
            session.setAttribute("flag", lists);
            return "redirect:/user_index.shtml";
        }
        // 判断账号密码是否正确（管理员）
        if (ui != null) {
            session.setAttribute("infos", ui);
            session.setAttribute("flag", lists);
            return "redirect:/selectAllCompanyinfo.shtml";
        }
        session.setAttribute("erroMessage", "*账号或者密码输入有误！");
        return "redirect:/login.jsp";
    }
*/
    /*
     * shiro方法登录
     */
   /* @RequestMapping("/login")
    public String login(Userinfo user, String code2, HttpSession session, HttpServletRequest request) {
        // 首先判断验证码是否正确
        String trueCode = (String) session.getAttribute("code");
        if (!code2.equals(trueCode)) {
            session.setAttribute("erroMessage", "*验证码错误！");
            return "redirect:/login.jsp";
        }
        Subject subject = SecurityUtils.getSubject();// 查看有哪些权限
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        try {
            subject.login(token);
            Session session2 = subject.getSession();
            session.setAttribute("phone", user.getPhone());
            return "redirect:/login2.shtml";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("erroMessage", "*用户名或密码错误！");
            return "redirect:/login.jsp";
        }
    }*/
    @RequestMapping("/login")
    public String login(@RequestParam("phone")String phone,@RequestParam("password")String password,@RequestParam("code2")String ValidateCode,HttpSession session) {
        // 1.首先判断验证码是否正确
        String trueCode = (String) session.getAttribute("code");  //生成的二维码编号
        System.out.println(ValidateCode);
        if (!trueCode.equals(ValidateCode)) {		//验证验证码，错误将信息保存到session中，并重定向到页面
        	session.setAttribute("erroMessage", "*验证码错误！");
        	return "redirect:/login.jsp";
        }
        System.out.println("开始校验！！！");
        //2.0shiro开始校验账户密码
        Subject subject = SecurityUtils.getSubject();// 得到账号密码封装起来的用户，并未进行登录成功的操作
        //2.1在校验账号密码之前，判断该用户是否已经登录过了，如果登录过了直接重定向到主页面，否则进行shiro的登录校验
        if(!subject.isAuthenticated()) {
        	//进行shiro的登录校验
        	UsernamePasswordToken currentToken = new UsernamePasswordToken(phone, password);//将账号密码封装成UsernamePasswordToken口令对象
        	currentToken.setRememberMe(true);//记住当前用户，在浏览器关闭后再次打开的情况下可以不用登录
        	try {
                subject.login(currentToken); //将当前为成功登录的信息交给shiro底层为用户校验，成功在realm中进行重定向到角色对应的主页，否则抛出异常并将错误信息返回到登录页面
               System.out.println("shiro校验成功！！！");
                if(subject.hasRole("company_user")) {
                	return "redirect:user_index.shtml";
                }
                return "redirect:admin_index.shtml";
            } catch (Exception e) {
            	session.setAttribute("erroMessage", "*用户名或密码错误！");
            	e.printStackTrace();
            	return "redirect:/login.jsp";
            }
        }else {
        	//已经登录过了的用户，通过判断其角色，进行分发到不同的主页面
        	if(subject.hasRole("company_user")) {
        		 return "redirect:user_index.shtml";
            }
        	return "redirect:admin_index.shtml";
        }
    }
  /**
   * 访问其他页面
   * */
   @RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}

    /**
     * 注册（申请合作）
     * 
     * @param cominfo
     * @return
     */
    @RequestMapping("/register")
    public String register(Companyinfo cominfo) {
        int flag = companyinfo.insert(cominfo);
        if (flag >= 1) {
            return "front/shenqing.jsp";
        }

        return "front/error.jsp";
    }

    /**
     * 修改资料，先获取存在session里面的实体类
     * 
     * @param req
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Companyinfo compi = (Companyinfo) session.getAttribute("infos");
        session.setAttribute("comp", compi);

        return "redirect:/user_update.shtml";
    }

    /**
     * 动态修改资料，修改密码不在此页面
     * 
     * @param company
     * @param req
     * @return
     */
    @RequestMapping("/updateInfo1")
    public String updateInfo1(Companyinfo company, HttpServletRequest req) {
        // System.out.println(company);
        HttpSession session = req.getSession();
        int flag = companyinfo.updateByPrimaryKeySelective(company);
        if (flag >= 1) {
            return "redirect:/user_index.shtml";
        }
        session.setAttribute("message", "*修改失败！");
        return "redirect:/user_update.shtml";
    }

    /**
     * 把密码带到页面
     * 
     * @param req
     * @return
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Companyinfo compi = (Companyinfo) session.getAttribute("infos");
        session.setAttribute("comp", compi);
        return "redirect:/user_pass.shtml";
    }

    /**
     * 修改密码
     * 
     * @param company
     * @return
     */
    @RequestMapping("/updatePassword1")
    public String updatePassword1(Companyinfo company) {
        int flag = companyinfo.updateByPrimaryKeySelective(company);
        if (flag >= 1) {
            return "redirect:/user_index.shtml";
        }
        return "redirect:/user_pass.shtml";
    }

    /**
     * 检验注册的手机号码是否存在
     * 
     * @param phone
     * @return
     */
    @RequestMapping("/testPhone")
    @ResponseBody
    public Map<String, Object> testPhone(String phone) {
        int flag = companyinfo.selectPhoneNum(phone);
        Map<String, Object> hm = new HashMap<String, Object>();
        if (flag >= 1) {
            hm.put("num", 1);
            hm.put("error", "*您输入的手机号码已存在！");
        } else {
            hm.put("num", 0);
            hm.put("error", "");
        }
        return hm;
    }

    /**
     * 退出登录
     * 
     * @param req
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpServletRequest req) {
    	SecurityUtils.getSubject().getSession().stop();
        return "front/exit.jsp";
    }

    /**
     * 公告详情
     * 
     * @param req
     * @return
     */
    @RequestMapping("/gongGao")
    public String gongGao(Integer id, Model model) {
        // System.out.println(id);
        infoService.selectByPrimaryKey(id, model);
        // model.addAttribute("gg", info);
        return "user_gongGao.pages";
    }

    /**
     * 公告详情
     * 
     * @param req
     * @return
     */
    @RequestMapping("/selectTongzhi")
    public String selectTongzhi(Model model) {
        List<Info> lists = infoService.selectAllInfo();
        model.addAttribute("infoList", lists);
        return "user_tongzhi.pages";
    }
}
