package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import model.Comment;

@WebServlet(name="CommentController",urlPatterns = {"/comment_move","/comment_List","/comment_write","/deleteComment"})
public class CommentController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
		
		
	}

	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uri = req.getRequestURI();
		
		
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		req.setCharacterEncoding("utf-8");
		
		
		if(action.equals("comment_move")) {
			RequestDispatcher rd = req.getRequestDispatcher("/06-comment.jsp");
			rd.forward(req, resp);
		
		}else if(action.equals("comment_List")) {
			CommentDAO dao = new CommentDAOImpl();
			List<Comment> comment =dao.selectAll();
			
			req.setAttribute("comments", comment);
			
			RequestDispatcher rd = req.getRequestDispatcher("/07-commentList.jsp");
			rd.forward(req, resp);
			
			
		}
		else if(action.equals("comment_write")) {
			CommentDAO dao = new CommentDAOImpl();
			Comment comment = new Comment();
			
			comment.setWriter(req.getParameter("user_name"));
			comment.setContent(req.getParameter("comment"));
			
			Comment resultComment = dao.insert(comment);
			
			if(resultComment !=null) {
				req.setAttribute("result", true);
				req.setAttribute("message", "댓글 입력 성공");
			
			}else {
				req.setAttribute("result", false);
				req.setAttribute("message", "댓글 입력 실패");
			}
			
			req.setAttribute("comments", resultComment);
			
			RequestDispatcher rd = req.getRequestDispatcher("/08-commentItem.jsp");
			rd.forward(req, resp);
		}else if(action.equals("deleteComment")) {
			CommentDAO dao = new CommentDAOImpl();
			Comment comment = new Comment();
			
			int num =Integer.parseInt(req.getParameter("num"));
			
			boolean result = dao.deleteByNum(num);
			
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/07-commentList.jsp");
			rd.forward(req, resp);
			
			
		}
		
	}
}
