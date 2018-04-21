package kr.java.swinglibrary.component;

public class Product implements ToArray {

	private String code;
	private String name;
	private int price;
	
	public Product(String code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	@Override
	public Object[] toArray() {
		return new Object[] {code, name, String.format("%,d", price)};
	}

}
