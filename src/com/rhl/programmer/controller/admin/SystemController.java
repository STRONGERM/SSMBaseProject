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
 * ϵͳ�����������
 * @author rhl
 *
 */

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * ��½�����ҳ
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
	 * ��½��Ļ�ӭҳ��
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/welcome",method =RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model) {
		
		model.setViewName("system/welcome");
		
		return model;
	}
	
	/**
	 * ��½ҳ��
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
	 * ��¼���ύ��
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
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		if(StringUtil.isEmpty(cpacha)) {
			ret.put("type", "error");
			ret.put("msg", "����д��֤�룡");
			return ret;
		}
		if(StringUtil.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if(StringUtil.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null) {
			ret.put("type", "error");
			ret.put("msg", "�Ự��ʱ����ˢ��ҳ�棡");
			return ret;
		}
		
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "��֤�����");
			return ret;
		}
		
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername==null){
//			System.out.println(findByUsername.getUsername());
			ret.put("type", "error");
			ret.put("msg", "���û������ڣ�");
			return ret;
		}
		if(!findByUsername.getPassword().equals(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�������");
			return ret;
		}
		
		request.getSession().setAttribute("admin", findByUsername);
		ret.put("type", "success");
		ret.put("msg", "��¼�ɹ���");
		return ret;
	}
	
	/**
	 * 
	 *  ϵͳ��֤��ģ��
	 * @param vcodeLen
	 * @param with
	 * @param height
	 * @param cpachaType:������֤�����ͣ������ַ���
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
