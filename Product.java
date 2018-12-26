package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ProductAction;
import tool.FileUpload;


@WebServlet("/Product")
@MultipartConfig(location="", maxFileSize=40000000)
public class Product extends HttpServlet {
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

		//文字コード
		request.setCharacterEncoding("utf-8");
		//転送先
		String strDisp = "";
		//オブジェクト宣言
		ProductAction product = new ProductAction(request, response);

        //商品投稿
		if(request.getParameter("register")!=null) {
			product.addProduct(request);
			product.addProductTags(request);
			strDisp = "index.html";
		}

		RequestDispatcher disp = request.getRequestDispatcher(strDisp);
		disp.forward(request, response);

	}


}
