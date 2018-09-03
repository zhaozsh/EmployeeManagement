package com.startdima.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.startdima.pojo.User;
import com.startdima.utils.DBUtils;

public class LoginDaoImpl {

	/**
	 * 用户登陆
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean login(User user) throws SQLException {
		boolean flag = false;
		String sql = "select * from t_user where username=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn = DBUtils.getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = true;
			}
	    } 
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    } 
		finally 
		{
	    	if(pstmt != null) {
	    		pstmt.close();
	    	}
	    	if(conn != null) {
	    		conn.close();
	    	}
		}
		return flag;
	}
}
