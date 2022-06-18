package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Administrator;
import beans.Customer;
import beans.CustomerType;
import beans.UserRole;
import beans.Gender;
import beans.Manager;
import beans.Trainer;
import beans.User;

public class CustomerDAO {
	private String path = "./files/users.txt";
	private HashMap<String, User> users = new HashMap<String, User>();
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private HashMap<String, Administrator> administrators = new HashMap<String, Administrator>();
	private HashMap<String, Manager> managers = new HashMap<String, Manager>();
	private HashMap<String, Trainer> trainers = new HashMap<String, Trainer>();

	
	public CustomerDAO() {
		readUsers();
	}
	
	public ArrayList<User> getUsers(){
		return (ArrayList<User>) users.values();
	}
	
	public User getUser(String username) {
		return users.get(username);
	}
	
	/*
	 * Metoda ce prvo proveriti da li je zauzet prosledjeni username i ukoliko nije cuva novog korisnika
	 */
	public Customer registerCustomer(User user) {
		if (users.containsKey(user.getUsername())) {
			return null;
		}
		Customer newCustomer = new Customer(user);
		newCustomer.setRole(UserRole.kupac);
		return writeCustomer(newCustomer);
	}
	
	/*
	 * Metoda sluzi za ucitavanje svih entiteta iz txt fajla
	 */
	public void readUsers() {
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				
				String[] data = line.split(",");
				String id = data[0];
				boolean deleted = Boolean.parseBoolean(data[1]);
				String username = data[2];
				String password = data[3];
				String name = data[4];
				String surname = data[5];
				Gender gender = Gender.valueOf(data[6]);
				Date dateOfBirth = new Date(Long.parseLong(data[7]));
				UserRole role = UserRole.valueOf(data[8]);
				
				// ukolko je korisnik logicki obrisan
				if (deleted) {
					continue;
				}
				
				User user = new User(id, deleted, username, password, name, surname, gender, dateOfBirth, role);
				users.put(user.getUsername(), user);
				
				// formiranje konkretnih korisnika
				if (role == UserRole.administrator) {
					Administrator a = new Administrator(user);
					administrators.put(a.getUsername(), a);
				}
				else if (role == UserRole.kupac) {
					int pointsCollected = Integer.parseInt(data[9]);
					Customer c = new Customer(user);
					// TODO: KADA SE NAPRAVI BAZA ZA PORUDZBINE
					//c.setAllOrders(new ArrayList<Order>);
					// TODO: KADA SE RAZRADI LOGIKA ZA CUSTOMER TYPE ODRADITI
					//c.setCustomerType(new CustomerType());
					customers.put(c.getUsername(), c);
				}
				else if (role == UserRole.menadzer) {
					//String restaurantId = data[9];
					Manager m = new Manager(user);
					// TODO : KADA SE NAPRAVI BAZA ZA RESTORANE ODRADITI
					//m.setRestaurant(null);
					managers.put(m.getUsername(), m);
				}
				else if (role == UserRole.trener) {
					Trainer t = new Trainer(user);
					// TODO : KADA SE NAPRAVI BAZA ZA ISPORUKE UCITATI KOJE ISPORUKE TREBA DA ISPORUCI
					trainers.put(t.getUsername(), t);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	/*
	 * Metoda sluzi za upisivanje novog kupca u fajl
	 */
	private Customer writeCustomer(Customer customer) {
		try (FileWriter f = new FileWriter(path, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(customerToText(customer));
			return customer;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		}
	}
	
	private String customerToText(Customer customer) {
		return customer.getId() + "," + customer.getUsername() + "," 
				+ customer.getPassword() + "," + customer.getName() + "," + customer.getSurname() + "," 
				+ customer.getGender() + "," + customer.getDateOfBirth().getTime() + "," 
				+ customer.getRole() + ",";
	}
	
}