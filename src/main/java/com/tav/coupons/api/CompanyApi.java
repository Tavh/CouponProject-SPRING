package com.tav.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tav.coupons.entities.CompanyEntity;
import com.tav.coupons.entities.CouponEntity;
import com.tav.coupons.entities.UserLoginDetailsEntity;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CompanyController;
import com.tav.coupons.logic.UserLoginDetailsController;

@RestController
@RequestMapping(value = "/loggedin/companies")
public class CompanyApi {

	@Autowired
	private CompanyController companyController;

	// ------------------------------------Creates a new company---------------------------------

	@PostMapping("/no-login-required")
	public void createCompany (@RequestBody CompanyEntity company) throws ApplicationException {

		companyController.createCompany(company);
	}

	// ------------------------------------Removes a company---------------------------------

	@DeleteMapping("/{id}")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeCompany (@PathVariable long id) throws ApplicationException {

		companyController.removeCompany(id);
	}

	// ------------------------------Update user login details---------------------------------

	@PutMapping
	public void updateCompany (@RequestBody CompanyEntity company) throws ApplicationException {

		companyController.updateCompany(company);
	}
	
	// ------------------------------------Getters---------------------------------

	@GetMapping("/{id}")
	public CompanyEntity getCompany (@PathVariable long id) throws ApplicationException {
		return companyController.getCompany(id);
	}

	@GetMapping("/by-companyname")
	public CompanyEntity getCompany (@RequestParam String companyName) throws ApplicationException {
		return companyController.getCompany(companyName);
	}

	@GetMapping("all-companies")
	public List <CompanyEntity> getAllCompanies () throws ApplicationException {

		return companyController.getAllCompanies();
	}

}
