package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handler.BrandHandler;
import com.models.MyError;

@WebServlet("/Brands/*")
public class BrandServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BrandHandler.getBrands(req,resp);
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}	
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BrandHandler.addBrand(req,resp);
		} catch (SQLException e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}

}
