package mpei;

public enum PHONES {
	HUAWEI_P20(1),
	HUAWEI_P10(2),
	HUAWEI_P9(3),
	HUAWEI_P8(4),
	IPHONE7(5),
	IPHONE7S(6),
	IPHONE8(7),
	IPHONEX(8),
	IPHONEXR(9),
	IPHONEXS(10),
	IPHONE7PLUS(11),
	IPHONE7SPLUS(12),
	HUAWEI_MATE9(13),
	HUAWEI_MATE10(14),
	HUAWEI_MATE20(15),
	HUAWEI_MATEPRO(16),
	HUAWEI_P20PRO(17),
	XIAOMI_A2(18),
	POCOPHONE(19),
	SAMSUNG_S8(20),
	REDMIS2(21);
	
	
	private int index;
	
	PHONES(int i) {
		this.index = i;
	}
	
	public int index() { return index; }
	
}
