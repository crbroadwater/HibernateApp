package com.chandler.hibernate.app;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder.Case;

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
		Scanner scan = new Scanner(System.in);
		boolean done = false;
		while(done == false) {
			factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
			session = factory.getCurrentSession();
			System.out.println("Would you like to:\n1. Add a customer to the database?"
					+ "\n2. View all customers in the database?"
					+ "\n3. Update a customer in the database?"
					+ "\n4. Remove a customer from the database?"
					+ "\n0. Quit Program?");
			int choice = scan.nextInt();
			List<Customer> customers;
			int update = 0;
			String firstName, lastName, email, phone, group;
			int social;

			switch(choice) {
			case 1: 
					System.out.println("Creating a new Customer file...");
					System.out.println("Enter First Name: ");
					firstName = scan.nextLine();
					System.out.println("Enter Last Name: ");
					lastName = scan.nextLine();
					System.out.println("Enter Email Address: ");
					email = scan.nextLine();
					System.out.println("Enter Phone Number: ");
					phone = scan.nextLine();
					System.out.println("Enter Group Name: ");
					group = scan.nextLine();
					System.out.println("Enter Social Security Number: ");
					social = scan.nextInt();
					Customer tempCustomer = new Customer(firstName, lastName, email, phone, group, social);
					
					session.beginTransaction();
					System.out.println("Saving the Customer...");
					session.save(tempCustomer);
					
					session.getTransaction().commit();
					System.out.println("Done!");
					break;
				
			case 2:
					System.out.println("Searching for Customers in the database...");
					session.beginTransaction();
					customers = session.createQuery("from Customer").list();
					for (Customer customer : customers) {
						System.out.println(customer);
					}
					session.getTransaction().commit();
					break;
			case 3: 
					System.out.println("Updating Customer file...");
					System.out.println("What is the Customer's ID number?");
					session.beginTransaction();
					Customer customer = session.get(Customer.class, scan.nextInt());
					if(customer != null) {
						System.out.println("Would you like to/n"
								+ "1. Update First Name?/n"
								+ "2. Update Last Name?/n"
								+ "3. Update Email?/n"
								+ "4. Update Phone Number?/n"
								+ "5. Update Group Name?/n"
								+ "6. Update Social Security Number?/n"
								+ "0. Quit?");
						update = scan.nextInt();
						String change;
						switch(update) {
						case 1:
							System.out.println("What do you want to change the First Name to?");
							change = scan.nextLine();
							customer.setFirstName(change);
							session.getTransaction().commit();
							break;
						case 2:
							System.out.println("What do you want to change the Last Name to?");
							change = scan.nextLine();
							customer.setLastName(change);
							session.getTransaction().commit();
							break;
						case 3:
							System.out.println("What do you want to change the Email Address to?");
							change = scan.nextLine();
							customer.setEmail(change);
							session.getTransaction().commit();
							break;
						case 4:
							System.out.println("What do you want to change the Phone Number to?");
							change = scan.nextLine();
							customer.setPhoneNumber(change);
							session.getTransaction().commit();
							break;
						case 5:
							System.out.println("What do you want to change the Group Name to?");
							change = scan.nextLine();
							customer.setGroupName(change);
							session.getTransaction().commit();
							break;
						case 6:
							System.out.println("What do you want to change the First Name to?");
							social = scan.nextInt();
							customer.setSocial(social);
							session.getTransaction().commit();
							break;
						case 0: 
							factory.close();
							break;
						default:
							System.out.println("Please Enter a Valid Number:  ");
						}
					}
					break;
			case 4:
				System.out.println("Removing a Customer...");
				System.out.println("What is the Customer's ID number?");
				session.beginTransaction();
				Customer customerdata = session.get(Customer.class, scan.nextInt());
				System.out.println("Deleting Customer: " + customerdata);
				session.delete(customerdata);
				session.getTransaction().commit();
				break;
			case 0:
				System.out.println("Quitting Program...");
				factory.close();
				done = true;
				break;
			default:
				System.out.println("Please enter a valid number: ");
				break;
				}
			}
			System.out.println("Program Terminated.");
		}
}
