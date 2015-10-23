package order;

import java.sql.Date;

public class OrderDTO {
	
	private int 	o_no;
	private int 	o_mno;
	private int 	o_pay;
	private String  o_address;
	private Date 	o_date;
	
	public OrderDTO(String o_mno, String o_pay, String o_adress, Date o_date){
		this.o_no  = 0;
		this.o_mno = Integer.parseInt(o_mno);
		this.o_pay = Integer.parseInt(o_pay);
		this.o_address = o_adress;
		this.o_date = o_date;
	}
	public OrderDTO() {
	}
	
	public int getO_no() {
		return o_no;
	}
	public int getO_mno() {
		return o_mno;
	}
	public int getO_pay() {
		return o_pay;
	}
	public String getO_address() {
		return o_address;
	}
	public Date getO_date() {
		return o_date;
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
	public void setO_address(String o_address) {
		this.o_address = o_address;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
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
