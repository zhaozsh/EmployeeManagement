package com.startdima.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;

import com.alibaba.fastjson.JSONArray;
import com.startdima.pojo.Employee;
import com.startdima.service.EmployeeService;

@WebServlet("/EmployeeServlet")
@MultipartConfig(location = "D://")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Employee emp = new Employee();
		EmployeeService employeeService = new EmployeeService();
		
		// 获取操作类型值type：add、delete、edit、srarch
		String type = request.getParameter("type");

		try {
			PrintWriter out = response.getWriter();
			// 新增或修改Employee信息
			if(type.equals("add") || type.equals("edit") ) {
				
				// 获取员工属性值并注入
				emp.setName(request.getParameter("name"));
				emp.setSex(Integer.parseInt(request.getParameter("sex")));
				emp.setBirthday(request.getParameter("birthday"));
				emp.setPhone(request.getParameter("phone"));
				emp.setAddress(request.getParameter("address"));
				
				// 设置存放员工证件照的路径
				String path=this.getServletContext().getRealPath("/");
				File f = new File(path+"upload\\");
	            if (!f.exists()) {// 若图片保存路径不存在则创建
	                f.mkdir();
	            }
		        Part p=request.getPart("file");
		        if(p.getContentType().contains("image")){
		        	ApplicationPart ap= (ApplicationPart) p;
		            String fname=ap.getSubmittedFileName();
		            p.write(path+"upload\\"+fname);
		            emp.setPhoto("upload/"+fname);
		        }
		        
				if(type.equals("add")){
					emp.setIdcard(request.getParameter("idcard"));
					if(employeeService.addEmployee(emp)) {
						out.println("添加成功！");
					}else {
						out.println("添加失败！");
					}
				}else{
					emp.setPid(request.getParameter("pid"));
					if(employeeService.editEmployee(emp)) {
						out.println("修改成功！");
					}else {
						out.println("修改失败！");
					}
				}
			}
			// 显示Employee信息
			else if(type.equals("search")) {
				JSONArray jsonArray = employeeService.getEmployees();
                out = response.getWriter();
                out.println(jsonArray);
			}
			// 删除Employee信息
			else if(type.equals("delete")) {
				String pid = request.getParameter("pid");
				if(employeeService.deleteEmployee(pid)) {
					out.println("删除成功！");
				}else {
					out.println("删除失败！");
				}
			}
			else {
				out.println("无效的type值！");
				System.out.println("无效的type值！");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
