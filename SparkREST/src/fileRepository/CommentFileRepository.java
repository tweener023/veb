package fileRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import beans.Comment;
import beans.Customer;
import beans.SportObject;
import services.SportObjectService;

public class CommentFileRepository {
	private static String fileLocation = "files/comments.txt";
	private static SportObjectService sportObjectService = new SportObjectService();
	
	public CommentFileRepository() {
	}
	
	public ArrayList<Comment> getAllComments() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		BufferedReader in = null;
		String line;
		try {
			in = new BufferedReader(new FileReader(new File(fileLocation)));
			while((line = in.readLine()) != null) {
				Comment comment = makeCommentFromLine(line);
				comments.add(comment);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					
				}
			}
		}
		return comments;
	}
	
	public Comment saveComment(Comment comment) {
		try(FileWriter f = new FileWriter(fileLocation, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(getStringFromComment(comment));
			return comment;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		}
	}
	
	private String getStringFromComment(Comment comment) {
		return comment.getCustomer().getId() + ";" + comment.getSportObject().getId() + ";"
				+ comment.getText() + ";" + Integer.toString(comment.getGrade());
	}
	
	private Comment makeCommentFromLine(String line) {
		String[] lineItems = line.split(";");
		Customer customer = new Customer();
		SportObject sportObject = sportObjectService.findSportObject(lineItems[1]);
		
		customer.setId(lineItems[0]);
		
		String text = lineItems[2];
		int grade = Integer.parseInt(lineItems[3]);
		
		return new Comment(customer, sportObject, text, grade);
		
	}
}
