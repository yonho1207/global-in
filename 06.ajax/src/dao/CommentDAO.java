package dao;

import java.util.List;

import model.Comment;

public interface CommentDAO {
	List<Comment> selectAll();
	Comment insert(Comment comment);
	Comment selectByNum(int num);
	boolean deleteByNum(int num);
}
