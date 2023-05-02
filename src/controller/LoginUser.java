package controller;

public class LoginUser {

	private int no;
	private String id;
	private String name;

	public LoginUser(int no, String id, String name) {
		this.no = no;
		this.id = id;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
