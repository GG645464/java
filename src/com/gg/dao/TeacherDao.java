package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gg.model.Admin;
import com.gg.model.Teacher;
import com.gg.util.DbUtil;
import com.gg.util.StringUtil;

public class TeacherDao {
	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	
		
		//修改老师
		public void updateTeacher(Teacher teacher){
			String sql = "update admin set name=?,age=?,password=? where id=? and typeId=2";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, teacher.getName());
				pstmt.setInt(2, teacher.getAge());
				pstmt.setString(3, teacher.getPassword());
				pstmt.setInt(4, teacher.getId());
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
		
		//添加老师
		public boolean saveTeacher1(Teacher teacher){
			boolean flag = false;
			String sql = "insert into admin(name,age,password,typeId,id) values(?,?,?,2,?)";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, teacher.getName());
				pstmt.setInt(2, teacher.getAge());
				pstmt.setString(3, teacher.getPassword());
				pstmt.setInt(4, teacher.getId());
				pstmt.executeUpdate();
				flag = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			
			return flag;
		}
		
		//删除老师
		public void deleteTeacher(int id){
			String sql = "delete from admin where id=? and typeId=2";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
		
		//查询老师
		public Teacher findTeacherById(int id){
			Teacher teacher = null;
			String sql = "select * from admin where id=? and typeId=2";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				if(rs.next()){
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String pwd = rs.getString("password");
					teacher = new Teacher(id,name,age,pwd);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return teacher;
		}
		//查询所有老师
		public List<Teacher> findAllteachers(){
			List<Teacher> teacherList = new ArrayList<Teacher>();
			String sql = "select * from admin where typeId=2";
			
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String password = rs.getString("password");
					Teacher teacher = new Teacher(id,name,age,password);
					teacherList.add(teacher);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return teacherList;
		}
		
		//查询指定页的老师信息
		public List<Teacher> finTeachersByPageNo(int pageNo){
			List<Teacher> teacherList = new ArrayList<Teacher>();
			//第n页的开始为    (页数-1)*页大小
			String sql = "select * from admin where typeId=2 limit "+(pageNo-1)*5+",5";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String password = rs.getString("password");
					Teacher teacher = new Teacher(id,name,age,password);
					teacherList.add(teacher);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return teacherList;
			
		}
		
		
		
		public int queryTotalRecords(){
				int totalRecores = 0;
				String sql = "select count(*) as total from admin where typeId=2";
				try{
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs =  pstmt.executeQuery();
					if(rs.next()){
						totalRecores = rs.getInt("total");
					}
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			
			return totalRecores;
		
		}
		
		//通过条件查询老师
		public List<Teacher> finTeachersByMap(Map<String, String> map){
			List<Teacher> teacherList = new ArrayList<Teacher>();
			StringBuffer sb=new StringBuffer("select * from teacher");
			String stuName = map.get("teacherName");
			String stuAge = map.get("age");
			if(StringUtil.isNoEmpty(stuName)){
				sb.append(" and name like '%"+stuName+"%'");
			}
			if(StringUtil.isNoEmpty(stuAge)){
				sb.append(" and age='"+stuAge+"'");
			}
			try {
			PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String tname = rs.getString("name");
				int age = rs.getInt("age");
				String password = rs.getString("password");
				Teacher teacher = new Teacher(id,tname,age,password);
				teacherList.add(teacher);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
			return teacherList;
		}
		
		//指定总记录数
		public int queryTotalRecordsMap(Map<String, String> map){
			StringBuffer sb=new StringBuffer("select count(*) as total from admin where typeId=2");
			String stuName = map.get("teacherName");
			String stuAge = map.get("age");
			int totalRecores = 0;
			if(StringUtil.isNoEmpty(stuName)){
				sb.append(" and name like '%"+stuName+"%'");
			}
			if(StringUtil.isNoEmpty(stuAge)){
				sb.append(" and age='"+stuAge+"'");
			}
			try {
				PreparedStatement pstmt = con.prepareStatement(sb.toString());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					totalRecores = rs.getInt("total");
				}
				rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		
		return totalRecores;
		
	}
}
