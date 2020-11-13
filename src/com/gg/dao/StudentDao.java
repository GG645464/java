package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.util.DbUtil;
import com.gg.util.StringUtil;



public class StudentDao {
	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	
	//添加学生
	public boolean saveStudent1(Student student){
		boolean flag = false;
		String sql = "insert into admin(name,age,password,classesId,typeId,id) values(?,?,?,?,3,?)";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getPassword());
			pstmt.setInt(4, student.getClasses().getId());
			pstmt.setInt(5, student.getId());
			pstmt.executeUpdate();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		
		return flag;
	}
	//修改学生
		public void updateStudent(Student student){
			String sql = "update admin set name=?,age=?,password=?,classesId=? where id=? and typeId=3";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getAge());
				pstmt.setString(3, student.getPassword());
				pstmt.setInt(4, student.getClasses().getId());
				pstmt.setInt(5, student.getId());
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
		
		//删除学生
		public void deleteStudent(int id){
			String sql = "delete from admin where id=? and typeId=3";
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
		
		//查询学生
		public Student findStudentById(int id){
			Student stu = null;
			String sql = "select * from admin where id=? and typeId=3";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				if(rs.next()){
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String pwd = rs.getString("password");
					int classesId = rs.getInt("classesId");
					Classes classes = new Classes(classesId);
					stu = new Student(id,name,age,pwd,classes);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return stu;
		}
		//查询所有学生
		public List<Student> findAllstudents(){
			List<Student> stuList = new ArrayList<Student>();
			String sql = "select a.id,a.name,age,password,b.id as cid,b.classesName as cname "
					+ "from admin as a,classes as b where a.clssses.id=b.id and typeId=3";
			
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String password = rs.getString("password");
					String classesName = rs.getString("cname");
					int cid = rs.getInt("cid");
					Classes classes = new Classes(cid,classesName);
					Student stu = new Student(id,name,age,password,classes);
					stuList.add(stu);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return stuList;
		}
		
		//查询指定页的学生信息
		public List<Student> finStudentsByPageNo(int pageNo){
			List<Student> stuList = new ArrayList<Student>();
			//第n页的开始为    (页数-1)*页大小
			String sql = "select a.id,a.name,age,password,b.id as cid,b.classesName as cname from admin as a,classes as b where typeId=3  and a.classesId=b.id ORDER BY a.id limit "+(pageNo-1)*5+",5";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String password = rs.getString("password");
					String classesName = rs.getString("cname");
					int cid = rs.getInt("cid");
					Classes classes = new Classes(cid,classesName);
					Student stu = new Student(id,name,age,password,classes);
					stuList.add(stu);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return stuList;
			
		}
		
		
		
		public int queryTotalRecords(){
				int totalRecores = 0;
				String sql = "select count(*) as total from admin where typeId=3";
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
		
		//通过条件查询学生
				public List<Student> finStudentsByMap(Map<String, String> map){
					List<Student> stuList = new ArrayList<Student>();
					StringBuffer sb=new StringBuffer("select * from admin a,classes b where a.classesId=b.id and typeId=3");
					String stuName = map.get("stuName");
					String stuAge = map.get("age");
					if(StringUtil.isNoEmpty(stuName)){
						sb.append(" and a.name like '%"+stuName+"%'");
					}
					if(StringUtil.isNoEmpty(stuAge)){
						sb.append(" and a.age='"+stuAge+"'");
					}
					try {
					PreparedStatement pstmt = con.prepareStatement(sb.toString());
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						int id = rs.getInt("a.id");
						String sname = rs.getString("name");
						int age = rs.getInt("age");
						String password = rs.getString("password");
						String classesName = rs.getString("classesName");
						int cid = rs.getInt("b.id");
						Classes classes = new Classes(cid,classesName);
						Student stu = new Student(id,sname,age,password,classes);
						stuList.add(stu);
					}
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					dbUtil.closeCon(con);
				}
					return stuList;
				}
		
		public int queryTotalRecordsMap(Map<String, String> map){
			StringBuffer sb=new StringBuffer("select count(*) as total from admin where typeId=3");
			String stuName = map.get("stuName");
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
