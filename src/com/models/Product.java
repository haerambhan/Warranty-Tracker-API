package com.models;

public class Product 
{		
	private int prodId;
	private int prodWP;
	private String prodName;
	private String prodBrand;
	private String prodCat;
	private String prodPD;
	
	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getProdWP() {
		return prodWP;
	}

	public void setProdWP(int prodWP) {
		this.prodWP = prodWP;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public String getProdCat() {
		return prodCat;
	}

	public void setProdCat(String prodCat) {
		this.prodCat = prodCat;
	}

	public String getProdPD() {
		return prodPD;
	}

	public void setProdPD(String prodPD) {
		this.prodPD = prodPD;
	}

	public Product(int prodId, int prodWP, String prodName, String prodBrand, String prodCat, String prodPD) {
		super();
		this.prodId = prodId;
		this.prodWP = prodWP;
		this.prodName = prodName;
		this.prodBrand = prodBrand;
		this.prodCat = prodCat;
		this.prodPD = prodPD;
	}
}
