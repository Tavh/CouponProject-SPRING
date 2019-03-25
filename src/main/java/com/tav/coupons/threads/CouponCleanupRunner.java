package com.tav.coupons.threads;

import javax.annotation.PostConstruct;

public class CouponCleanupRunner {

	private static ExpiredCouponCleanupThread expiredCouponCleanupThread;

	public void cleanExpiredCoupons() {
		Thread thread = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				expiredCouponCleanupThread = new ExpiredCouponCleanupThread();
			}
		};

		thread.start();			
	}
}
