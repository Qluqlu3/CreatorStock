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

import bean.MatchTagBean;
import bean.ProductCommentBean;
import bean.ProductDetailBean;
import dao.DBProductDetail;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet("/ProductDetail")
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		HttpSession session = request.getSession();

		//if(request.getAttribute("userId") != null  && request.getParameter("productId") != null)

		int userId = 0;
		try {
			if(request.getParameter("process").equals("fetch_ProductDetail")) {

				int productId = Integer.parseInt(request.getParameter("productid"));
				DBProductDetail dbpd = new DBProductDetail(userId, productId);
				Gson gson = new Gson();
				ArrayList<ProductDetailBean> arrypdb = new ArrayList<>();
				ArrayList<ProductCommentBean> arrypcb = new ArrayList<>();
				ArrayList<String> arryTag = new ArrayList<>();
				ArrayList<MatchTagBean> arryMatchTag = new ArrayList<>();

				arrypdb = dbpd.selectDetail();
				arrypcb = dbpd.selectComment();
				arryTag = dbpd.getTag();
				arryMatchTag = dbpd.matchTag();

				response.setContentType("applicaytion/json;");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.println("{");
				pw.println("\"productDetail\" :");
				pw.println("[");
				for(int k = 0;k < arrypdb.size();k++) {
					pw.println("{");
					pw.println("\"creator_id\" : \""+arrypdb.get(k).getCreatorId()+"\",");
					pw.println("\"pro_name\" : \""+arrypdb.get(k).getProName()+"\",");
					pw.println("\"img_path\" : \""+arrypdb.get(k).getImgPath()+"\",");
					pw.println("\"data_path\" : \""+arrypdb.get(k).getDataPath()+"\",");
					pw.println("\"price\" : \""+arrypdb.get(k).getPrice()+"\",");
					pw.println("\"category\" : \""+arrypdb.get(k).getCategory()+"\",");
					//String str = arrypdb.get(k).getProContent();
					//str = Pattern.compile(".*\" \".*").matcher(str).replaceAll("\n");
					pw.println(("\"profile\" : \""+arrypdb.get(k).getProContent()+"\",").replaceAll("(\\r|\\n)", ""));
					pw.println("\"user_name\" : \""+arrypdb.get(k).getUserName()+"\",");
					pw.println("\"ava_path\" : \""+arrypdb.get(k).getAvPath()+"\",");
				}
				pw.println(("\"comment\" :"+gson.toJson(arrypcb)+",").replaceAll("(\\r|\\n)", ""));
				pw.println("\"tag\" :"+gson.toJson(arryTag)+",");
				pw.println("\"machTagProduct\" :"+gson.toJson(arryMatchTag));
				pw.println("}]}");
			}

			else if(request.getParameter("process").equals("sent_comment") && request.getParameter("comment") != null) {
				String comment = request.getParameter("comment");
				int productId = Integer.parseInt(request.getParameter("productid"));

				DBProductDetail dbpd = new DBProductDetail(productId, userId);
				dbpd.insertComment(comment);
			}
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			response.setContentType("text/plain; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.getWriter().println("ERROR");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
