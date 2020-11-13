package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gg.model.Admin;
import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.model.Teacher;
import com.gg.model.Type;
import com.gg.util.DbUtil;


public class AdminDao {
	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	//管理员登录
	public boolean isExistsAdmin(Admin admin){
		boolean isExists = false;
		String sql="select * from admin where name=? and password=? and typeId=1";
		
		try{

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
					isExists = true;
				}
					
		}catch(Exception e){
			e.printStackTrace();
		}
		return isExists;
	}
	
	//查询管理员
	public Admin findAdmin(Admin admin){
		Admin resultAdmin = null;
		String sql = "select * from admin where name=? and password=? and typeId=1";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassword());
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()){
				String account = rs.getString("name");
				int id = rs.getInt("id");
				String pwd = rs.getString("password");
				resultAdmin = new Admin(id,account,pwd);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		return resultAdmin;
	}
	
	//更新管理员信息
	public void update(Admin admin) {
		String sql = "update admin set name=?,password=?,age=? where id=? and typeId=1";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassword());
			pstmt.setInt(3, admin.getAge());
			pstmt.setInt(4, admin.getId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
	}
	
	//查询管理员
	public Admin findAdminById(int id){
		Admin admin = null;
		String sql = "select * from admin where id=? and typeId=1";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				int typeId = rs.getInt("typeId");
				String pwd = rs.getString("password");
				Type type = new Type(typeId,"管理员");
				admin = new Admin(id, name, pwd, type);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		return admin;
	}
	
	//学生登录
		public boolean isExistsStudent(Student student){
			boolean isExists = false;
			String sql="select * from admin where name=? and password=? and typeId=3";
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getPassword());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
						isExists = true;
					}
						
			}catch(Exception e){
				e.printStackTrace();
			}
			return isExists;
		}
		
		//查询学生
		public Student findStudent(Student student){
			Student resultStudent = null;
			String sql = "select * from admin where name=? and password=? and typeId=3";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getPassword());
				ResultSet rs =  pstmt.executeQuery();
				if(rs.next()){
					String name = rs.getString("name");
					int id = rs.getInt("id");
					String pwd = rs.getString("password");
					int age = rs.getInt("age");
					int classesId = rs.getInt("classesId");
					Classes classes = new Classes(classesId);
					resultStudent = new Student(id, name, age, pwd, classes);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return resultStudent;
		}
			
		
		//添加学生(学生注册)
		public boolean saveStudent(Student student){
			boolean flag = false;
			String sql = "insert into admin(name,age,password,classesId,typeId) values(?,?,?,?,3)";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getAge());
				pstmt.setString(3, student.getPassword());
				pstmt.setInt(4, student.getClasses().getId());
				pstmt.executeUpdate();
				flag = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			
			return flag;
		}
		
		//老师登录
				public boolean isExists(Teacher teacher){
					boolean isExists = false;
					String sql="select * from admin where name=? and password=? and typeId=2";
					try {
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, teacher.getName());
						pstmt.setString(2, teacher.getPassword());
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()){
								isExists = true;
							}
								
					}catch(Exception e){
						e.printStackTrace();
					}
					return isExists;
				}
				
				public Teacher findTeacher(Teacher teacher){
					Teacher resultTeacher = null;
					String sql = "select * from admin where name=? and password=? and typeId=2";
					try{
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, teacher.getName());
						pstmt.setString(2, teacher.getPassword());
						ResultSet rs =  pstmt.executeQuery();
						if(rs.next()){
							String name = rs.getString("name");
							int id = rs.getInt("id");
							int age = rs.getInt("age");
							String pwd = rs.getString("password");
							resultTeacher = new Teacher(id,name,age,pwd);
						}
						rs.close();
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						dbUtil.closeCon(con);
					}
					return resultTeacher;
				}
				
				//通过用户名查询老师
				public boolean findTeacherByName(String name) {
					boolean flag = false;
					String sql = "select * from admin where name=? and typeId=2";
					try{
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, name);
						ResultSet rs =  pstmt.executeQuery();
						if(rs.next()){
							flag = true;
						}
						rs.close();
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						dbUtil.closeCon(con);
					}
					return flag;
				}
				
				//添加老师(注册)
				public boolean saveTeacher(Teacher teacher){
					boolean flag = false;
					String sql = "insert into admin(name,age,password,typeId) values(?,?,?,2)";
					try{
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, teacher.getName());
						pstmt.setInt(2, teacher.getAge());
						pstmt.setString(3, teacher.getPassword());
						pstmt.executeUpdate();
						flag = true;
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						dbUtil.closeCon(con);
					}
					
					return flag;
				}
				
		
}
