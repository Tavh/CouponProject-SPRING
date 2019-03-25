package com.tav.coupons.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tav.coupons.entities.CustomerEntity;
import com.tav.coupons.entities.UserLoginDetailsEntity;
import com.tav.coupons.enums.ErrorType;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.UserLoginDetailsController;

@RestController
@RequestMapping(value = "/loggedin/users")
public class UserLoginDetailsApi {

	@Autowired
	private UserLoginDetailsController userLoginDetailsController;

	// ------------------------------------Constructor---------------------------------

	public UserLoginDetailsApi () {

		this.userLoginDetailsController = new UserLoginDetailsController();
	}

	// -------------------------------------Login--------------------------------------

	@PostMapping("/login")
	public void login (HttpServletRequest request, 
			HttpServletResponse response, @RequestBody UserLoginDetailsEntity user) throws ApplicationException {

		String email = user.getEmail();
		String password = user.getPassword();

		boolean isUserLegitimate = userLoginDetailsController.isUserLegitimate(email, password);

		if (isUserLegitimate) {

			request.getSession();
			Cookie cookie = new Cookie("email", user.getEmail());
			cookie.setPath("/");
			response.addCookie(cookie);

			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(202);
			res.setHeader("LoginStatus", "User : " + user.getEmail() + ", has logged in successfully");
		}
	}

	// -------------------------------------Logout--------------------------------------

	@GetMapping("/logout")
	public void logout (HttpServletRequest request, 
			HttpServletResponse response)  throws Throwable {

		String email = "No email detected";
		
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
				Cookie userCookie = new Cookie("email", null);
				userCookie.setValue(null);
				userCookie.setPath(request.getContextPath()); 
				userCookie.setMaxAge(0); 
				response.addCookie(userCookie);
			}
		}
				
		request.getSession().invalidate();
		
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setValue(null);
		cookie.setPath(request.getContextPath()); 
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("LogoutStatus", "User : " +  email + " has logged out successfully");
	}
	
	// ------------------------------------Creates user login details---------------------------------

	@PostMapping("/no-login-required")
	public void createUserLoginDetails (@RequestBody UserLoginDetailsEntity userLoginDetails) throws ApplicationException {

		userLoginDetailsController.createUserLoginDetails(userLoginDetails);
	}

	// ------------------------------------Remove user login details---------------------------------

	@DeleteMapping("/{id}")
	public void removeUserLoginDetails (@PathVariable long id) throws ApplicationException {

		userLoginDetailsController.removeUserLoginDetails(id);
	}


	// ------------------------------Update user login details---------------------------------

	@PutMapping
	public void updateUserLoginDetails (@RequestBody UserLoginDetailsEntity userLoginDetails) throws ApplicationException {

		userLoginDetailsController.updateUserLoginDetails(userLoginDetails);
	}

	// ------------------------------------Getters---------------------------------

	@GetMapping("/{id}")
	public UserLoginDetailsEntity getUserLoginDetails (@PathVariable long id) throws ApplicationException {
		return userLoginDetailsController.getUserLoginDetails(id);
	}

	@GetMapping("/by-email")
	public UserLoginDetailsEntity getUserLoginDetails (@RequestParam String email) throws ApplicationException {
		return userLoginDetailsController.getUserLoginDetails(email);
	}
}
