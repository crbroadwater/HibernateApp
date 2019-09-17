package com.chandler.hibernate.app;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chandler.hibernate.app.entity.Customer;

public class CreateCustomer {

	private static String firstName, lastName, email, phone, group, temp, change;
	private static long social;
	private static int update, choice;
	private static Session session;
	private static List<Customer> customers;
	private static boolean changeInfo, done;
	private static SessionFactory factory;
	private static Customer customer;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		factory = new Configuration()
									.configure()
									.addAnnotatedClass(Customer.class)
									.buildSessionFactory();
		
		openSession();
		Scanner scan = new Scanner(System.in);
		done = false;
		changeInfo = false;
		factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		while(done == false) {
			openSession();
			choice = databaseOperations(scan);
			update = 0;
			databaseOperationsHelper(choice, scan);
		}
		System.out.println("Program Terminated");
		System.exit(0);
	}

	private static void openSession() {
		session = factory.getCurrentSession();
	}

	private static int databaseOperations(Scanner scan) {
		System.out.println("Would you like to:\n1. Add a customer to the database?"
				+ "\n2. View all customers in the database?"
				+ "\n3. Update a customer in the database?"
				+ "\n4. Remove a customer from the database?"
				+ "\n0. Quit Program?");
		int choice = scan.nextInt();
		return choice;
	}
	
	private static void databaseOperationsHelper(int choice, Scanner scan) {
		switch(choice) {
		case 1: 
			addCustomer(scan);
				break;
		case 2:
			queryAllCustomers();
				break;
		case 3: 
			updateCustomers(scan);
				break;
		case 4:
			removeCustomer(scan);
			break;
		case 0:
			closeProgram();
			break;
		default:
			System.out.println("Please enter a valid number: ");
			break;
			}
	}

	private static void closeProgram() {
		System.out.println("Quitting Program...");
		factory.close();
		done = true;
	}

	private static void addCustomer(Scanner scan) {
		System.out.println("Creating a new Customer file...");
		scan.nextLine();
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
		
		begin();
		System.out.println("Saving the Customer...");
		session.save(tempCustomer);
		
		commit();
		System.out.println("Done!");
	}

	private static void removeCustomer(Scanner scan) {
		System.out.println("Removing a Customer...");
		System.out.println("What is the Customer's ID number?");
		begin();
		Customer customerdata = session.get(Customer.class, scan.nextInt());
		System.out.println("Deleting Customer: " + customerdata);
		session.delete(customerdata);
		commit();
	}

	private static void updateCustomers(Scanner scan) {
		System.out.println("Updating Customer file...");
		System.out.println("What is the Customer's ID number?");
		begin();
		customer = session.get(Customer.class, scan.nextInt());
		if(customer != null) {
			updateCustomers2(scan);
		}
		else {
			System.out.println("Customer not found!");
			commit();
		}
	}

	private static void updateCustomers2(Scanner scan) {
		System.out.println("Customer found as: " + customer);
		update = updateInformation(scan);
		scan.nextLine();
		updateCustomersHelper(scan, customer, update);	
	}

	private static void updateCustomersHelper(Scanner scan, Customer customer, int update) {
		change = "";
		switch(update) {
		case 1:
			System.out.println("What do you want to change the First Name to?");
			change = scan.nextLine();
			if(change == null || change == "" || change.isEmpty()) {
				change = scan.nextLine();
			}
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setFirstName(change);
			commit();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 2:
			System.out.println("What do you want to change the Last Name to?");
			change = scan.nextLine();
			if(change == null || change == "" || change.isEmpty()) {
				change = scan.nextLine();
			}
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setLastName(change);
			commit();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 3:
			System.out.println("What do you want to change the Email Address to?");
			change = scan.nextLine();
			if(change == null || change == "" || change.isEmpty()) {
				change = scan.nextLine();
			}
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setEmail(change);
			commit();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 4:
			System.out.println("What do you want to change the Phone Number to?");
			change = scan.nextLine();
			if(change == null || change == "" || change.isEmpty()) {
				change = scan.nextLine();
			}
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setPhoneNumber(change);
			commit();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 5:
			System.out.println("What do you want to change the Group Name to?");
			change = scan.nextLine();
			if(change == null || change == "" || change.isEmpty()) {
				change = scan.nextLine();
			}
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setGroupName(change);
			commit();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 6:
			System.out.println("What do you want to change the Social Security Number to?");
			social = scan.nextLong();
			if(!session.getTransaction().isActive()) {
				openSession();
			}	
			customer.setSocial(social);
			commit();
			scan.nextLine();
			System.out.println("Would you like to change anything else for current customer?: Y for yes, N for no.");
			temp = scan.nextLine().trim().toLowerCase();
			if(temp.equals("y")) {
				updateCustomersHelper(scan, customer, updateInformation(scan));
				break;
			}
			else if(temp.equals("n")) {
				changeInfo = true;
				break;
			}
			else {
				System.out.println("Invalid Input Entered: ");
				changeInfo = true;
				break;
			}
		case 0: 
			System.out.println("Quitting the Update of Customer...");
			break;
		default:
			System.out.println("Please Enter a Valid Number:  ");
		}
	}

	private static void getTransaction() {
		session.getTransaction();
	}

	private static void begin() {
		session.beginTransaction();
	}

	private static void commit() {
		if(!session.getTransaction().isActive()) {
			begin();
		}
		session.getTransaction().commit();
	}

	private static void queryAllCustomers() {
		System.out.println("Searching for Customers in the database...");
		begin();
		customers = session.createQuery("from Customer").list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		commit();
	}

	private static int updateInformation(Scanner scan) {
		System.out.println("Would you like to\n"
				+ "1. Update First Name?\n"
				+ "2. Update Last Name?\n"
				+ "3. Update Email?\n"
				+ "4. Update Phone Number?\n"
				+ "5. Update Group Name?\n"
				+ "6. Update Social Security Number?\n"
				+ "0. Quit?");
		update = scan.nextInt();
		return update;
	}
}
