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
import services.WorkoutService;
import beans.Trainer;
import beans.User;
import beans.SportObject;

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
	}

	public Iterable<Object> getUsers(){
		readUsers();
		ArrayList<Object> users = new ArrayList<Object>();
		users.addAll(administrators.values());
		users.addAll(customers.values());
		users.addAll(trainers.values());
		users.addAll(managers.values());
		return users;
	}
	
	public Iterable<Manager> getManagers(){
		readUsers();
		return managers.values();
	}
	
	public Iterable<Customer> getCustomers() {
		readUsers();
		return customers.values();
	}
	
	public User getUser(String username) {
		readUsers();
		return users.get(username);
	}
	
	//**********Ne radi se ponovno ucitavanje podataka jer se ovo uvek poziva odmah nakog getUser//
	public Customer getCustomer(String username) {
		return customers.get(username);
	}
	
	public Manager getManager(String username) {
		return managers.get(username);
	}
	
	public Trainer getTrainer(String username) {
		return trainers.get(username);
	}
	
	public Administrator getAdministrator(String username) {
		return administrators.get(username);
	}
	//**********//
	
	/*
	 * Metoda ce prvo proveriti da li je zauzet prosledjeni username i ukoliko nije cuva novog korisnika
	 */
	public Customer registerCustomer(User user) {
		readUsers();
		if (users.containsKey(user.getUsername())) {
			Customer c = new Customer();
			c.setUsername("-1");	// ako je username zauzet vrati -1 u tom polju
			return c;
		}
		Customer newCustomer = new Customer(user);
		newCustomer.setRole(UserRole.kupac);
		if (writeUser(customerToText(newCustomer), true)) {
			return newCustomer;
		}
		return null;
	}
	
	/*
	 * Metoda sluzi za ucitavanje svih entiteta iz txt fajla
	 */
	public void readUsers() {
		clearHashMaps();
		ArrayList<String> lines = readLines();
		for (String line : lines) {
			String[] data = line.split(",");
			String id = data[0];
			String username = data[1];
			String password = data[2];
			String name = data[3];
			String surname = data[4];
			Gender gender = Gender.valueOf(data[5]);
			Date dateOfBirth = new Date();
			UserRole role = UserRole.valueOf(data[7]);
		
			User user = new User(username, password, name, surname, gender, dateOfBirth, role);
			users.put(user.getUsername(), user);
			
			// formiranje konkretnih korisnika
			if (role == UserRole.administrator) {
				Administrator a = new Administrator(user);
				administrators.put(a.getUsername(), a);
			}
			else if (role == UserRole.kupac) {
			//	int pointsCollected = Integer.parseInt(data[10]);
				String ordersText = "";
				Customer c = new Customer(user);
				/*
				try {
					ordersText = data[11];
					if (ordersText != null && ordersText != "") {
						String[] idsOfOrders = ordersText.split(";");
						WorkoutService os = new WorkoutService();
						for (String orderId : idsOfOrders) {
							c.get().add(os.getOrder(orderId));
						}
					}
				} catch(Exception e) {
					
				}
				ShoppingCartService scService = new ShoppingCartService();
				ShoppingCart sc = scService.getShoppingCartOfUser(id);
				if (sc == null) {
					sc = new ShoppingCart();
					sc.setCustomerId(id);
				}
				c.setShoppingCart(sc);
				c.setPointsCollected(pointsCollected);
				CustomerTypeService typeService = new CustomerTypeService();
				c.setType(typeService.getAppropriateCustomerType(pointsCollected));
				*/
				customers.put(c.getUsername(), c);
			}
		}
	}

	private ArrayList<String> readLines() {
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				
				lines.add(line);
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
		return lines;
	}

	private void clearHashMaps() {
		users.clear();
		customers.clear();
		administrators.clear();
		trainers.clear();
		managers.clear();
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
		return customer.getId() + ","  + customer.getUsername() + "," 
				+ customer.getPassword() + "," + customer.getName() + "," + customer.getSurname() + "," 
				+ customer.getGender() + "," + customer.getDateOfBirth() + "," 
				+ customer.getRole() + "," ;
	}

	private String managerToText(Manager manager) {
		StringBuilder managerString = new StringBuilder("");
		managerString.append(manager.getId() + "," + manager.getUsername() + "," 
				+ manager.getPassword() + "," + manager.getName() + "," + manager.getSurname() + "," 
				+ manager.getGender() + "," + manager.getDateOfBirth() + "," 
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
				+ trainer.getGender() + "," + trainer.getDateOfBirth()+ "," 
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

	public Manager registerManager(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public Trainer registerTrainer(Trainer trainer) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
