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
import com.models.Brand;

public class BrandHandler {

	public static void getBrands(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		
		String brandName = req.getParameter("brandName");
		DatabaseAccess db = DatabaseAccess.getInstance();
		Set<Brand> brands = db.getBrands(brandName);
		JSONArray response = new JSONArray(brands);	
    	PrintWriter out = resp.getWriter(); 
		out.print(response.toString());
		out.flush();
		
	}

	public static void addBrand(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		DatabaseAccess db = DatabaseAccess.getInstance();	
		try {
			String body = req.getReader().readLine();
			JSONObject obj = new JSONObject(body);
			String brandName = obj.getString("brandName");		
			Brand brand = db.createBrand(brandName);
			JSONObject response = new JSONObject(brand);
	    	PrintWriter out = resp.getWriter(); 
			out.print(response.toString());
			out.flush();
			
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

}
