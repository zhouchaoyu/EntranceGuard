package xjh.loveHome.IC.uitils;

public enum PageModeName{
	pageNo,pageRow;
public	String getName(){
		if (this==pageNo) {
			return "pageNo";
		}
		if (this==pageRow) {
			return "pageRow";
		}
		return "";
		}
	
}