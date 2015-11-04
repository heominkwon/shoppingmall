package not.board;

import java.sql.Timestamp;

public class NotDataBean {
	private int n_no;
	private String n_title;
	private String n_content;
	private String n_writer;
	private Timestamp n_regdate;
	private int n_readcount;
	private int ref;
	private int n_step;
	private int n_level;
	
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public Timestamp getN_regdate() {
		return n_regdate;
	}
	public void setN_regdate(Timestamp n_regdate) {
		this.n_regdate = n_regdate;
	}
	public int getN_readcount() {
		return n_readcount;
	}
	public void setN_readcount(int n_readcount) {
		this.n_readcount = n_readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getN_step() {
		return n_step;
	}
	public void setN_step(int n_step) {
		this.n_step = n_step;
	}
	public int getN_level() {
		return n_level;
	}
	public void setN_level(int n_level) {
		this.n_level = n_level;
	}

}
