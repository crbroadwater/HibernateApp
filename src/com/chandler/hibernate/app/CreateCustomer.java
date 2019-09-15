package com.chandler.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chandler.hibernate.app.entity.Customer;

public class CreateCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory factory = new Configuration()
									.configure()
									.addAnnotatedClass(Customer.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating a new Customer file...");
			Customer tempCustomer = new Customer("Mike", "Jones", "mikejones@gmail.com", "801-555-5555", "BioLife Pharmaceuticals", 123456789);
			
			session.beginTransaction();
			System.out.println("Saving the Customer...");
			session.save(tempCustomer);
			
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}

}
