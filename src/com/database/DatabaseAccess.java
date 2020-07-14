package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.models.Brand;
import com.models.Category;
import com.models.Product;
import com.models.User;

public class DatabaseAccess {

	private static DatabaseAccess db = null;

	private DatabaseAccess() {
		// Private constructor for singleton class
	}

	public static Connection getConnection() throws SQLException {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/warrantytracker?useSSL=false", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return c;
	}

	public static DatabaseAccess getInstance() {

		if (db == null) {
			db = new DatabaseAccess();
		}
		return db;
	}

	public Set<Category> getCategories() throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Set<Category> categories = null;

		try {
			categories = new LinkedHashSet<Category>();
			con = getConnection();
			pt = con.prepareStatement("Select * from Category;");
			rs = pt.executeQuery();
			String p[] = new String[3];
			while (rs.next()) {
				for (int i = 1; i <= 2; i++) {
					p[i] = rs.getString(i);
				}
				int catId = Integer.parseInt(p[1]);
				String catName = p[2];
				categories.add(new Category(catId, catName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return categories;
	}

	public Set<User> getUsers() throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Set<User> users = null;

		try {
			users = new LinkedHashSet<User>();
			con = getConnection();
			pt = con.prepareStatement("Select * from User;");
			rs = pt.executeQuery();
			String p[] = new String[8];
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					p[i] = rs.getString(i);
				}
				int userId = Integer.parseInt(p[1]);
				String userName = p[2];
				String userEmail = p[3];
				String userPass = p[4];
				String userMobile = p[5];
				String userDp = p[6];
				users.add(new User(userId, userName, userEmail, userPass, userMobile, userDp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return users;
	}

	public User getUser(int id) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = getConnection();
			pt = con.prepareStatement("Select * from User where userId = ?;");
			pt.setInt(1, id);
			rs = pt.executeQuery();
			String p[] = new String[8];
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					p[i] = rs.getString(i);
				}
				int userId = Integer.parseInt(p[1]);
				String userName = p[2];
				String userEmail = p[3];
				String userPass = p[4];
				String userMobile = p[5];
				String userDp = p[6];
				user = new User(userId, userName, userEmail, userPass, userMobile, userDp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return user;
	}

	public User deleteUser(int userId) throws SQLException {

		Connection con = null;
		PreparedStatement pt = null;
		User user = this.getUser(userId);

		try {
			con = getConnection();
			pt = con.prepareStatement("Delete from User where userId = ?;");
			pt.setInt(1, userId);
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return user;
	}

	public User createUser(String userEmail, String userPass, String userName, String userDp, String userMobile)
			throws SQLException {

		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		User user = null;
		if (userDp == null)
			userDp = "";
		try {
			con = getConnection();
			pt = con.prepareStatement(
					"insert into User(userName,userEmail,userPass,userMobile,userDp) values(?,?,?,?,?)");
			pt.setString(1, userName);
			pt.setString(2, userEmail);
			pt.setString(3, userPass);
			pt.setInt(4, Integer.parseInt(userMobile));
			pt.setString(5, userDp);
			pt.executeUpdate();
			pt.close();
			pt = con.prepareStatement("select last_insert_id();");
			rs = pt.executeQuery();
			rs.next();
			user = this.getUser(rs.getInt(1));
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return user;
	}

	public Set<Brand> getBrands(String bName) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		bName = bName == null? "" : bName;
		Set<Brand> brands = null;

		try {
			brands = new LinkedHashSet<Brand>();
			con = getConnection();
			pt = con.prepareStatement("Select * from Brand where brandName like ?;");
			pt.setString(1, "%" + bName + "%");
			rs = pt.executeQuery();
			String p[] = new String[3];
			while (rs.next()) {
				for (int i = 1; i <= 2; i++) {
					p[i] = rs.getString(i);
				}
				int brandId = Integer.parseInt(p[1]);
				String brandName = p[2];
				brands.add(new Brand(brandId, brandName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return brands;
	}

	public Brand createBrand(String brandName) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Brand brand = null;
		try {
			con = getConnection();
			pt = con.prepareStatement("insert into Brand(brandName) values(?)");
			pt.setString(1, brandName);
			pt.executeUpdate();
			pt.close();
			pt = con.prepareStatement("select last_insert_id();");
			rs = pt.executeQuery();
			rs.next();
			brand = this.getBrand(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return brand;
	}

	private Brand getBrand(int brId) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Brand brand = null;

		try {
			con = getConnection();
			pt = con.prepareStatement("Select * from Brand where brandId = ?;");
			pt.setInt(1, brId);
			rs = pt.executeQuery();
			String p[] = new String[3];
			while (rs.next()) {
				for (int i = 1; i <= 2; i++) {
					p[i] = rs.getString(i);
				}
				int brandId = Integer.parseInt(p[1]);
				String brandName = p[2];
				brand = new Brand(brandId, brandName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return brand;
	}

	public Set<Product> getProducts(String userId, String sortBy, String sortType, String prodId, String prodName,
			String prodBrand, String prodCat) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Set<Product> products = null;

		try {
			if (userId == null)
				userId = "";
			if (sortBy == null || sortBy.equals(""))
				sortBy = "p.prodId";
			if (sortType == null)
				sortType = "";
			if (prodId == null)
				prodId = "";
			if (prodName == null)
				prodName = "";
			if (prodBrand == null)
				prodBrand = "";
			if (prodCat == null)
				prodCat = "";
			products = new LinkedHashSet<Product>();
			con = getConnection();
			String query = "Select p.prodId, p.prodName, b.brandName, c.catName, p.prodPD, p.prodWP from Product as p INNER JOIN Brand as b on p.brandId = b.brandId INNER JOIN Category as c on p.catId = c.catId where p.prodId like ? and p.prodName like ? and b.brandName like ? and c.catName like ? and p.userId like ?  order by "
					+ sortBy + " " + sortType + ";";
			pt = con.prepareStatement(query);
			pt.setString(1, "%" + prodId + "%");
			pt.setString(2, "%" + prodName + "%");
			pt.setString(3, "%" + prodBrand + "%");
			pt.setString(4, "%" + prodCat + "%");
			pt.setString(5, "%" + userId + "%");
			rs = pt.executeQuery();
			String p[] = new String[8];
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					p[i] = rs.getString(i);
				}
				int prodId2 = Integer.parseInt(p[1]);
				String prodName2 = p[2];
				String prodBrand2 = p[3];
				String prodCat2 = p[4];
				String prodPD2 = p[5];
				int prodWP2 = Integer.parseInt(p[6]);
				products.add(new Product(prodId2, prodWP2, prodName2, prodBrand2, prodCat2, prodPD2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return products;
	}

	public Product getProduct(int prodId) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Product product = null;

		try {

			con = getConnection();
			String query = "Select p.prodId, p.prodName, b.brandName, c.catName, p.prodPD, p.prodWP from Product as p INNER JOIN Brand as b on p.brandId = b.brandId INNER JOIN Category as c on p.catId = c.catId";
			pt = con.prepareStatement(query);
			rs = pt.executeQuery();
			String p[] = new String[8];
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					p[i] = rs.getString(i);
				}
				int prodId2 = Integer.parseInt(p[1]);
				String prodName2 = p[2];
				String prodBrand2 = p[3];
				String prodCat2 = p[4];
				String prodPD2 = p[5];
				int prodWP2 = Integer.parseInt(p[6]);
				product = new Product(prodId2, prodWP2, prodName2, prodBrand2, prodCat2, prodPD2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return product;
	}

	public Product createProduct(String prodName, int catId, int brandId, String prodPD, int prodWP, int userId)
			throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			con = getConnection();
			pt = con.prepareStatement(
					"insert into Product ( prodName, brandId, catId, prodPD, prodWP, userId) values (?,?,?,?,?,?);");
			pt.setString(1, prodName);
			pt.setInt(2, brandId);
			pt.setInt(3, catId);
			pt.setDate(4, java.sql.Date.valueOf(prodPD));
			pt.setInt(5, prodWP);
			pt.setInt(6, userId);
			pt.executeUpdate();
			pt.close();
			pt = con.prepareStatement("select last_insert_id();");
			rs = pt.executeQuery();
			rs.next();
			product = this.getProduct(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return product;
	}

	public Product deleteProduct(int prodId) throws SQLException {
		Connection con = null;
		PreparedStatement pt = null;
		Product product = this.getProduct(prodId);

		try {
			con = getConnection();
			pt = con.prepareStatement("Delete from Product where prodId = ?;");
			pt.setInt(1, prodId);
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return product;
	}

}
