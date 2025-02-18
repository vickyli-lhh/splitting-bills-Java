import java.util.ArrayList;

public class Project {
	private String name;
	private JointAccount jointAccount;
//	TODO: �O���OallItems allUsers����n
	private ArrayList<Item> allItem = new ArrayList<Item>();
	private ArrayList<UserAccount> allUser = new ArrayList<UserAccount>();

	public Project(String name, JointAccount jointAccount) {
		this.name = name;
		this.jointAccount = jointAccount;
	}

	public void addItem(String itemName, double totalAmount, String payee, String payer) {
		if (payer.equals("all") || payer.equals("ALL") || payer.equals("All")) {
			payer = getAllUser();
		}
		String payerList[] = payer.split(" ");//分割 payer 字串，分割依據: " "
		for (String user: payerList) {
//			TODO: �p�G��J���s�b��user�|�۰ʷs�W�B�ư�NA
			if (!getAllUser().contains(user) && !user.equals("NA")) {//.contains()
				addUser(user);
			}
		}
		Item item = new Item(itemName, totalAmount, payee, payerList);
		allItem.add(item);
//		TODO: �ڥ[�F�u�u
		System.out.println("--- Item " + itemName + " is added to " + this.name + "! ---");
		System.out.println();
	}

//	TODO: �令void
	public void getAllItem() {
		System.out.printf("%-8s %-20s %-10s %-15s %-50s", "Index", "Item", "Amount", "Who_paid", "participants");
		System.out.println();
		for (Item i : allItem) {
			System.out.format("%-8s %-20s %-10s %-15s %-50s",
					allItem.indexOf(i), i.getName(), i.getTotalAmount(), i.getPayee(), i.getpayers());
            System.out.println();
		}
	}

	public void addUser(String name) {
		UserAccount user = new UserAccount(name, 0.0);//新增使用者(UserAccount的物件)，並初始化餘額為 0。
		allUser.add(user);
	}

//	TODO:�@�ڷs�W��
	public String getAllUser() {
		String nameList = "";
		for (UserAccount user : allUser) {
			nameList += user.getName() + " " ;
		}
		return nameList;
	}

//	TODO: �ڷs�W��
	public void revise(int index, int function, String newContet) {//revise item
		Item revise = allItem.get(index);
		switch (function) {
		case 1:
//			1-�ק� item name�F
			revise.setName(newContet);
			break;
		case 2:
//			2-�ק���B�F
			double d = Double.parseDouble(newContet);
			revise.setTotalAmount(d);
			break;
		case 3:
//			3-�ק怜�ڤH�F
			revise.setPayee(newContet);
			break;
		case 4:
//			4-�ק�I�ڤH�F
			String payer[] = newContet.split(" ");
			revise.setPayer(payer);
			break;
		}
		System.out.println("Item " + revise.getName() + " is revised!");
		System.out.println();
	}

//	TODO: �ڷs�W��
	public void revise(int index, int function) {
//		5-�R�� item
		Item revise = allItem.get(index);
		allItem.remove(revise);
		System.out.println("Item " + revise.getName() + " is deleted!");
		System.out.println();
	}

	public void calculation() {
		for(Item item: allItem) {
			double totalAmount = item.getTotalAmount();
			double amount = totalAmount / item.getPayersNum();
			for (UserAccount user : allUser) {
				String userName = user.getName();
				if (userName.equals(item.getPayee())) {
					user.addMoney(totalAmount);
				}
				if (item.getpayers().contains(userName)) {
					user.oweMoney(amount);
				}
			}
			if (item.getPayee().equals("JointAccount")) {
				jointAccount.spend(amount);
			}
		}
		for(UserAccount user : allUser) {
			double money = user.getTotalAmount();
			if (money > 0) {
				System.out.printf(user.getName() + " gets " + money);
				System.out.println("");
			} else if (money < 0) {
				System.out.printf(user.getName() + " owes " + Math.abs(money));
				System.out.println("");
			}
		}
		System.out.print("Joint Account balance: " + jointAccount.getTotalAmount());
	}
}
