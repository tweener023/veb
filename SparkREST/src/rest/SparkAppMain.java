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

public class SparkAppMain {
	
	private static CustomerDAO customerDAO = new CustomerDAO();
	
	private static Gson g = new Gson();
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("rest/test", (req, res) -> {
			return "Works";
		});
		

        post("/registration", (req, res) -> {
            Gson gsonReg = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            Customer customer = gsonReg.fromJson(req.body(), Customer.class);
            customerDAO.addCustomer(customer);
            return true;
        });
    }
	
}
