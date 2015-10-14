package product;

public class ProductDTO {
	private int p_no;
	private int p_cno;
	private String p_name;
	private int p_price;
	private int p_count;
	
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
}
