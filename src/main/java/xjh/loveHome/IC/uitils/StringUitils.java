package xjh.loveHome.IC.uitils;

public  class StringUitils {
	
	public static boolean isNotBlank(String value) {
		if (value==null||value.trim().equals("")) {
			return false;
		}
		return true;
		
	}

	public static boolean isBlank(String value) {
		if (value==null||value.trim().equals("")) {
			return true;
		}
		return false;
	}
	
}
