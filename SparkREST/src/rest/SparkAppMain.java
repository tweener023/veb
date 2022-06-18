package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.CustomerDAO;
import beans.Customer;
import beans.User;

public class SparkAppMain {
	
	private static CustomerDAO customerDAO = new CustomerDAO();
	
	private static Gson g = new Gson();
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("rest/test", (req, res) -> {
			return "Works";
		});
		

		post("rest/customers", (req, res) -> {
			res.type("application/json");
			String registrationParams = req.body();
			User userToReg = g.fromJson(registrationParams, User.class);
			Customer customer = customerDAO.registerCustomer(userToReg);
			if (customer == null) {
				return null;
			}
			return g.toJson(customer);	// ovo je 1 opcija, druga je da vrati objeakt user
		});
    }
	
}
