package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handler.CategoryHandler;
import com.models.MyError;

@WebServlet("/Categories/*")
public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CategoryHandler.getCategories(req,resp);
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}
}
