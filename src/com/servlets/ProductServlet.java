package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handler.ProductHandler;
import com.models.MyError;

@WebServlet("/Products/*")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			ProductHandler.deleteProduct(req, resp);
		} catch (SQLException e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String path = req.getPathInfo();
			System.out.println(path);
			if (path == null || path.equals("/")) {
				ProductHandler.getProducts(req, resp);
			} else {
				ProductHandler.getProduct(req, resp);
			}
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			ProductHandler.addProduct(req, resp);
		} catch (SQLException e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}
}
