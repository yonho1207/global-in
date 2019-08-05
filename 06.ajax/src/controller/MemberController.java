package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAOImpl;

import model.Member;






@WebServlet(name="MemberController",urlPatterns = {"/member_input","/idcheck"})
public class MemberController extends HttpServlet{

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
			System.out.println(uri);
			
			int lastIndex = uri.lastIndexOf("/");
			String action = uri.substring(lastIndex+1);
			
			req.setCharacterEncoding("utf-8");
			
			
			if(action.equals("member_input")) {
				RequestDispatcher rd = req.getRequestDispatcher("/04-idcheck.jsp");
				rd.forward(req, resp);
				
				
			}else if(action.equals("idcheck")) {
				String id = req.getParameter("id");
				
				MemberDAOImpl dao = new MemberDAOImpl();
				System.out.println(id);
				int cnt = dao.idcheck(id);
				System.out.println(cnt);
				
				if(cnt>0) {
					
					req.setAttribute("message","중복된 아이디입니다");
				}else if(cnt<1) {
					
					req.setAttribute("message","사용 가능한 아이디입니다");
				}
				
			
				RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
				rd.forward(req, resp);
				
				
				
			}

		}
}