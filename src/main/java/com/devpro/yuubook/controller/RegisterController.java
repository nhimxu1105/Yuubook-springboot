package com.devpro.yuubook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.yuubook.dto.AjaxResponse;
import com.devpro.yuubook.entities.User;
import com.devpro.yuubook.services.UserService;

@Controller
public class RegisterController extends BaseController{ 
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}
	
	@PostMapping("/save-user")
	public ResponseEntity<AjaxResponse> saveUser(ModelMap model, @RequestBody User user){
		User userResp = userService.findUserByEmail(user.getEmail());
		if(userResp != null) {
			return ResponseEntity.ok(new AjaxResponse("Email đã tồn tại. Vui lòng nhập email khác.", 400));
		}
		userService.save(user);
		return ResponseEntity.ok(new AjaxResponse("Đăng kí thành công.", 200));
	}
}
