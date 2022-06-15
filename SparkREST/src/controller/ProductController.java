package controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

import com.google.gson.Gson;

import services.ProductService;

public class ProductController {
	
	private static Gson g = new Gson();
	private static ProductService productService = new ProductService();
	
	
}
