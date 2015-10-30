package OrderProduct;

public class OrderProductDTO {

	private int op_no;
	private int op_ono;
	private int op_pno;
	private int op_count;
	private int op_price;
	
	public OrderProductDTO (int op_ono, int op_pno, int op_count, int op_price) {
		this.op_no = 0;
		this.op_ono = op_ono;
		this.op_pno = op_pno;
		this.op_count = op_count;
		this.op_price = op_price;
	}
	public OrderProductDTO () {
		
	}
	public int getOP_no() {
		return op_no;
	}
	public int getOP_ono() {
		return op_ono;
	}
	public int getOP_pno() {
		return op_pno;
	}
	public int getOP_count() {
		return op_count;
	}
	public int getOP_price() {
		return op_price;
	}

	
	public void setOP_no(int op_no) {
		this.op_no = op_no;
	}
	public void setOP_ono(int op_ono) {
		this.op_ono = op_ono;
	}
	public void setOP_pno(int op_pno) {
		this.op_pno = op_pno;
	}
	public void setOP_count(int op_count) {
		this.op_count = op_count;
	}
	public void setOP_price(int op_price) {
		this.op_price = op_price;
	}
	
	public void setOP_no(String op_no) {
		this.op_no = Integer.parseInt(op_no);
	}
	public void setOP_ono(String op_ono) {
		this.op_ono = Integer.parseInt(op_ono);
	}
	public void setOP_pno(String op_pno) {
		this.op_pno = Integer.parseInt(op_pno);
	}
	public void setOP_count(String op_count) {
		this.op_count = Integer.parseInt(op_count);
	}
	public void setOP_price(String op_price) {
		this.op_price = Integer.parseInt(op_price);
	}
}
