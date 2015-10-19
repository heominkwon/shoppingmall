package order;

public class OrderDTO {
	
	private int o_no;
	private int o_mno;
	private int o_pay;
	
	public int getO_no() {
		return o_no;
	}
	public int getO_mno() {
		return o_mno;
	}
	public int getO_pay() {
		return o_pay;
	}
	
	public String Str_getO_no() {
		return String.valueOf(o_no);
	}
	public String Str_getO_mno() {
		return String.valueOf(o_mno);
	}
	public String Str_getO_pay() {
		return String.valueOf(o_pay);
	}
	
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public void setO_mno(int o_mno) {
		this.o_mno = o_mno;
	}
	public void setO_pay(int o_pay) {
		this.o_pay = o_pay;
	}
	
	public void setO_no(String o_no) {
		this.o_no = Integer.parseInt(o_no);
	}
	public void setO_mno(String o_mno) {
		this.o_mno = Integer.parseInt(o_mno);
	}
	public void setO_pay(String o_pay) {
		this.o_pay = Integer.parseInt(o_pay);
	}
}
