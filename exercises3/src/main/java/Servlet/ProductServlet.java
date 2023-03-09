package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import Model.JsonReturn;
import Model.Product;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("id");
		JsonReturn jsonReturn;
		if (productId == null ) {
			List<Product> products = ProductDAO.getInstance().getAll();
			jsonReturn = new JsonReturn(200, "Load all product success", products);
			
		} else {
			Product product = ProductDAO.getInstance().getById(Integer.parseInt(productId));
			if (product == null) {
				jsonReturn = new JsonReturn(404, "Not found", null);
			} else {
				jsonReturn = new JsonReturn(200, "Find product success", product);
			}
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonReturn.toJson());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		
		System.out.println(id);
		JsonReturn jsonReturn;
		
		int idVal = 0, priceVal = 0;
		
		try {
			idVal = Integer.parseInt(id);
			priceVal = Integer.parseInt(price);
			
			Product product = new Product(idVal, name, priceVal);
			
			boolean saveProduct = ProductDAO.getInstance().save(product);
			jsonReturn = (saveProduct) ? new JsonReturn(200, "Save success", null) : new JsonReturn(400, "id already exists", null);
		} catch (Exception e) {
			jsonReturn = new JsonReturn(400, "id or price must be numeric", null);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonReturn.toJson());
		out.flush();
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		JsonReturn jsonReturn;
		
		int idVal = 0, priceVal = 0;
		
		try {
			idVal = Integer.parseInt(id);
			priceVal = Integer.parseInt(price);
			
			Product product = new Product(idVal, name, priceVal);
			
			boolean saveProduct = ProductDAO.getInstance().update(product);
			jsonReturn = (saveProduct) ? new JsonReturn(200, "Update success", null) : new JsonReturn(404, "id not found", null);
		} catch (Exception e) {
			jsonReturn = new JsonReturn(400, "id or price must be numeric", null);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonReturn.toJson());
		out.flush();
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		JsonReturn jsonReturn;
		
		int idVal = 0;
		
		try {
			idVal = Integer.parseInt(id);
			
			boolean deleteProduct = ProductDAO.getInstance().remove(idVal);
			jsonReturn = (deleteProduct) ? new JsonReturn(200, "Delete success", null) : new JsonReturn(404, "id not found", null);
		} catch (Exception e) {
			jsonReturn = new JsonReturn(400, "id or price must be numeric", null);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonReturn.toJson());
		out.flush();
	}

}
