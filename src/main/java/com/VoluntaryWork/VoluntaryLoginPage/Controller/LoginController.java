package com.VoluntaryWork.VoluntaryLoginPage.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.VoluntaryWork.VoluntaryLoginPage.Model.UserTO;
import com.VoluntaryWork.VoluntaryLoginPage.Service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userservice;
	
	public static String loginAs;
	


	public static String getLoginAs() {
		return loginAs;
	}

	public static void setLoginAs(String loginAs) {
		LoginController.loginAs = loginAs;
	}

	@RequestMapping(value= {"/","/login"}, method=RequestMethod.GET)
	
	public ModelAndView login(@RequestParam(value="loginAs", required=false) String loginAs ) {
		ModelAndView modelAndView=new ModelAndView();
			System.out.println(loginAs);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public ModelAndView registration() {
	
	UserTO userTO=new UserTO();	
	ModelAndView modelAndView =new ModelAndView();
	modelAndView.addObject("user",userTO);
	modelAndView.setViewName("registration");
	return modelAndView;

	}
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ModelAndView registration(@Valid UserTO userTO,@RequestParam("user_role") String user_role,BindingResult bindingResult) {
		ModelAndView modelAndView=new ModelAndView();
		UserTO userExists=userservice.findUserByEmail(userTO.getEmail());
		if(userExists!=null) {
			bindingResult.rejectValue("email","error.user","There is a User with existing EmailId");
			
		}
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		}
		else {
		
			userservice.saveUser(userTO,user_role);
			modelAndView.addObject("successMessage", "User has been Registered Successfully");
			modelAndView.addObject("user",new UserTO());
		    modelAndView.setViewName("registration");
		}
		return  modelAndView;
	}
	
	@RequestMapping(value="/host/home", method=RequestMethod.GET)
	public ModelAndView hostHome() {
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		UserTO userTO=userservice.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome "+userTO.getFirstName() +" "+userTO.getLastName());
		modelAndView.addObject("hostMessage", "YOU ARE LOGGED IN AS HOST");
		modelAndView.setViewName("host/home");
		
		return modelAndView;
	}
	@RequestMapping(value="/traveller/home", method=RequestMethod.GET)
	public ModelAndView travellerHome() {
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		UserTO userTO=userservice.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome "+userTO.getFirstName() +" "+userTO.getLastName());
		modelAndView.addObject("hostMessage", "YOU ARE LOGGED IN AS TRAVELLER");
		modelAndView.setViewName("traveller/home");
		
		return modelAndView;
	}
	
}
