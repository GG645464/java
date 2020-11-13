package com.gg.util;

import java.util.List;

import com.gg.model.Classes;
import com.gg.model.Student;

/**
 * 页面模型，封装了某一页有关的所有信息
 * @author Administrator
 *
 */
public class PageModel<T> {
	private List<T> list;
	private int totalRecords;
	private int pageSize = 5;
//	private int totalPages;
	private int PageNo;
	
	
	public PageModel(List<T> list, int totalRecords, int pageNo) {
		super();
		this.list = list;
		this.totalRecords = totalRecords;
		PageNo = pageNo;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageModel() {
		super();
	}
	public int getPageNo() {
		return PageNo;
	}
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		
		return totalRecords%5==0?(totalRecords/5):(totalRecords/5+1);
	}
	
	//首页
	public int getTopPage(){
		return 1;
	}
	
	//上一页
	public int getPreviousPage(){
		return PageNo<=1?PageNo:(PageNo-1);
	}
	
	//下一页
	public int getNextPage(){
		return PageNo>=getTotalPages()?getTotalPages():(PageNo+1);
	}
	
	//尾页
	public int getLastPage(){
		return getTotalPages();
	}
}
