package com.rhl.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.util.StringUtil;
import com.rhl.programmer.entity.admin.User;
import com.rhl.programmer.service.admin.UserService;
import com.rhl.programmer.util.CpachaUtil;

/**
 * 系统操作类控制器
 * @author rhl
 *
 */

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆后的主页
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/index",method =RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		
		model.setViewName("system/index");
		model.addObject("name", "rhl");
		
		return model;
	}
	/**
	 * 登陆后的欢迎页面
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/welcome",method =RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model) {
		
		model.setViewName("system/welcome");
		
		return model;
	}
	
	/**
	 * 登陆页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method =RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		
		model.setViewName("system/login");
		
		return model;
	}
	/**
	 * 登录表单提交处
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method =RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user,String cpacha,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		if(StringUtil.isEmpty(cpacha)) {
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			return ret;
		}
		if(StringUtil.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(StringUtil.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null) {
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername==null){
//			System.out.println(findByUsername.getUsername());
			ret.put("type", "error");
			ret.put("msg", "该用户不存在！");
			return ret;
		}
		if(!findByUsername.getPassword().equals(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
			return ret;
		}
		
		request.getSession().setAttribute("admin", findByUsername);
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
		return ret;
	}
	
	/**
	 * 
	 *  系统验证码模块
	 * @param vcodeLen
	 * @param with
	 * @param height
	 * @param cpachaType:区别验证码类型，传入字符串
	 * @param request
	 * @param response
	 * 
	 */
	@RequestMapping(value = "/get_cpacha", method = RequestMethod.GET )
	public void generateCpacha(@RequestParam(name = "vl" ,required = false,defaultValue = "4") Integer vcodeLen,
			@RequestParam(name = "w" ,required = false,defaultValue = "100") Integer with,
			@RequestParam(name = "h" ,required = false,defaultValue = "30") Integer height,
			@RequestParam(name = "type" ,required = true,defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen,with,height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}	
	
}
