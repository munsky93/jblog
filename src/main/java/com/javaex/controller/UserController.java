package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		System.out.println("joinform");

		return "user/joinForm";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinSuccess";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("login");
		return "user/login";
	}

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform(@RequestParam("id") String id,
			@RequestParam("password") String password, HttpSession session, Model model) {
		
		UserVo authUser = userService.loginform(id, password);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";

		} else {
			model.addAttribute("result", "fail");
			return "redirect:/user/login?no=1";

		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";

	}
    
    @ResponseBody
   	@RequestMapping(value="/idcheck", method=RequestMethod.POST)       /* idcheck를 받는다 */
   	public boolean exists(@RequestParam("id") String id) {          /* String id 담는다 */
   		
   		System.out.println("ajax id 중복체크" + id);
   		
     		return userService.idcheck(id);                                /* id 을 파라미터로 이용해서 userservice에 있는 idcheck 메소드를 호출한다 */
   	}                                                                     /* .idcheck == 메소드 */
   	
}
   	
