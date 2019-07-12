package com.rhl.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

/**
 * ��̨��½����
 * @author rhl
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		Object admin = request.getSession().getAttribute("admin");
		
		if(admin == null) {
			String header = request.getHeader("X-Requested-With");
			//�ж��Ƿ���ajax����
			if("XMLHttpRequest".equals(header)) {
				//��ʾ��ajax����
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��½�Ự��ʱ��δ��½������µ�½��");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//��ʾ����ͨ������ת���Ӷ��򵽵�½ҳ��
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		return true;
	}

}
