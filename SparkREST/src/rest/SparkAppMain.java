package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.delete;
import static spark.Spark.put;

import java.io.File;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gsonAdapters.DateAdapter;
import services.SportObjectService;
import services.UserService;
import services.WorkoutService;
import spark.Session;
import beans.Customer;
import beans.SportObject;
import beans.User;
import beans.Workout;

public class SparkAppMain {
	
	
	private static DateAdapter dateAdapter = new DateAdapter();

	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, dateAdapter).create();
	
	private static UserService userService = new UserService();
	private static WorkoutService workoutService = new WorkoutService();
	private static SportObjectService sportObjectService = new SportObjectService();
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("rest/test", (req, res) -> {
			return "Works";
		});
		
				// Registracija korisnika
				post("rest/customers", (req, res) -> {
					res.type("application/json");
					String registrationParams = req.body();
					User userToReg = g.fromJson(registrationParams, User.class);
					Customer customer = userService.registerCustomer(userToReg);
					if (customer == null) {
						return null;
					}
					return g.toJson(customer);
				});
				
				post("rest/workout", (req, res) -> {
					res.type("application.json");
					Workout workout = g.fromJson(req.body(), Workout.class);
					return workoutService.saveWorkout(workout);
				});
				
				get("rest/workouts", (req, res) -> {
					res.type("application/json");
					return g.toJson(workoutService.getAllWorkouts());
				});
				
				delete("rest/workouts/:id", (req, res) -> {
					res.type("application/json");
					return g.toJson(workoutService.deleteWorkout(req.params(":id")));
				});
				
				put("rest/workouts", (req, res) -> {
					res.type("application/json");
					Workout newWorkout = g.fromJson(req.body(), Workout.class);
					String id = newWorkout.getId();
					return g.toJson(workoutService.changeWorkout(id, newWorkout));
				});
				
				post("rest/sportObject", (req, res) -> {
					res.type("application/json");
					SportObject sportObject = g.fromJson(req.body(), SportObject.class);
					return g.toJson(sportObjectService.saveSportObject(sportObject));
				});
				
				get("rest/sportObjects", (req, res) -> {
					res.type("application/json");
					return g.toJson(sportObjectService.getAllSportObjects());
				});
				
				delete("rest/sportObjects/:id", (req, res) -> {
					res.type("application/json");
					return g.toJson(sportObjectService.deleteSportObject(req.params(":id")));
				});
				
				put("rest/sportObjects", (req, res) -> {
					res.type("application/json");
					SportObject newSportObject = g.fromJson(req.body(), SportObject.class);
					String id = newSportObject.getId();
					return g.toJson(sportObjectService.changeSportObject(id, newSportObject));
				});
				
				// http://localhost:8080/login
				post("rest/login", (req, res) -> {
					res.type("application/json");
					Session ss = req.session(true);
					User userToLogIn = g.fromJson(req.body(), User.class);
					String username = userToLogIn.getUsername();
					String password = userToLogIn.getPassword();
					Object user = userService.findUser(username, password);
					if (user == null) {
						return null;
					} else {
						ss.attribute("user", user);
					}
					return g.toJson(user);
				});

				get("rest/getLoggedUser", (req, res) -> {
					res.type("application/json");
					Session ss = req.session(true);
					User user = (User) ss.attribute("user");
					if (user == null) {
						return null;
					} else {
						Object retUser = userService.findUser(user.getUsername(), user.getPassword());
						return g.toJson(retUser);
					}
				});
				
				// Izmena korisnika
				put("rest/customers/:id", (req, res) -> {
					res.type("application/json");
					Customer customer = g.fromJson(req.body(), Customer.class);
					Session ss = req.session();
					Object user = ss.attribute("user");
					customer = userService.changeCustomer(customer);
					// kada je username -1 znaci da je zauzeto i da izmena nije uspela
					if (user.getClass() == Customer.class && !customer.getUsername().equals("-1")) {
						ss.attribute("user", customer);
					}
					return g.toJson(customer);
				});

	}
	
}
