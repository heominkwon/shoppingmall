package product;

import java.sql.Timestamp;

public class ProductDTO {
	private int p_no;
	private int p_cno;
	private String p_name;
	private int p_price;
	private int p_count;
	private String p_desc;
	private String p_path;
	private Timestamp p_regdate;
	
	public int getP_no(){
		return this.p_no;
	}
	public int getP_cno(){
		return this.p_cno;
	}
	public String getP_name(){
		return this.p_name;
	}
	public int getP_price(){
		return this.p_price;
	}
	public int getP_count(){
		return this.p_count;
	}
	public String getP_desc(){
		return this.p_desc;
	}
	public String getP_path(){
		return this.p_path;
	}
	public Timestamp getP_regdate() {
        return this.p_regdate;
    } 
	
	
	public void setP_no(int p_no){
		this.p_no = p_no;
	}
	public void setP_no(String p_no){
		this.p_no = Integer.parseInt(p_no);
	}
	public void setP_cno(int p_cno){
		this.p_cno = p_cno;
	}
	public void setP_cno(String p_cno){
		this.p_cno = Integer.parseInt(p_cno);
	}
	public void setP_name(String p_name){
		this.p_name = p_name;
	}
	public void setP_price(int p_price){
		this.p_price = p_price;
	}
	public void setP_price(String p_price){
		this.p_price = Integer.parseInt(p_price);
	}
	public void setP_count(int p_count){
		this.p_count = p_count;
	}
	public void setP_count(String p_count){
		this.p_count = Integer.parseInt(p_count);
	}
	public void setP_desc(String p_desc){
		this.p_desc = p_desc;
	}
	public void setP_path(String p_path){
		this.p_path = p_path;
	}
	public void setP_regdate(Timestamp p_regdate) {
        this.p_regdate = p_regdate;
    } 
}
