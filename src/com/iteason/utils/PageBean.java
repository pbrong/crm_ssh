package com.iteason.utils;

import java.util.List;

public class PageBean {
	
	private Integer currentPage;
	private Integer totalCount;
	private Integer pageSize;
	private Integer totalPage;
	private List list;
	
	//-------------------------------------
	
	
	//空参构造
	public PageBean(){
		
	}
	
	
	//含参构造
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		this.totalCount = totalCount;
	
		//判断pageSize
		if(pageSize == null){
			this.pageSize = 3;
		}else{
			this.pageSize = pageSize;
		}
		
		//计算总页数
		this.totalPage = (int) Math.ceil(1.0*this.totalCount/this.pageSize);
		
		
		//判断currentPage
		if(currentPage == null){
			this.currentPage = 1;
		}else if(currentPage == 0){
			this.currentPage = 1;
		}else if(currentPage > this.totalPage){
			this.currentPage = this.totalPage;
		}else{
			this.currentPage = currentPage;
		}
		
		
	
	}
	
			//获得索引
			public Integer getStart(){
				//计算索引
				Integer start = (int) (this.currentPage-1)*this.pageSize;
				
				return start;
			}
	
		//---------------------------------
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public Integer getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(Integer totalCount) {
			this.totalCount = totalCount;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public Integer getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}
		public List getList() {
			return list;
		}
		public void setList(List list) {
			this.list = list;
		}


		@Override
		public String toString() {
			return "PageBean [currentPage=" + currentPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize
					+ ", totalPage=" + totalPage + ", list=" + list + "]";
		}
	
	
	
			
	
	
}
