package mall.client.vo;

public class Orders {
	private int ordersNo;
	private int clientNo;
	private int ebookNo;
	private String ordersDate;
	private String ordersState;
	
	public int getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public int getEbookNo() {
		return ebookNo;
	}
	public void setEbookNo(int ebookNo) {
		this.ebookNo = ebookNo;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getOrdersState() {
		return ordersState;
	}
	public void setOrdersState(String ordersState) {
		this.ordersState = ordersState;
	}
	@Override
	public String toString() {
		return "Orders [ordersNo=" + ordersNo + ", clientNo=" + clientNo + ", ebookNo=" + ebookNo + ", ordersDate="
				+ ordersDate + ", ordersState=" + ordersState + "]";
	}
	

	
}
