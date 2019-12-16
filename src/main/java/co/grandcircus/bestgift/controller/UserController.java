package co.grandcircus.bestgift.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bestgift.jparepos.UserRepo;
import co.grandcircus.bestgift.models.User;
import co.grandcircus.bestgift.models.dandelion.EntityExtractionResults;
import co.grandcircus.bestgift.services.EntityExtractionService;

@Controller
public class UserController {

	@Autowired
	UserRepo rp;

	@Autowired
	HttpSession session;
	
	@RequestMapping("/") 
	public ModelAndView displayLogin() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/new-account")
	public ModelAndView newAccount() {
		return new ModelAndView("add-user");
	}

//	This checks to see if the email is already in the database. If not, the user will be added to the database
	@PostMapping("/add-user")
	public ModelAndView addUser(User user) {
		List<User> users = rp.findAll();
		for (User u : users) {
			if (u.getEmailAddress().equals(user.getEmailAddress())) {
				return new ModelAndView("redirect:/", "message", "Your email is already in our database");
			}
		}
		rp.save(user);
		return new ModelAndView("redirect:/");
	}

//	This checks to see if the database contains the email address, and if the password matches for the account
	@PostMapping("/login-user")
	public ModelAndView loginUser(@RequestParam("emailAddress") String emailAddress,
			@RequestParam("passWord") String passWord) {
		List<User> users = rp.findAll();
		User loginUser = rp.findByEmailAddress(emailAddress);
		if (!users.contains(loginUser)) {
			//TODO add a popup alert to indicate user if email/password don't match
			// as opposed to redirecting to a new page
			return new ModelAndView("index", "message", "Your email is not in our database");
		}
		if (!loginUser.getPassWord().equals(passWord)) {
			return new ModelAndView("index", "message", "Your password does not match");
		}
		
		session.setAttribute("user", loginUser);
				
		return new ModelAndView("startsearch", "user", loginUser);

	}
}
