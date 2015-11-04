package Connection;


public class ConnectionDTO {
	
	private int year; 
	private int month;
	private int con_num;
	private int teenager;
	private int twenties;
	private int thirties;
	private int forty;	
	
	public ConnectionDTO(int year, int month, int con_num, int teenager, int twenties, int thirties, int forty){
		this.year  = year;
		this.month = month;
		this.con_num = con_num;
		this.teenager = teenager;
		this.twenties = twenties;
		this.thirties = thirties;
		this.forty = forty;
	}
	public ConnectionDTO() {
	}
	
	public int getYear(){
		return year;
	}
	public int getMonth(){
		return month;
	}
	public int getCon_num(){
		return con_num;
	}
	public int getTeenager(){
		return teenager;
	}
	public int getTwenties(){
		return twenties;
	}
	public int getThirties(){
		return thirties;
	}
	public int getForty(){
		return forty;
	}
	
	public void setYear(int year){
		this.year=year;
	}
	public void setMonth(int month){
		this.month=month;
	}
	public void setCon_num(int con_num){
		this.con_num=con_num;
	}
	public void setTeenager(int teenager){
		this.teenager=teenager;
	}
	public void setTwenties(int twenties){
		this.twenties=twenties;
	}
	public void setThirties(int thirties){
		this.thirties=thirties;
	}
	public void setForty(int forty){
		this.forty=forty;
	}
	
	
}
