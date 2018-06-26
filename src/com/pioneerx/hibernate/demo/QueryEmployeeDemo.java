package com.pioneerx.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.entity.Employee;

public class QueryEmployeeDemo {

	public static void main(String[] args) {

		// create sessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// PRINT JUST NORTAL EMPLOYEES
			session.beginTransaction();
			List<Employee> employees = session.createQuery("from Employee e where e.company = 'Nortal' ").getResultList();
			printEmployeeList(employees);
			
			// PRINT ALL EMPLOYEES
			employees = session.createQuery("from Employee").getResultList();
			printEmployeeList(employees);
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

	private static void printEmployeeList(List<Employee> employees) {
		System.out.println("----Employees List ----");
		for(Employee e : employees) {
			System.out.println(e.toString());
		}
		System.out.println("-----------------------");
	}

}
