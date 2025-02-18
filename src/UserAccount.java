
public class UserAccount extends Account{
	private String name;// Instance variable
//	�w�������t
	public UserAccount(String name, double totalAmount) {// Constructor
		super(totalAmount);//super: 呼叫父類別的constructor
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void oweMoney(double amount) {
		this.totalAmount = this.totalAmount - amount;
	}
}
