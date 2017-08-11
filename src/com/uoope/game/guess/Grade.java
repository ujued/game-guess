package com.uoope.game.guess;

public class Grade {
	@Override
	public String toString() {
		return value + "";
	}

	private int value;
	private static Grade instance;

	private Grade(String dataFileName) {
		this.value = Integer.parseInt(new Data().getProperty(0));
	}

	public static Grade get() {
		if (instance == null)
			instance = new Grade("data.uf");
		return instance;
	}

	public static Grade set(String filePath) {
		instance = new Grade(filePath);
		return instance;
	}

	public void setValue(int value) {
		this.value = value;

	}

	public void add(int value) {
		this.value += value;
	}
	public void sub(int value){
		this.value -=  value;
	}
}
