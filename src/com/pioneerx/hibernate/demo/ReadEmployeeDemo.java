package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.entity.Employee;

public class ReadEmployeeDemo {

	public static void main(String[] args) {
		
		// create sessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// begin transaction
			session.beginTransaction();
			
			// retrieve employee
			int tempId = 2;
			Employee savedEmp = session.get(Employee.class, tempId);
			System.out.println("Retrieved Employee: " + savedEmp.toString());
			
			// commit transaction, yes still do this for READ
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
