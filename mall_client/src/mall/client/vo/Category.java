package mall.client.vo;

public class Category {
	private int categoryNo;
	private String categoryName;
	private int categoryWeight;
	private String categorDate;
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryWeight() {
		return categoryWeight;
	}
	public void setCategoryWeight(int categoryWeight) {
		this.categoryWeight = categoryWeight;
	}
	public String getCategorDate() {
		return categorDate;
	}
	public void setCategorDate(String categorDate) {
		this.categorDate = categorDate;
	}
	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", categoryWeight="
				+ categoryWeight + ", categorDate=" + categorDate + "]";
	}
	
	
	
	
}
