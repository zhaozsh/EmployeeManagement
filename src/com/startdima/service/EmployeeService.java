package com.startdima.service;

import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.startdima.dao.EmployeeDaoImpl;
import com.startdima.pojo.Employee;

public class EmployeeService {

	EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
	
	/**
	 * 新增Employee
	 * @param emp
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addEmployee(Employee emp) throws SQLException {
		return employeeDaoImpl.addEmployee(emp);		
	}
	
	/**
	 * 修改Employee
	 * @param emp
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean editEmployee(Employee emp) throws SQLException {
		return employeeDaoImpl.editEmployee(emp);		
	}
	
	/**
	 * 查询全部Employees
	 * @return JSONArray
	 * @throws SQLException
	 */
	public JSONArray getEmployees() throws SQLException {
		return employeeDaoImpl.getEmployees();
	}
	
	/**
	 * 根据主键id删除employee
	 * @param pid
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteEmployee(String pid) throws SQLException {
		return employeeDaoImpl.deleteEmployee(pid);
	}
}
