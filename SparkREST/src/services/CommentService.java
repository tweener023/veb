package services;

import java.util.ArrayList;

import beans.Comment;
import fileRepository.CommentFileRepository;

public class CommentService {
	private static CommentFileRepository cfr = new CommentFileRepository();
	
	public CommentService() {
	}
	
	public ArrayList<Comment> getAllComments() {
		return cfr.getAllComments();
	}
	
	public Comment saveComment(Comment comment) {
		return cfr.saveComment(comment);
	}
	
	public ArrayList<Comment> getAllCommentsOfCustomer(String customerId) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for(Comment comment : this.getAllComments())
				if(comment.getCustomer().getId().equals(customerId))
						comments.add(comment);
		return comments;
	}
	
	public ArrayList<Comment> getAllCommentsOfSportObject(String sportObjectId) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for(Comment comment : this.getAllComments())
				if(comment.getSportObject().getId().equals(sportObjectId))
					comments.add(comment);
		return comments;
	}
	
	public double getAvgGradeOfSportObject(String id) {
		ArrayList<Comment> comments = this.getAllCommentsOfSportObject(id);
		double numOfComment = 0.00;
		double sumOfGrade = 0.00;
		
		if(comments.isEmpty()) {
			return 0;
		}
		
		for(Comment comment : comments) {
			numOfComment++;
			sumOfGrade += comment.getGrade();
		}
		
		return sumOfGrade / numOfComment;
		
	}
}
