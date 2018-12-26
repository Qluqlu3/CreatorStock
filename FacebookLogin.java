package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBUser;

/**
 * Servlet implementation class FacebookLogin
 */
@WebServlet("/FacebookLogin")
public class FacebookLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String MailAddress = "";
		String FacebookID = "";
		int intLogin = 0;
		int intUserId = 0;

		HttpSession session = request.getSession();
		try {
			if(request.getParameter("process").equals("login")) {
				//メールアドレス取得
				MailAddress = request.getParameter("email");
				FacebookID = request.getParameter("fbID");

				//コンストラクタ
				DBUser dbu = new DBUser(FacebookID, MailAddress);

				//ユーザが登録済みか検査
				intLogin = dbu.checkLogin();

				//ユーザIDをsessionに格納するために取得
				intUserId = dbu.insertUser(intLogin);
				//sessionに格納
				session.setAttribute("UserId", intUserId);

				response.setContentType("text/plain; charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.getWriter().println("1");
			}
			else if(request.getParameter("process").equals("logout")) {
				session.invalidate();
				response.setContentType("text/plain; charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.getWriter().println("2");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}



