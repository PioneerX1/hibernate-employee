package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) {

		// create sessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// try catch for HQL transactions/statements
		try {
			// create Employee object
			Employee tempEmp = new Employee("Enzo", "Paschino", "Nortal");
			
			// begin transaction
			session.beginTransaction();
			
			// save employee object
			session.save(tempEmp);
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
