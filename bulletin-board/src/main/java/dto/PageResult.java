package dto;

import java.util.List;

import entity.BulletinBoard;

public class PageResult<T> {

	private List<T> data;
	private Integer totalPages;
	private Integer totalCount;
	private Integer currentPage;
	
	
	public PageResult(List<T> data, Integer totalPages, Integer totalCount, Integer currentPage) {
		super();
		this.data = data;
		this.totalPages = totalPages;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
	}
	public PageResult() {
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
}
