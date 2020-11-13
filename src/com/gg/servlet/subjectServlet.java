package com.gg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.SubjectDao;
import com.gg.dao.TestDao;
import com.gg.model.Student;
import com.gg.model.Subject;
import com.gg.model.Test;



@WebServlet("/admin/subjectServlet")
public class subjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestDao testDao = new TestDao(); 
    private SubjectDao subjectDao = new SubjectDao();
	
    public subjectServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		
		if("list".equals(param)) {
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			List<Subject> subjectList =  subjectDao.findAllSubjects(testId);
			request.setAttribute("subjectList", subjectList);
			
			//��������õ��������������
			int count = 0;
			int totalscore = 0;
			for(int i=0;i<subjectList.size();i++) {
				count += 1;
				totalscore += subjectList.get(i).getScore();
			}
			
			request.setAttribute("count",count);
			request.setAttribute("totalscore",totalscore);
			
			//ѧ���û�����ҳ��
			try {
				Student student = (Student)request.getSession().getAttribute("admin");
				String typeName = student.getType().getTypeName();
				String result = request.getParameter("answer");
				if("ѧ��".equals(typeName)) {
					if("t".equals(result)) {
						request.getRequestDispatcher("../student/stu_result.jsp?testId="+testId).forward(request, response);
					}else {
						request.getRequestDispatcher("../student/stu_subject_index.jsp?testId="+testId).forward(request, response);
					}
					
				//�����û����Խ���
				}else {
					//ת��index.jsp
					request.getRequestDispatcher("subject_index.jsp?testId="+testId).forward(request, response);
				}
			}catch (Exception e) {
				
			}
			try {
				request.getRequestDispatcher("subject_index.jsp?testId="+testId).forward(request, response);
			}catch (Exception e) {
				
			}
//			request.getRequestDispatcher("subject_index.jsp?testId="+testId).forward(request, response);
			
		}else if("addForm".equals(param)) {
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
			request.getRequestDispatcher("subject_addForm.jsp?testId="+testId).forward(request, response);
		}else if("add".equals(param)){
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
//			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String optionsA = request.getParameter("optionsA");
			String optionsB = request.getParameter("optionsB");
			String optionsC = request.getParameter("optionsC");
			String optionsD = request.getParameter("optionsD");
			int score = Integer.parseInt(request.getParameter("score"));
			String answer = request.getParameter("answer");
			String desc = request.getParameter("desc");
			

			Subject subject = new Subject(title, optionsA, optionsB, optionsC, optionsD, answer, score, test, desc);
			subjectDao.saveSubject(subject);
			
			request.getRequestDispatcher("subjectServlet?param=list&testId="+testId).forward(request, response);
			
		}else if("submit".equals(param)) {
			
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
			List<Subject> subjectList = subjectDao.findAllSubjects(testId);
			request.setAttribute("subjectList", subjectList);
			String[] answer = new String[subjectList.size()];
			String[] result = new String[subjectList.size()];
			
			//(>>�޸ķ�װҵ���<<)
			// ������һ�Σ���Ž�������Ҵ�
			//ͳ�Ʒ���
			int score=0;
			for(int i=0;i<subjectList.size();i++) {
				result[i] =  request.getParameter("subject_option_"+subjectList.get(i).getId());
				answer[i] = subjectList.get(i).getAnswer();
			}
			
			//�����ڶ��Σ��ԱȽ�����������
			for(int i=0;i<subjectList.size();i++) {
				
				if(answer[i].equals(result[i])) {
					score += subjectList.get(i).getScore();
				}
			}
			
			request.setAttribute("answer", answer);
			request.getSession().setAttribute("result", result);
			request.setAttribute("score", score);
			
//			System.out.println("�����÷֣�"+score);
			
			request.getRequestDispatcher("relust.jsp?testId="+testId).forward(request, response);
			
			
		}
		else if("delete".equals(param)){
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
			//ִ��ɾ��
			int id = Integer.parseInt(request.getParameter("id"));
			subjectDao.deleteSubject(id);
			
			response.sendRedirect("subjectServlet?param=list&testId="+testId);
		}else if("update".equals(param)){	
			
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String optionsA = request.getParameter("optionsA");
			String optionsB = request.getParameter("optionsB");
			String optionsC = request.getParameter("optionsC");
			String optionsD = request.getParameter("optionsD");
			int score = Integer.parseInt(request.getParameter("score"));
			String answer = request.getParameter("answer");
			String desc = request.getParameter("desc");
			

			Subject subject = new Subject(id, title, optionsA, optionsB, optionsC, optionsD, answer, score, test, desc);
			subjectDao.updateSubject(subject);
			
			//�ض���ص�index.jspҳ��
			response.sendRedirect("subjectServlet?param=list&testId="+testId);
			
		}
		else if("modify".equals(param)){
			
			int testId = Integer.parseInt(request.getParameter("testId"));
			Test test = testDao.findTestById(testId);
			request.setAttribute("test", test);
			
			int id = Integer.parseInt(request.getParameter("id"));
			Subject subject = subjectDao.findSubjectById(id);
			request.setAttribute("subject", subject);
			
			
			request.getRequestDispatcher("subject_update.jsp?testId="+testId).forward(request, response);
		}
		else {
			response.sendRedirect("other.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
