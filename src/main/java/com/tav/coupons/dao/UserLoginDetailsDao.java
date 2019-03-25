package com.tav.coupons.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tav.coupons.entities.UserLoginDetailsEntity;
import com.tav.coupons.enums.ErrorType;
import com.tav.coupons.exceptions.ApplicationException;

@Repository
public class UserLoginDetailsDao {

	@PersistenceContext(unitName="CouponSpringUnit")
	private EntityManager entityManager;


	// -----------------------------Constructor------------------------------------

	public UserLoginDetailsDao () {
	}

	// -----------------------------Create a new user login details-------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createUserLoginDetails (UserLoginDetailsEntity userLoginDetails) throws ApplicationException {
		entityManager.persist(userLoginDetails);

		return userLoginDetails.getId();
	}

	// ----------------------------Removes user login details------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeUserLoginDetails (long id) throws ApplicationException{

		UserLoginDetailsEntity userLoginDetails = entityManager.find(UserLoginDetailsEntity.class, id);
		
		entityManager.remove(userLoginDetails);
	}

	// ------------------------------Update user login details---------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUserLoginDetails (UserLoginDetailsEntity userLoginDetails) throws ApplicationException {

		entityManager.merge(userLoginDetails);
	}

	// --------------------------------Getters-------------------------------------

	// If the user details don't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public UserLoginDetailsEntity getUserLoginDetails (long id) throws ApplicationException {

		UserLoginDetailsEntity userLoginDetailsEntity = entityManager.find(UserLoginDetailsEntity.class, id);

		return userLoginDetailsEntity;
	}

	// If the user details don't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public UserLoginDetailsEntity getUserLoginDetails (String email) throws ApplicationException {

		Query query = entityManager.createQuery("FROM UserLoginDetailsEntity userLoginDetails WHERE userLoginDetails.email=:email").setParameter("email", email);

		UserLoginDetailsEntity userLoginDetails; 

		List results = query.getResultList();

		if(results.isEmpty()) {
			userLoginDetails = null;
		} else {
			userLoginDetails = (UserLoginDetailsEntity) query.getSingleResult();
		}

		return userLoginDetails;
	}

}
