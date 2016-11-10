package org.ticket.controller;
import javax.servlet.http.HttpSession;

import org.ticket.bean.Users;
import org.ticket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author zero
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class UsersController {
	private UsersService usersService;

	public UsersService getUsersService() {
		return usersService;
	}
	//�Զ����
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	//�û���¼��ת
	@RequestMapping("Web/Application/login")
	public String login(){
		return "Web/Application/Index";
	}
	//�û���¼��֤
	@RequestMapping("Web/Application/verifyLogin")
	@ResponseBody
	public JSONPObject verifyLogin(Users user,@RequestParam String callback,HttpSession session){
		Users us = usersService.getUsersByUsersAccountAndUsersPassWord(user);
		System.out.println(us);
		boolean flag = false;
		if(null!=us)
		{
			flag = true;
			session.setAttribute("login", us);
			System.out.println("��¼�ɹ���flag="+flag);
			return new JSONPObject(callback, flag);	
		}
		System.out.println("��¼ʧ�ܣ�flag="+flag);
		return new JSONPObject(callback, flag);
	}
}
