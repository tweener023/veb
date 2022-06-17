package rest;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.ProductController;
import dao.CustomerDAO;
import beans.Customer;

public class SparkAppMain {
	
	private static CustomerDAO customerDAO = new CustomerDAO();
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		

        post("/registration", (req, res) -> {
            Gson gsonReg = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            Customer customer = gsonReg.fromJson(req.body(), Customer.class);
            customerDAO.addCustomer(customer);
            return true;
        });
    }
	
}
