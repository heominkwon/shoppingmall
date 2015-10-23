package logon;

public class LogonDataBean {

	private int no;
	private String id;
	private String name;
	private String passwd;
	private int birth;
	private String address;
	private String phone;
	private String email;
	
	public void setNo (int no){
		this.no = no;
	}
	public void setId (String id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	public void setBirth(int birth){
		this.birth = birth;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	public int getNo(){
		return no;
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPasswd(){
		return passwd;
	}
	public int getBirth(){
		return birth;
	}
	public String getAddress(){
		return address;
	}
	public String getPhone(){
		return phone;
	}
	public String getEmail(){
		return email;
	}

}
	
	
	
	
	
	
	
	
	
	