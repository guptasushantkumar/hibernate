package com.hibernate.main;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.model.Customer;
import com.hibernate.model.Transaction;
import com.hibernate.util.HibernateUtil;

public class HibernateOnetoOne {

	public static void main(String[] args) {

		Transaction txn = new Transaction();
		txn.setTxnDate(new Date());
		txn.setTxnTotal(10);
		
		Customer cust = new Customer();
		cust.setCustAddress("Nagpur");
		cust.setCustEmail("abc.com");
		cust.setCustName("Bhola");
		
		txn.setCustomer(cust);
		cust.setTransaction(txn);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(txn);
//		session.save(cust);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

}
