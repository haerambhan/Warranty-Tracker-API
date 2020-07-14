package com.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.database.DatabaseAccess;
import com.models.Category;

public class CategoryHandler {

	public static void getCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		DatabaseAccess db = DatabaseAccess.getInstance();
		Set<Category> categories;
		PrintWriter out = resp.getWriter();
		categories = db.getCategories();
		JSONArray response = new JSONArray(categories);
		out.print(response.toString());
		out.flush();
	}
}
