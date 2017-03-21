package com.hand.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, HttpServletRequest request, Model model) {
		session.invalidate();
		return "uploadFile";
	}
	
    @RequestMapping("/view")
    public ModelAndView view(HttpServletRequest request){
        String path = request.getParameter("path") + "";
        ModelAndView mav = new ModelAndView();
        String contextPath = request.getContextPath();
        mav.addObject("contextPath", contextPath);
        mav.setViewName(path);
        System.out.println("跳轉");
        return mav;
    }
    
    
}

