package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gg.model.Test;
import com.gg.util.DbUtil;

public class TestDao {
	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	//查询所有试卷
		public List<Test> queryAllTests(){
			List<Test> testList = new ArrayList<Test>();
			String sql = "select * from test";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("testName");
					Test test = new Test(id,name);
					testList.add(test);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return testList;
		}
		
		//增加试卷
		public boolean saveTest(Test test){
			boolean flag = false;
			String sql = "insert into test(id,testName,time) values(?,?,?)";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, test.getId());
				pstmt.setString(2,test.getTestName());
				pstmt.setInt(3, test.getTime());
				pstmt.executeUpdate();
				flag = true;
			}catch(Exception e){
				e.printStackTrace();
				
			}finally{
				dbUtil.closeCon(con);
			}
			return flag;
		}
		//修改试卷
		public void updateTest(Test test){
			String sql = "update test set testName=?,time=? where id=?";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, test.getTestName());
				pstmt.setInt(2, test.getTime());
				pstmt.setInt(3, test.getId());
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
		
		//查询试卷
		public Test findTestById(int id){
			Test test = null;
			String sql = "select * from test where id=?";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				if(rs.next()){
					String name = rs.getString("testName");
					int time = rs.getInt("time");
					test = new Test(id,name,time);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return test;
		}
				
				
		//删除试卷
		public void deleteTest(int id){
			String sql = "delete from test where id=?";
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
		
		
		public List<Test> finTestsByPageNo(int pageNo){
			List<Test> testList = new ArrayList<Test>();
			//第n页的开始为    (页数-1)*页大小
			String sql = "select * from test limit "+(pageNo-1)*5+",5";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String testName = rs.getString("testName");
					int time = rs.getInt("time");
					Test test = new Test(id,testName,time);
					testList.add(test);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return testList;
			
		}
		
		public int queryTotalTestRecords(){
				int totalRecores = 0;
				String sql = "select count(*) as total from test";
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
		
		//通过试卷名称查找试卷
		public List<Test> finTestByName(String name){
			List<Test> testList = new ArrayList<Test>();
			String sql = "select * from test where testName like '%"+name+"%'";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String testName = rs.getString("testName");
					int time = rs.getInt("time");
					Test test = new Test(id,testName,time);
					testList.add(test);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return testList;
		}
		
}
