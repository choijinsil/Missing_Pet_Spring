package beans.missing.domain;

public class UserVO {

	private String id;
	private String name;
	private String pass;
	private String email;
	private String tel;
	private String address;
	private String black;

	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public UserVO(String id, String name, String pass, String email, String tel, String address, String black) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.black = black;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", pass=" + pass + ", email=" + email + ", tel=" + tel
				+ ", address=" + address + ", black=" + black + "]";
	}

}
