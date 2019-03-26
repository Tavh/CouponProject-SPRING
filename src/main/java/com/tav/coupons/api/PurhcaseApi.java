package com.tav.coupons.api;

import java.util.List;

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

import com.tav.coupons.entities.CouponEntity;
import com.tav.coupons.entities.CustomerEntity;
import com.tav.coupons.entities.PurchaseEntity;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CouponController;
import com.tav.coupons.logic.CustomerController;
import com.tav.coupons.logic.PurchaseController;

@RestController
@RequestMapping(value = "/loggedin/purchases")
public class PurhcaseApi {

	@Autowired
	private PurchaseController purchaseController;
	@Autowired
	private CustomerController customerController;
	@Autowired
	private CouponController couponController;

	// ------------------------------------Creates a new purchase---------------------------------

	@PostMapping
	public void createPurchase (@RequestBody PurchaseEntity purchase) throws ApplicationException {

		CustomerEntity customer = customerController.getCustomer(2);
		CouponEntity coupon = couponController.getCoupon(1);

		purchase.setCustomer(customer);
		purchase.setCoupon(coupon);
		
		purchaseController.createPurchase(purchase);
	}

	// ------------------------------------Removes a purchase---------------------------------

	@DeleteMapping("/{id}")
	public void removePurchase (@PathVariable long id) throws ApplicationException {

		purchaseController.removePurchase(id);
	}
	
	// ------------------------------Updates a purchase---------------------------------

	@PutMapping
	public void updatePurchase (@RequestBody PurchaseEntity purchase) throws ApplicationException {

		purchaseController.updatePurchase(purchase);
	}
	
	// ------------------------------------Getters---------------------------------

	@GetMapping("/{id}")
	public PurchaseEntity getPurchase (@PathVariable long id) throws ApplicationException {
		return purchaseController.getPurchase(id);
	}
	
	@GetMapping("/customer-purchases")
	public List<PurchaseEntity> getCustomerPurchases (@RequestParam long customerId) throws ApplicationException {
		return purchaseController.getCustomerPurchases(customerId);
	}
}
