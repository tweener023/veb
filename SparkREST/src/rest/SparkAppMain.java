package rest;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import java.io.File;
import controller.ProductController;

public class SparkAppMain {
	
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		
	}
}
