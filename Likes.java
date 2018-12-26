package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.LikesAction;

/**
 * Servlet implementation class LikesServlet
 */
@WebServlet("/Likes")
public class Likes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Likes() {
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
		//譁�蟄励さ繝ｼ繝�
		request.setCharacterEncoding("utf-8");
		//霆｢騾∝��
		String strDisp = "";
		//繧ｪ繝悶ず繧ｧ繧ｯ繝亥ｮ｣險�
        LikesAction likes = new LikesAction();
        //邱上＞縺�縺ｭ謨ｰ
        int allLikes = 0;

		if(request.getParameter("likes")!=null) {
            likes.getLikes(request, response);

            //System.out.print(request.getParameter("ProductID"));
            //strDisp = "goal.html";
        }else if(request.getParameter("dolikes")!=null) {
        	likes.checkLikesStatus(request);
        	likes.getLikes(request,  response);
        	//strDisp = "goal.html";

        }else if(request.getParameter("checklikes")!=null) {
        	likes.checkLikesStatusOnload(request, response);

        	//System.out.println(request.getParameter("checklikes"));
        	//System.out.println(request.getParameter("ProductID"));
        	//strDisp = "goal.html";
        }

		//RequestDispatcher disp = request.getRequestDispatcher(strDisp);
		//disp.forward(request, response);
	}

}
