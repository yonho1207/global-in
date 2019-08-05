package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Comment;



public class CommentDAOImpl extends BaseDAO implements CommentDAO {
	
	public static final String Comment_SELECT_ALL ="select * from comments order by num";
	
	public static final String Comment_INSERT_SQL = "INSERT INTO comments VALUES(seqComment.nextval,?,?,sysdate)";
	
	public static final String COMMENT_SELECT_SEQCURRVAL_SQL = "select seqComment.currval as num from dual";
	
	public static final String COMMENT_SELECT_BY_NUM = "select * from comments where num=?";
	
	public static final String COMMENT_DELETE_SQL = "delete from comments where num=?";
	
	
	
	public boolean deleteByNum(int num) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COMMENT_DELETE_SQL);
			preparedStatement.setInt(1,num);
			
			int rowCount = preparedStatement.executeUpdate();
		
			if(rowCount>0) {
			result = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			closeDBObjects(null,preparedStatement,connection);
			
		}
		
		
		return result;
		
		
	}
		
		
		
		
		
	
	
	public Comment selectByNum(int num) {
		Comment comment = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COMMENT_SELECT_BY_NUM);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				comment=new Comment();
				
				comment.setNum(resultSet.getInt("num"));
				comment.setWriter(resultSet.getString("writer"));
				comment.setContent(resultSet.getString("content"));
				comment.setDatetime(resultSet.getString("datetime"));
				
				
				
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();

		} finally {

			closeDBObjects(resultSet, preparedStatement, connection);
		}
		
		
		
		
		return comment;
		
		
	}
	
		
		
		
		
		
	
	
	public List<Comment> selectAll(){
		List<Comment> commentList = new ArrayList<Comment>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(Comment_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Comment comment = new Comment();
				comment.setNum(resultSet.getInt("num"));
				comment.setWriter(resultSet.getString("writer"));
				comment.setContent(resultSet.getString("content"));
				comment.setDatetime(resultSet.getString("datetime"));

				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			closeDBObjects(resultSet, preparedStatement, connection);
		}
		
		
		
		
		return commentList;
		
	}
		
		
		
		
	
	
	
	public Comment insert(Comment comment) {
		Comment selectByComment = null;
		
	
		Connection connection =null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(Comment_INSERT_SQL);
			
			
			preparedStatement.setString(1,comment.getWriter());
			preparedStatement.setString(2,comment.getContent());
			
			
			
			
			
			int rowCount = preparedStatement.executeUpdate();
			
			if(rowCount>0) {
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(COMMENT_SELECT_SEQCURRVAL_SQL);
				if(resultSet.next()) {
					selectByComment = selectByNum(resultSet.getInt("num"));
					//System.out.println(selectByComment.toString());
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeDBObjects(resultSet,statement,null);
			closeDBObjects(null,preparedStatement,connection);
			
		}
		return selectByComment;
		
	}
}
	


