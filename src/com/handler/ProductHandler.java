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
import com.models.Product;

import Utility.UtilityClass;

public class ProductHandler {

	public static void getProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String prodId = req.getParameter("prodId");
		String prodName = req.getParameter("prodName");
		String prodBrand = req.getParameter("prodBrand");
		String prodCat = req.getParameter("prodCat");
		String sortType = req.getParameter("sortType");
		String sortBy = req.getParameter("sortBy");
		String userId = req.getParameter("userId");

		DatabaseAccess db = DatabaseAccess.getInstance();
		Set<Product> products = db.getProducts(userId, sortBy, sortType, prodId, prodName, prodBrand, prodCat);
		JSONArray response = new JSONArray(products);
		PrintWriter out = resp.getWriter();
		out.print(response.toString());
		out.flush();
	}

	public static void getProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

		DatabaseAccess db = DatabaseAccess.getInstance();
		int prodId = UtilityClass.getId(req);
		Product product = db.getProduct(prodId);
		JSONObject response = new JSONObject(product);
		PrintWriter out = resp.getWriter();
		out.print(response.toString());
		out.flush();

	}

	public static void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

		DatabaseAccess db = DatabaseAccess.getInstance();
		try {
			String body = req.getReader().readLine();
			JSONObject obj = new JSONObject(body);
			String prodName = obj.getString("prodName");
			int catId = obj.getInt("catId");
			int brandId = obj.getInt("brandId");
			String prodPD = obj.getString("prodPD");
			int prodWP = obj.getInt("prodWP");
			int userId = obj.getInt("userId");
			Product product = db.createProduct(prodName, catId, brandId, prodPD, prodWP, userId);
			JSONObject response = new JSONObject(product);
			PrintWriter out = resp.getWriter();
			out.print(response.toString());
			out.flush();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {

		DatabaseAccess db = DatabaseAccess.getInstance();
		int prodId = UtilityClass.getId(req);
		Product product = db.deleteProduct(prodId);
		JSONObject response = new JSONObject(product);
		PrintWriter out = resp.getWriter();
		out.print(response);
		out.flush();
	}
}
