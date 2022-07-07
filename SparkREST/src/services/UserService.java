package services;

import com.google.gson.JsonElement;

import beans.Customer;
import beans.Manager;
import beans.Trainer;
import beans.User;
import beans.UserRole;
import fileRepository.UserFileRepository;

public class UserService {

	private UserFileRepository userRepository = new UserFileRepository();
	
	public UserService() {
		
	}
	
	public Object findUser(String username, String password) {
		User user = userRepository.getUser(username);
		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}
		if (user.getRole() == UserRole.kupac) {
			return userRepository.getCustomer(username);
		}
		if (user.getRole() == UserRole.menadzer) {
			return userRepository.getManager(username);
		}
		if (user.getRole() == UserRole.trener) {
			return userRepository.getTrainer(username);
		}
		if (user.getRole() == UserRole.administrator) {
			return userRepository.getAdministrator(username);
		}
		return user;
	}
	
	public Customer registerCustomer(User user) {
		return userRepository.registerCustomer(user);
	}
	
	public Manager registerManager(Manager manager) {
		return userRepository.registerManager(manager);
	}
	
	public Trainer registerTrainer(Trainer trainer) {
		return userRepository.registerTrainer(trainer);
	}
	
	// IZMENA KORISNIKA
		public Customer changeCustomer(Customer customer) {
			return userRepository.changeCustomer(customer);
		}

		public Iterable<Object> getAllUsers(){
			return userRepository.getUsers();
		}
		
		public Object getUserByUsername(String username) {
			User user = userRepository.getUser(username);
			if (user == null) {
				return null;
			}
			if (user.getRole() == UserRole.kupac) {
				return userRepository.getCustomer(username);
			}
			if (user.getRole() == UserRole.menadzer) {
				return userRepository.getManager(username);
			}
			if (user.getRole() == UserRole.trener) {
				return userRepository.getTrainer(username);
			}
			if (user.getRole() == UserRole.administrator) {
				return userRepository.getAdministrator(username);
			}
			return user;
		}

}
