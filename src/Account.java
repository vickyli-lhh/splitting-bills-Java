
public class Account {
	protected double totalAmount = 0;// Instance variable

	public Account (double amount) {// Constructor
		this.totalAmount = amount;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void addMoney(double amount) {
		this.totalAmount = this.totalAmount + amount;
	}
	
	public void spend(double amount) {
		this.totalAmount = this.totalAmount - amount;
	}
}
