package com.gg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gg.model.Student;
import com.gg.model.Subject;
import com.gg.model.Test;
import com.gg.util.DbUtil;


public class SubjectDao {
	private DbUtil dbUtil = new DbUtil();
	Connection con = dbUtil.getCon();
	
	//添加学生
		public void saveSubject(Subject subject){
			String sql = "insert into subject(title,optionsA,optionsB,optionsC,optionsD,answer,score,`desc`,testId,id) values(?,?,?,?,?,?,?,?,?,?)";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, subject.getTitle());
				pstmt.setString(2, subject.getOptionsA());
				pstmt.setString(3, subject.getOptionsB());
				pstmt.setString(4, subject.getOptionsC());
				pstmt.setString(5, subject.getOptionsD());
				pstmt.setString(6, subject.getAnswer());
				pstmt.setInt(7, subject.getScore());
				pstmt.setString(8, subject.getDesc());
				pstmt.setInt(9, subject.getTest().getId());
				pstmt.setInt(10, subject.getId());
				
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
		
		//修改试题
		public void updateSubject(Subject subject){
			String sql = "update subject set title=?,optionsA=?,optionsB=?,optionsC=?,optionsD=?,answer=?,score=?,`desc`=?,testId=? where id=?";
			try{
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, subject.getTitle());
				pstmt.setString(2, subject.getOptionsA());
				pstmt.setString(3, subject.getOptionsB());
				pstmt.setString(4, subject.getOptionsC());
				pstmt.setString(5, subject.getOptionsD());
				pstmt.setString(6, subject.getAnswer());
				pstmt.setInt(7, subject.getScore());
				pstmt.setString(8, subject.getDesc());
				pstmt.setInt(9, subject.getTest().getId());
				pstmt.setInt(10, subject.getId());
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
		}
	
	
	//查询指定试卷的所有试题
	public List<Subject> findAllSubjects(int id){
		List<Subject> subjectList = new ArrayList<Subject>();
		String sql = "SELECT a.id,a.time,a.testName,b.id as subjectId,title,optionsA,optionsB,optionsC,optionsD,answer,score,`desc` from test a,subject b where a.Id=b.testId and a.id="+id+" order by subjectId asc";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				int subjectId = rs.getInt("subjectId");
				String title = rs.getString("title");
				String optionsA = rs.getString("optionsA");
				String optionsB = rs.getString("optionsB");
				String optionsC = rs.getString("optionsC");
				String optionsD = rs.getString("optionsD");
				String answer = rs.getString("answer");
				int score = rs.getInt("score");
				String desc = rs.getString("desc");
				
				int testId = rs.getInt("a.id");
				String testName = rs.getString("testName");
				int time = rs.getInt("time");
				
				Test test = new Test(testId, testName, time);
				Subject subject = new Subject(subjectId, title, optionsA, optionsB, optionsC, optionsD, answer, score,test,desc);
				subjectList.add(subject);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbUtil.closeCon(con);
		}
		return subjectList;
	}
	
	//删除试题
	public void deleteSubject(int id){
		String sql = "delete from subject where id=?";
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
	
	//查询试卷下的试题
	public Subject findSubjectById(int id){
		Subject subject = null;
		String sql = "select * from subject where id=?";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()){
				String title = rs.getString("title");
				String optionsA = rs.getString("optionsA");
				String optionsB = rs.getString("optionsB");
				String optionsC = rs.getString("optionsC");
				String optionsD = rs.getString("optionsD");
				String answer = rs.getString("answer");
				int score = rs.getInt("score");
				String desc = rs.getString("desc");
				
				int testId = rs.getInt("testId");
				
				Test test = new Test(testId);
				subject = new Subject(id, title, optionsA, optionsB, optionsC, optionsD, answer, score,test,desc);
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				dbUtil.closeCon(con);
			}
			return subject;
		}	
	
	//查询试卷数量
	public int queryTotalRecords(int id){
		int totalRecores = 0;
		String sql = "SELECT count(*) as total from test a,subject b where a.Id=b.testId and a.id="+id;
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
