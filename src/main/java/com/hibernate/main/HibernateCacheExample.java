package com.hibernate.main;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class HibernateCacheExample {

	public static void main(String[] args) throws InterruptedException {

		Employee emp = new Employee();
		emp.setEmpName("Ramesh");
        emp.setEmpSalary(20000);
        
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.persist(emp);
		emp.setEmpName("Kapil");
		session.getTransaction().commit();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setFetchMode("employee.address", FetchMode.JOIN);
		emp.setEmpName("Rekha");
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		emp = (Employee) session.merge(emp);
//		session.update(emp);
		
		session.getTransaction().commit();
		sessionFactory.close();
	}
}
