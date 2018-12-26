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

import bean.FollowListBean;
import dao.DBFollowList;


/**
 * Servlet implementation class FollowListServlet
 */
@WebServlet("/FollowListServlet")
public class FollowListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		int intUserId = 0;
		System.out.println(session.getAttribute("UserId"));

		try {
			if(request.getParameter("process").equals("fetch_follow_list")) {
				if(session.getAttribute("UserId") != null) {
					intUserId = (int)session.getAttribute("UserId");
					
					DBFollowList dbfl = new DBFollowList(intUserId);
					ArrayList<FollowListBean> arryFollow = new ArrayList<>();
					Gson gson = new Gson();

					arryFollow = dbfl.selectFollowList();

					response.setContentType("application/json;");
					response.setCharacterEncoding("UTF-8");
					PrintWriter pw = response.getWriter();
					pw.println("{\"followlist\" :"+gson.toJson(arryFollow)+"}");
				}
			}
			else if(request.getParameter("process").equals("delete_follow")) {
				System.out.println("HERE1");
				if(session.getAttribute("UserId") != null) {
					System.out.println("HERE2");
					intUserId = (int)session.getAttribute("UserId");

					if(request.getParameter("delete_follow_id")!= null) {
						int deleteFollowId = Integer.parseInt(request.getParameter("delete_follow_id"));

						DBFollowList dbfl = new DBFollowList(intUserId);
						Gson gson = new Gson();

						dbfl.deleteFollow(deleteFollowId);

						response.setContentType("application/json;");
						response.setCharacterEncoding("UTF-8");
						PrintWriter pw = response.getWriter();
						pw.println("1");
					}
				}
			}
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
