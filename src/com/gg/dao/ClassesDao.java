package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.util.DbUtil;

public class ClassesDao {

	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	//查询所有班级
	public List<Classes> queryAllClasses(){
		List<Classes> cList = new ArrayList<Classes>();
		String sql = "select * from classes";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("classesName");
				Classes classes = new Classes(id,name);
				cList.add(classes);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		return cList;
	}
	
	//增加班级
	public void saveClasses(Classes classes){
		String sql = "insert into classes(id,classesName) values(?,?)";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, classes.getId());
			pstmt.setString(2,classes.getName());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
	}
	//修改班级
	public void updateClasses(Classes classes){
		String sql = "update classes set classesName=? where id=?";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, classes.getName());
			pstmt.setInt(2, classes.getId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
	}
	
	//查询班级
			public Classes findClassById(int id){
				Classes cls = null;
				String sql = "select * from classes where id=?";
				try{
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, id);
					ResultSet rs =  pstmt.executeQuery();
					if(rs.next()){
						String name = rs.getString("classesName");
						cls = new Classes(id,name);
					}
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					dbUtil.closeCon(con);
				}
				return cls;
			}
			
			
	//删除班级
	public void deleteClasses(int id){
		String sql = "delete from classes where id=?";
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
	
	public List<Classes> finClassesByPageNo(int pageNo){
		List<Classes> classList = new ArrayList<Classes>();
		//第n页的开始为    (页数-1)*页大小
		String sql = "select * from classes limit "+(pageNo-1)*5+",5";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String classesName = rs.getString("classesName");
				Classes classes = new Classes(id,classesName);
				classList.add(classes);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		return classList;
		
	}
	
	public int queryTotalClassRecords(){
			int totalRecores = 0;
			String sql = "select count(*) as total from classes";
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
}
