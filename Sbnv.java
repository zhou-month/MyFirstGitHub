package sys;

import java.util.ArrayList;
import java.util.Scanner;

public class Sbnv {
	public static void main(String[] args) {
		ArrayList<Indent> indent = new ArrayList<Indent>();
		String[] state = new String[3];//用于保存订单的状态信息:0.未完成      1.已完成
		String[] meunName = new String[] { "红烧带鱼", "鱼香肉丝", "时令鲜蔬" };// 菜品名称
		double[] prices = new double[] { 38.0, 20.0, 10.0 };// 菜品单价
		int[] good = new int[3];// 点赞数
		
		while(true){
			System.out.println("-------欢迎来到吃货联盟-------");
			System.out.println("1.订餐");
			System.out.println("2.查看");
			System.out.println("3.签收");
			System.out.println("4.删除");
			System.out.println("5.点赞");
			System.out.println("6.退出");
			System.out.println("请输入你的选择:");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice){
			case 1:
				//订餐
				add(indent,meunName,prices,good,state);
				break;
			case 2:
				//查看餐袋
				check(indent,state);
				break;
			case 3:
				//签收订单
				sign(indent,state);
				break;
			case 4:
				//删除订单
				delete(indent,state);
				break;
			case 5:
				//我要点赞
				good(indent,state,good,meunName);
				break;
			case 6 :
				System.out.println("谢谢惠顾，再见");
				System.exit(0);
			default:
				System.out.println("请正确输入你要的选择");
				break;
			}
		}
	}
	
	
	
	
	//用于后面程序中频繁的检验订单是否为空    ！但是貌似并没有起到很好的作用				(问老师有没有什么办法可以提高复用)
	static boolean notNull(ArrayList<Indent> indent){
		 boolean flag = true;
		 if(indent.size() != 0){
			 flag = false;
		 }
		return flag;
	}
	
	
	
	//用于订餐
	static void add(ArrayList<Indent> indent,String[] meunName,double[] prices,int[] good,String[] state){
		double money = 0.0;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入接收人(订餐人)的姓名");
		String name = sc.next();
		System.out.println("欢迎点餐!");
		System.out.println("菜单如下:");
		System.out.println("序号\t\t菜品名称\t单价\t点赞数");
		for(int i=0;i<meunName.length;i++){
			System.out.println(i+1+"\t\t"+meunName[i]+"\t"+prices[i]+"\t"+good[i]);
		}
		System.out.println("请输入你点餐的序号,如需返回请按‘0’");
		int meun = sc.nextInt();
		if(meun == 0){
			return;
		}
		System.out.println("请输入你要的份数");
		int count = sc.nextInt();
		System.out.println("请输入送达的地点");
		String address = sc.next();
		System.out.println("请输入你要送达的时间");
		double time = sc.nextDouble();
		double carriage=0.0;
		 money= prices[meun-1]*count;
		if(money >= 50){
			money = money*0.9;
		}else{//不满50则收5元运费
			carriage= 5.0;//运费
			money += carriage;
		}
		
		System.out.println("请确认订单:");
		System.out.println("订餐人  "+name+"  订购的菜品  "+meunName[meun-1]+"  份数  "+count);
		System.out.println("送达地点  "+address+"  送达时间  "+time+"  总金额  "+money+"元   运费(如果超过100元则免邮)"+carriage+"元");
		while(true){
			System.out.println("确认Y/N");
			String read = sc.next();
			if(read.toUpperCase().equals("N")){
				return;
			}else if(read.toUpperCase().equals("Y")){
				break;
			}else{
				System.out.println("请确认订单");
			}
		}
		
		Indent in = new Indent();
		in.name = name;
		in.meun = meunName[meun-1];
		in.count = count;
		in.address = address;
		in.time = time;
		in.money = money;
		
		indent.add(in);
		/*//订单的签收flasa在此处添加
		int a =indent.indexOf(name);
		//测试//不行
		System.out.println(a);
		state[a] = "未完成";*/
		System.out.println("您已成功下单");
		
	}
	
	
	
	
	//用于查看订单
	static void check(ArrayList<Indent> indent,String[] state){
		Scanner sc = new Scanner(System.in);
		boolean flag;
		if(flag = notNull(indent)){
			System.out.println("不好意思，目前还没有人下订单");
			return;
		}
		System.out.println("序号\t订餐人\t餐品信息\t\t送餐时间\t送餐地点\t总金额\t状态");
		for(int i=0;i<indent.size();i++){
			Indent in = indent.get(i);
			System.out.println(i+1+"\t"+in.getName()+"\t"+in.getMeun()+" "+in.getCount()+"份"+"\t"+in.getTime()
					+"时"+"\t"+in.getAddress()+"\t"+in.getMoney()+"元"+"\t"+state[i]);
		}
		return;
	}
	
	
	
	//签收（还是存在问题，需明天请教老师）
	static void sign(ArrayList<Indent> indent,String[] state){
		boolean flag;
		if(flag = notNull(indent)){
			System.out.println("不好意思，目前还没有人下订单");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要签收订餐人姓名");
		 String name = sc.next();
		 int index = -1;
		 for(int j=0;j<indent.size();j++){
			 Indent i = indent.get(j);
			 if(i.getName().equals(name)){
					index = j;
					break;
				 }
		 	}
		 if(index == -1){
			 System.out.println("不好意思，请查实你的订单是否有误");
		 }else{
			 while(true){
				System.out.println("是否确认签收Y/N");
				String read = sc.next();
				if(read.toUpperCase().equals("N")){
					return;
				}else if(read.toUpperCase().equals("Y")){
					System.out.println("已经确认签收。别忘记去点赞哦");
					state[index] = "已签收";
					break;
				}else{
					System.out.println("请正确输入");
				}
			}
		 }
	}
	
	
	
	//删除
	static void delete(ArrayList<Indent> indent,String[] state){
		 if(indent.size()==0){
				System.out.println("目前没有订单产生");
				return;
		 }else{
			 System.out.println("输入你要删除订单的订餐人姓名");
			 Scanner sc = new Scanner(System.in);
			 String name = sc.next();
			 int index = -1;
			 for(int j=0;j<indent.size();j++){
				 Indent i = indent.get(j);
				 if(i.getName().equals(name)){
					index = j;
					break;
				 }
			 }
			 if(index == -1){
					System.out.println("你要删除的订单不存在");
					return;
				}else{
					if(state[index].equalsIgnoreCase("未完成")){
						System.out.println("是否确认签收Y/N");
						String read = sc.next();
						if(read.toUpperCase().equals("N")){
							return;
						}else if(read.toUpperCase().equals("Y")){
							System.out.println("删除成功");
							indent.remove(index);
							state[index] = null;
						}else{
							System.out.println("请正确输入");
						}
					}else{//这边订单信息不确定，先这样
						indent.remove(index);
						System.out.println("删除成功");
					}
		 		}
		 	}
		}
	
	
	
	//点赞
	static void good (ArrayList<Indent> indent,String[] state,int[] good,String[] meunName){
		 if(indent.size()==0){
				System.out.println("目前没有订单产生");
				return;
		 }
		 System.out.println("请输入你要签收的订餐人的姓名");
		 Scanner sc1 = new Scanner(System.in);
		 String name = sc1.next();
		 int index = -1;
		 String goodmeun = null;
		 for(int j=0;j<indent.size();j++){
			 Indent i = indent.get(j);
			 goodmeun = i.getMeun();
			 if(i.getName().equals(name)){
					index = j;
					break;
				 }
		 	}
		 if(index == -1){
			 System.out.println("你还没点餐，请品尝完了再进行点赞");
			 return;
		 }else{
			 for(int i=0;i<meunName.length;i++){
				 if(meunName[i].equals(goodmeun)){
					 good[i]++;
					 System.out.println("点赞成功");
				 }
			 }
		 }
	}
	
	
}
