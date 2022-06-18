package fileRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import beans.Administrator;
import beans.Customer;
import beans.Gender;
import beans.HistoryOfTraining;
import beans.Manager;
import beans.UserRole;
import beans.Workout;
import beans.Trainer;
import beans.User;

/**
 * Klasa koja služi da interaguje sa trajnim skladištem za korisnike.
 */
public class UserFileRepository {

	private String path = "./files/users.txt";
	private HashMap<String, User> users = new HashMap<String, User>();
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private HashMap<String, Administrator> administrators = new HashMap<String, Administrator>();
	private HashMap<String, Trainer> trainers = new HashMap<String, Trainer>();
	private HashMap<String, Manager> managers = new HashMap<String, Manager>();
	
	public UserFileRepository() {
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
					//c.setShoppingCart(new ShoppingCart());
					//c.setPointsCollected(pointsCo;llected)
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

	public Manager registerManager(Manager manager) {
		readUsers();
		manager.setRole(UserRole.menadzer);
		if (users.containsKey(manager.getUsername())){
			return null;
		}
		if (writeUser(managerToText(manager), true)) {
			return manager;
		}
		return null;
	}
	
	
	public Trainer registerTrainer(Trainer trainer) {
		readUsers();
		trainer.setRole(UserRole.trener);
		if (users.containsKey(trainer.getUsername())) {
			return null;
		}
		if (writeUser(trainerToText(trainer), true)) {
			return trainer;
		}
		return null;
	}
	
	private String customerToText(Customer customer) {
		return customer.getId() + ","  + customer.getUsername() + "," 
				+ customer.getPassword() + "," + customer.getName() + "," + customer.getSurname() + "," 
				+ customer.getGender() + "," + customer.getDateOfBirth().getTime() + "," 
				+ customer.getRole() + "," ;
	}

	private String managerToText(Manager manager) {
		StringBuilder managerString = new StringBuilder("");
		managerString.append(manager.getId() + "," + manager.getUsername() + "," 
				+ manager.getPassword() + "," + manager.getName() + "," + manager.getSurname() + "," 
				+ manager.getGender() + "," + manager.getDateOfBirth().getTime() + "," 
				+ manager.getRole() + ",");
		if (manager.getSportObject() == null) {
			managerString.append("-1");
		}else {
			managerString.append(manager.getSportObject().getId());
		}
		return managerString.toString();
	}
	
	private String trainerToText(Trainer trainer) {
		StringBuilder trainerString = new StringBuilder("");
		trainerString.append(trainer.getId() + "," + trainer.getUsername() + "," 
				+ trainer.getPassword() + "," + trainer.getName() + "," + trainer.getSurname() + "," 
				+ trainer.getGender() + "," + trainer.getDateOfBirth().getTime() + "," 
				+ trainer.getRole() + ",");
		for (HistoryOfTraining o : trainer.getUpcomingWorkouts()) {
			trainerString.append(o.getStartOfWorkout());
			trainerString.append(";");
		}
		return trainerString.toString();
	}
	/*
	 * Metoda sluzi za upisivanje novog korisnika u fajl. Drugi parametar sluzi da kaze da li se
	 * fajl iznova pise ili se dodaje novi red
	 */
	private boolean writeUser(String userToWrite, boolean append) {
		try (FileWriter f = new FileWriter(path, append);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(userToWrite);
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}
	}
	
}
