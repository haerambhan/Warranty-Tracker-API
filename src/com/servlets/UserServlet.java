package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handler.UserHandler;
import com.models.MyError;

@WebServlet("/Users/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserHandler.deleteUser(req, resp);
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
				UserHandler.getUsers(req, resp);
			} else {
				UserHandler.getUser(req, resp);
			}
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserHandler.addUser(req, resp);
		} catch (Exception e) {
			resp.getWriter().print(new JSONObject(new MyError(500, e.getClass().getName(), e.getMessage())));
		}
	}
}
