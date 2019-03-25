
package com.tav.coupons.dao;
import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tav.coupons.entities.*;
import com.tav.coupons.entitymanagerhelper.EntityManagerHelper;
import com.tav.coupons.exceptions.ApplicationException;

@Repository
public class CouponDao {

	@PersistenceContext(unitName="CouponSpringUnit")
	private EntityManager entityManager;
	
// ------------------------------Create a new coupon-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createCoupon (CouponEntity coupon) throws ApplicationException {
		
		entityManager.persist(coupon);
		
		return coupon.getId();
	}
	
// ------------------------------Remove a coupon(s)---------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCoupon (long id) throws ApplicationException {
		
		CouponEntity coupon = entityManager.find(CouponEntity.class, id);
		
		entityManager.remove(coupon);
	}
	
	// Deletes all the expired coupons
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteExpiredCoupons () throws ApplicationException {
		
		/* Since this function is called from a ServletContextListener, it will not have access to
			the entityManager, therefore, we have to create a new one */
		EntityManager localEntityManager = EntityManagerHelper.getEntityManager();
		
		Calendar cal = Calendar.getInstance();

		Date today = cal.getTime();
		
		Query query = localEntityManager.createQuery("DELETE FROM CouponEntity coupon WHERE coupon.endDate<=:today").setParameter("today", today);
		
		// Handling the transaction and executing the query
		EntityManagerHelper.beginTransaction();
		query.executeUpdate();
		EntityManagerHelper.commit();
		
		EntityManagerHelper.closeEntityManager();
		
		System.out.println("Cleaning coupons...");
	}

// ------------------------------Update a coupon---------------------------------
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCoupon (CouponEntity coupon) throws ApplicationException {
		
		entityManager.merge(coupon);
		
	}

// -------------------------------------Getters-----------------------------------
	
	// If the coupon doesn't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public CouponEntity getCoupon (long id) throws ApplicationException {
		
		CouponEntity coupon = entityManager.find(CouponEntity.class, id);
		
		return coupon;
	}
	
	// If the coupon doesn't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public CouponEntity getCoupon (String title) throws ApplicationException {
		System.out.println(title);
		Query query = entityManager.createQuery("FROM CouponEntity coupon WHERE coupon.title=:couponTitle").setParameter("couponTitle", title);
		
		CouponEntity coupon; 
		
		List results = query.getResultList();

		if(results.isEmpty()) {
			coupon = null;
		} else {
			coupon = (CouponEntity) query.getSingleResult();
		}
		
		return coupon;
	}

	// Gets all the coupons that exist in the database
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getAllCoupons() throws ApplicationException {
		Query query = entityManager.createQuery("FROM CouponEntity");
		
		@SuppressWarnings("unchecked")
		List<CouponEntity> allCoupons = query.getResultList();
		
		return allCoupons;
	}
	
	// Gets all the coupons that were made by a specific company
	@Transactional(propagation = Propagation.REQUIRED)
	public List <CouponEntity> getCompanyCoupons (long companyId) throws ApplicationException {
		Query query = entityManager.createQuery("FROM CouponEntity coupon WHERE coupon.company.id=:companyId").setParameter("companyId", companyId);
		
		@SuppressWarnings("unchecked")
		List<CouponEntity> allCoupons = query.getResultList();
		
		return allCoupons;
	}

}
