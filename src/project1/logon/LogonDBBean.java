package project1.logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogonDBBean {
	
	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance(){
		return instance;
	}
	private LogonDBBean(){
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	public void insertMember(LogonDataBean member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number=0;
		
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(M_NO) from member");
			rs = pstmt.executeQuery();
			if (rs.next()) 
				number=rs.getInt(1)+1;	
			else
				number=1; 
			pstmt= conn.prepareStatement(
					"insert into MEMBER values(member_seq.NEXTVAL,?,?,?,?,?,?,?)");
			//pstmt.setInt(1, member.getNo());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setInt(4, member.getBirth());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try {conn.close(); } catch(SQLException ex) {}
		}
	}
	public int getUserNo(String id) throws Exception{
		int m_no = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select M_NO from MEMBER where M_ID = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				m_no= rs.getInt("M_NO");			
			}				
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
			if(conn != null) try {conn.close();} catch(SQLException ex){}
		}
		
		return m_no;
	}
	public int userCheck(String id, String passwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd="";
		int x = -1;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select M_PW from MEMBER where M_ID = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				dbpasswd= rs.getString("M_PW");
				if(dbpasswd.equals(passwd))
					x=1; //비번맞음
				else
					x=0; //비번틀림
			}else
				x=-1; //아이디 없음
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
			if(conn != null) try {conn.close();} catch(SQLException ex){}
		}
		return x;
	}
	
	public int confirmId(String id)
	throws Exception{
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		int x=-1;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select M_ID from MEMBER where M_ID = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next())
				x=1;
			else
				x=-1;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!= null) try{rs.close(); } catch(SQLException ex){}
			if(pstmt!= null) try{pstmt.close();} catch(SQLException ex){}
			if(conn != null) try{conn.close();} catch(SQLException ex){}
		}
		return x;
	}
	
	public LogonDataBean getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"select * from MEMBER where M_ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member = new LogonDataBean();
				member.setNo(rs.getInt("M_NO"));
				member.setId(rs.getString("M_ID"));
				member.setPasswd(rs.getString("M_PW"));
				
				member.setBirth(rs.getInt("M_BIRTH"));
				member.setAddress(rs.getString("M_ADDRESS"));
				member.setPhone(rs.getString("M_PHONE"));
				member.setEmail(rs.getString("M_EMAIL"));
				member.setName(rs.getString("M_NAME"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs !=null) try {rs.close(); } catch(SQLException ex){}
			if (pstmt != null) try {pstmt.close(); } catch(SQLException ex){}
			if (conn != null) try {conn.close();} catch(SQLException ex){}
		}
		return member;
	}
	public void updateMember(LogonDataBean member)throws Exception{
		Connection conn= null;
		PreparedStatement pstmt = null;
		
		try{
			conn=getConnection();
			pstmt= conn.prepareStatement(
					"update MEMBER set M_PW=?,M_ADDRESS=?, M_PHONE=?,M_EMAIL=? "+"where M_ID=?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
			if(conn != null) try {conn.close();} catch(SQLException ex){}
		}
	}
	
	public int deleteMember(String id, String passwd) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd="";
		int x=-1;
		try{
			conn= getConnection();
			pstmt= conn.prepareStatement(
					"select M_PW from MEMBER where M_ID=?");
			pstmt.setString(1,id);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				dbpasswd= rs.getString("M_PW");
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement(
							"delete from MEMBER where M_ID=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x=1;
				}else
					x=0;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null) try {rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException ex){}
			if(conn !=null) try {conn.close();} catch(SQLException ex){}
		}
		return x;
	}
}
