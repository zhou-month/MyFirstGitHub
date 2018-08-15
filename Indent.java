package sys;
/*
 * 	订餐人名称：
            所选菜品信息：
	送餐时间：
	送餐地址：
	订单的总金额：

 */
public class Indent {
	
	String name;
	String meun;
	int count;
	double time;
	String address;
	double money;
	
	public Indent() {

	}

	public Indent(String name, String meun, double time, String address,int money) {
		this.name = name;
		this.meun = meun;
		this.time = time;
		this.address = address;
		this.money = money;
		this.count = count;
	}

	
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeun() {
		return meun;
	}

	public void setMeun(String meun) {
		this.meun = meun;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}


	
	
	
	
	
	
	
}
