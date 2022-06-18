package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gsonAdapters.DateAdapter;
import services.SportObjectService;
import services.UserService;
import services.WorkoutService;
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
		

		// REGISTRACIJA kupca
				post("rest/customers", (req, res) -> {
					res.type("application/json");
					String registrationParams = req.body();
					User userToReg = g.fromJson(registrationParams, User.class);
					System.out.println("user je "+ userToReg);
					Customer customer = userService.registerCustomer(userToReg);
					if (customer == null) {
						return null;
					}
					return g.toJson(customer);	// ovo je 1 opcija, druga je da vrati objeakt user
				});
				
				post("rest/workout", (req, res) -> {
					res.type("application.json");
					Workout workout = g.fromJson(req.body(), Workout.class);
					boolean save = workoutService.saveWorkout(workout);
					if(save)
						return "Uspeh";
					else
						return null;
				});
				
				get("rest/items", (req, res) -> {
					return g.toJson(workoutService.getAllWorkouts());
				});
				
				post("rest/sportObjects", (req, res) -> {
					res.type("application/json");
					SportObject sportObject = g.fromJson(req.body(), SportObject.class);
					boolean save = sportObjectService.saveSportObject(sportObject);
					if(save)
						return "Uspeh";
					else
						return null;
				});
				
				get("rest/sportObject", (req, res) -> {
					return g.toJson(sportObjectService.getAllSportObjects());
				});
	}
	
}
