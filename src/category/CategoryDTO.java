package category;

public class CategoryDTO {
	
	private int c_no;
	private String c_name;
	
	public int getC_no() {
		return c_no;
	}
	public String getC_name() {
		return c_name;
	}
	
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public void setC_no(String c_no){
		this.c_no = Integer.parseInt(c_no);
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public void setC_name(int C_name){
		this.c_name = String.valueOf(C_name);
	}
}
