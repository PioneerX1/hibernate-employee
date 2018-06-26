package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.entity.Employee;

public class UpdateEmployeeDemo {

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
			
			// 1. can create new object and update existing db entry with that ID
			// first, retrieve employee by id, instantiate object, modify it, then save it back
			int empId = 1;
			Employee emp = session.get(Employee.class, empId);
			emp.setCompany("T-Mobile");
			session.save(emp);
			
			// commit
			session.getTransaction().commit();
			
			
			// 2. can hard code a db field by that ID via createQuery
			// begin new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Employee e set company='Fulcrum' where e.id='2'").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
