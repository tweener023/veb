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
import services.UserService;
import beans.Customer;
import beans.User;

public class SparkAppMain {
	
	
	private static DateAdapter dateAdapter = new DateAdapter();

	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, dateAdapter).create();
	
	private static UserService userService = new UserService();
	
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
					Customer customer = userService.registerCustomer(userToReg);
					if (customer == null) {
						return null;
					}
					return g.toJson(customer);	// ovo je 1 opcija, druga je da vrati objeakt user
				});
	}
	
}
