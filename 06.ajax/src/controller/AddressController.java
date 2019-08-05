package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import model.Address;

@WebServlet(name="AddressController",urlPatterns = {"/address_move","/address_search"})
public class AddressController extends HttpServlet{
	
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
	
		
		if(action.equals("address_move")) {
			RequestDispatcher rd = req.getRequestDispatcher("/09-daumpostapi.jsp");
			rd.forward(req, resp);
			
		}else if(action.equals("address_search")) {
			AddressDAO dao = new AddressDAOImpl();
			Address address = new Address();
			
			
			
			address.setPostcode(Integer.parseInt(req.getParameter("postcode")));
			address.setRoadAddress(req.getParameter("roadAddress"));
			address.setJibunAddress(req.getParameter("jibunAddress"));
			address.setDetailAddress(req.getParameter("detailAddress"));
			address.setExtraAddress(req.getParameter("extraAddress"));
			
			dao.insert(address);
			
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}
		
		
		
	}
}
