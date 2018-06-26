package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {
		
		// create sessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// DELETE VIA OBJECT INSTANTIATION 
			session.beginTransaction();
			int empId = 3;
			Employee emp = session.get(Employee.class, empId);
			session.delete(emp);
			session.getTransaction().commit();
			
			// DELETE VIA CREATE QUERY()
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Employee where id=5").executeUpdate();
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
