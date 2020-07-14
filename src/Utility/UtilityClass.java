package Utility;

import javax.servlet.http.HttpServletRequest;

public class UtilityClass {
	
	public static int getId(HttpServletRequest req)
	{
		String temp = req.getPathInfo().substring(1);
		if (temp.endsWith("/")) {
		    temp = temp.substring(0, temp.length() - 1);
		}
		return Integer.parseInt(temp);
	}
}
