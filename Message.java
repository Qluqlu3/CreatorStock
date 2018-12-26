package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.MessageBean;
import bean.MessageHistoryBean;
import dao.DBMessage;
/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//int userId = 111;
		int userId = 0;
		userId = (int)session.getAttribute("UserId");

		Gson gson = new Gson();

		DBMessage dbm = new DBMessage(userId);
		ArrayList<MessageBean> arryMessage = new ArrayList<MessageBean>();
		ArrayList<MessageHistoryBean> arryMhb = new ArrayList<MessageHistoryBean>();
		
		try {
			//&&session.getAttribute("userId") != null
			if(request.getParameter("process").equals("fetch_messages")) {
				//userId = (int)session.getAttribute("UserId");
				arryMessage = dbm.selectList(userId);
				response.setContentType("application/json;");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.println("{\"friendlist\" :"+gson.toJson(arryMessage)+"}");
				//pw.println("{\"result\" : \"" + param + "\"}");
				 //"{\"responseMessage\" : \"サーブレットからの返信です\"}";
			}

			else if(request.getParameter("process").equals("sent_messages")) {
				//userId = (int)session.getAttribute("userId");
				int toUserId = Integer.parseInt(request.getParameter("touser"));
				String message = request.getParameter("msg");
				dbm.insertMessage(toUserId, message);
			}

			else if(request.getParameter("process").equals("fetch_chatbox")) {
				//userId = (int)session.getAttribute("userId");
				int toUserId = Integer.parseInt(request.getParameter("touser"));
				arryMhb = dbm.getHistory(toUserId);
				response.setContentType("applicaytion/json;");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.println("{\"msglist\" :"+gson.toJson(arryMhb)+"}");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
