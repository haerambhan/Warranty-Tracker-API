package com.handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.database.DatabaseAccess;
import com.models.User;

import Utility.UtilityClass;

public class UserHandler {

	public static void getUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		
		DatabaseAccess db = DatabaseAccess.getInstance();
		Set<User> users;
		PrintWriter out = resp.getWriter(); 
		users = db.getUsers();
		JSONArray response = new JSONArray(users);
		out.print(response.toString());
		out.flush();
	}

	public static void getUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		DatabaseAccess db = DatabaseAccess.getInstance();
		User user = null;
		int userId = UtilityClass.getId(req);
		PrintWriter out = resp.getWriter(); 
		user = db.getUser(userId);
		JSONObject response = new JSONObject(user);
		out.print(response.toString());
		out.flush();
	}

	public static void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, JSONException {
		
		DatabaseAccess db = DatabaseAccess.getInstance();	
		try {
			String body = req.getReader().readLine();
			System.out.println(body);
			JSONObject obj = new JSONObject(body);
			String userEmail = obj.getString("userEmail");
			String userPass = obj.getString("userPass");
			String userName = obj.getString("userName");
			String userDp = obj.getString("userDp");
			String userMobile = obj.getString("userMobile");
			User user = db.createUser(userEmail,userPass, userName, userDp, userMobile);
			JSONObject response = new JSONObject(user);
	    	PrintWriter out = resp.getWriter(); 
			out.print(response.toString());
			out.flush();
			
		} catch (JSONException e) {
			e.printStackTrace();
			throw e;
			
		}
	}

	public static void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		
		DatabaseAccess db = DatabaseAccess.getInstance();
		int userId = UtilityClass.getId(req);
		User user = db.deleteUser(userId);
		JSONObject response = new JSONObject(user);
    	PrintWriter out = resp.getWriter(); 
		out.print(response.toString());
		out.flush();
	}
}
