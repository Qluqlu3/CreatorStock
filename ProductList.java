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

import bean.ProductDetailBean;
import bean.ProductListCommentBean;
import dao.DBProductDetail;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
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

		HttpSession session = request.getSession();

		int userId = 0;

			try {
			//session.getAttribute("userId") != null
			if(request.getParameter("process").equals("fetch_Product_list")) {
				userId = (int)session.getAttribute("UserId");
				int i = 0;
				int j = 0;
				boolean flg = false;
				boolean firstFlg = false;
				DBProductDetail dbpd = new DBProductDetail(userId, 0);
				ArrayList<ProductDetailBean> arrypdb = new ArrayList<>();
				ArrayList<ProductListCommentBean> arryplcb = new ArrayList<>();

				Gson gson = new Gson();

				arrypdb = dbpd.selectProductList();
				arryplcb = dbpd.selectLatestComment();

				response.setContentType("applicaytion/json;");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.println("{");
				pw.println("\"productList\" : ");
				pw.println("[");
				for(int k = 0;k < arrypdb.size();k++) {
					pw.println("{");
					pw.println("\"pro_id\" : \""+arrypdb.get(k).getProId()+"\",");
					pw.println("\"pro_name\" : \""+arrypdb.get(k).getProName()+"\",");
					pw.println("\"img_path\" : \""+arrypdb.get(k).getImgPath()+"\",");
					pw.println("\"data_path\" : \""+arrypdb.get(k).getDataPath()+"\",");
					pw.println("\"price\" : \""+arrypdb.get(k).getPrice()+"\",");
					pw.println("\"category\" : \""+arrypdb.get(k).getCategory()+"\",");
					pw.println(("\"profile\" : \""+arrypdb.get(k).getProContent()+"\",").replaceAll("(\\r|\\n)", ""));
					pw.println("\"user_name\" : \""+arrypdb.get(k).getUserName()+"\",");
					pw.println("\"ava_path\" : \""+arrypdb.get(k).getAvPath()+"\"");
					for(i = 0;i < arryplcb.size();i++) {
						if(arrypdb.get(k).getProId() == arryplcb.get(i).getProId()) {
							if (firstFlg == false) {
								pw.println(",");
								pw.println("\"commentList\" : [");
							}
							pw.println("{");
							pw.println("\"pro_id\" : \""+arryplcb.get(i).getProId()+"\",");
							pw.println("\"user_id\" : \""+arryplcb.get(i).getUserId()+"\",");
							pw.println("\"user_name\" : \""+arryplcb.get(i).getUserName()+"\",");
							pw.println(("\"comment\" : \""+arryplcb.get(i).getComment()+"\",").replaceAll("(\\r|\\n)", ""));
							pw.println("\"time\" : \""+arryplcb.get(i).getTime()+"\"");
							String str = "},";
							firstFlg = true;
							try {
								if(arryplcb.get(i).getProId() != arryplcb.get(i+1).getProId()) {
									str = str.substring(0, str.length()-1);
									str = str+"]},";
								}
							}
							catch(IndexOutOfBoundsException e) {
								str = str.substring(0, str.length()-1);
								str = str+"]}";
							}
							pw.println(str);
						}
						else {
							firstFlg = false;
						}
					}
				}
				pw.println("]");
				pw.println("}");
			}
			else if(request.getParameter("process").equals("delete_product")) {
				int productId = Integer.parseInt(request.getParameter("productId"));
				DBProductDetail dbpd = new DBProductDetail(userId, productId);

				dbpd.deleteProduct();
				
				response.setContentType("application/json;");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.println("1");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
