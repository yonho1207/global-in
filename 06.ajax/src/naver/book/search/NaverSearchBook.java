package naver.book.search;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="NaverSearchBook",urlPatterns= {"/booksearch"})
public class NaverSearchBook extends HttpServlet{

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String result = null;
    	String clientId = "G6Rbx08lusdckn5VkSjL";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "FRvCq6xNnD";//애플리케이션 클라이언트 시크릿값";
        try {
            
        	String text = URLEncoder.encode(req.getParameter("keyword"), "UTF-8");
            System.out.println(text);
        	
        	String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            result = response.toString();
            
			
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
        req.setAttribute("result", result);
        
        RequestDispatcher rd = req.getRequestDispatcher("/001-bookItem.jsp");
		rd.forward(req, resp);
	}

	
}