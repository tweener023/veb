package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Customer;
import beans.CustomerType;
import beans.UserRole;
import beans.Gender;

public class CustomerDAO {
    private HashMap<String, Customer> customers;
    private HashMap<String, CustomerType> customerTypes;

    public CustomerDAO() {
        customers = new HashMap<String, Customer>();
        customerTypes = new HashMap<>();

        try {
            loadAll();
            loadTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        try {
//            writeAll();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }
    
    @SuppressWarnings("deprecation")
	private void writeAll() throws IOException {
        Customer c1 = new Customer();
        c1.setUsername("c1");
        c1.setPassword("123");
        c1.setName("Mile");
        c1.setSurname("Kitic");
        c1.setGender(beans.Gender.muski);
        c1.setDateOfBirth(new Date(1998, Calendar.JANUARY, 2));
        c1.setRole(UserRole.kupac);
        c1.setDeleted(false);
        
        Customer c2 = new Customer();
        c2.setUsername("c2");
        c2.setPassword("123");
        c2.setName("Elena");
        c2.setSurname("Kitic");
        c2.setGender(beans.Gender.zenski);
        c2.setDateOfBirth(new Date(1998, Calendar.JANUARY, 2));
        c2.setRole(UserRole.kupac);
        c2.setDeleted(false);
        
        customers.put("c1", c1);
        customers.put("c2", c2);

        Gson gson = new Gson();
        FileWriter fw = new FileWriter("files/customers.json");
        gson.toJson(this.customers, fw);
        fw.flush();
        fw.close();
    }
    
    public void loadAll() throws FileNotFoundException {
        Gson gson = new Gson();
        Type token = new TypeToken<HashMap<String, Customer>>(){}.getType();
        BufferedReader br = new BufferedReader(new FileReader("files/customers.json"));
        this.customers = gson.fromJson(br, token);
    }


    private void loadTypes() throws FileNotFoundException {
        Gson gson = new Gson();
        Type token = new TypeToken<HashMap<String, CustomerType>>(){}.getType();
        BufferedReader br = new BufferedReader(new FileReader("files/customer_types.json"));
        this.customerTypes = gson.fromJson(br, token);
    }
    

    public void addCustomer(Customer customer) {
        customer.setPoints(0);
        customers.put(customer.getUsername(), customer);

        try {
            this.writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}