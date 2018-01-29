package com.hibernate.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class HQLExamples {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.getCurrentSession();
		Transaction tx = ss.beginTransaction();

		// Get All Employees
		Query query = ss.createQuery("from Employee");
		List<Employee> ls = query.list();
		for (Employee emp : ls) {
			System.out.println("List of Employees::" + emp.getEmpId() + ","
					+ emp.getAddress().getCity());
		}

		// Get Employee with id
		query = ss.createQuery("from Employee where empName= :name");
		query.setString("name", "David");
		Employee e = (Employee) query.uniqueResult();
		System.out.println("Employee Name=" + e.getEmpName() + ", City="
				+ e.getAddress().getCity());

		query = ss
				.createSQLQuery(
						"select {e.*}, {a.*} from Employee e join Address a ON e.emp_id=a.emp_id")
				.addEntity("e", Employee.class).addJoin("a", "e.address");

		Criteria criteria = ss.createCriteria(Employee.class).add(Restrictions.eq("id", new Integer(1)));
		
		ProjectionList columns = Projections.projectionList().add(Projections.property("a"));
		criteria.setProjection(columns);
		ss.getTransaction().commit();
		sf.close();
	}
}
