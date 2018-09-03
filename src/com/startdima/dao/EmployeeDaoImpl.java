package com.startdima.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.startdima.pojo.Employee;
import com.startdima.utils.DBUtils;

public class EmployeeDaoImpl {
	
	/**
	 * 添加employee
	 * @param emp
	 * @return boolean
	 * @throws SQLException
	 */
    public boolean addEmployee(Employee emp) throws SQLException{
    	boolean flag = false;
    	String sql = "insert into t_employee values(null,?,?,?,?,?,?,?,1)";
    	Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getSex());
			pstmt.setString(3, emp.getBirthday());
			pstmt.setString(4, emp.getIdcard());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getAddress());
			pstmt.setString(7, emp.getPhoto());
			if(pstmt.executeUpdate() > 0) {
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
    
    /**
	 * 添加employee
	 * @param emp
	 * @return boolean
	 * @throws SQLException
	 */
    public boolean editEmployee(Employee emp) throws SQLException{
    	boolean flag = false;
    	String sql = "update t_employee set NAME=?,sex=?,BIRTHDAY=?,PHONE=?,ADDRESS=?,PHOTO=? where P_EMPLOYEE_ID=?";
    	Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getSex());
			pstmt.setString(3, emp.getBirthday());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getPhoto());
			pstmt.setString(7, emp.getPid());
			if(pstmt.executeUpdate() > 0) {
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
    
    /**
     * 获取employee信息
     * @return JSONArray
     * @throws SQLException
     */
    public JSONArray getEmployees() throws SQLException{
    	
    	Connection conn = null;
    	String sql = "select* from t_employee where active=1";
		PreparedStatement pstmt = null;

		JSONArray jsonArray = new JSONArray();  
		try {
			conn = DBUtils.getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				JSONObject jsonObj = new JSONObject(); 
				jsonObj.put("id", rs.getString("P_EMPLOYEE_ID"));  
				jsonObj.put("name", rs.getString("NAME"));  
				jsonObj.put("sex", rs.getInt("SEX"));  
				jsonObj.put("birthday", rs.getDate("BIRTHDAY"));
				jsonObj.put("idcard", rs.getString("IDCARD"));  
				jsonObj.put("phone", rs.getString("PHONE"));  
				jsonObj.put("address", rs.getString("ADDRESS"));  
				jsonObj.put("photo", rs.getString("PHOTO"));  
				jsonArray.add(jsonObj);             
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
    	return jsonArray;
    }
    
    /**
     * 删除employee
     * @param pid
     * @return boolean
     * @throws SQLException
     */
    public boolean deleteEmployee(String pid) throws SQLException {
		boolean flag = false;
		Connection conn = null;
    	String sql = "update t_employee set active=0 where P_EMPLOYEE_ID=?";
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, pid);
			if(pstmt.executeUpdate() > 0) {
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
