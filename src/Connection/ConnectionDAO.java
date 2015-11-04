package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import order.OrderDTO;

public class ConnectionDAO implements ConnectionInterface {

	private ConnectionDAO() {}
	
	private static ConnectionDAO instance = new ConnectionDAO();
	
	public static ConnectionDAO getInstance(){
		return instance;
	}
	
	@Override
	public int checkConnection(int year, int month) throws Exception{
		int x=0;
		
		Connection         conn = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM CONNECTION where YEAR=? and MONTH=?");
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			rs=pstmt.executeQuery();			
			if(rs.next()){
				x=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		
		return x;
	}
	@Override
	public void updateConnection(int year, int month, int age) throws Exception{
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE CONNECTION SET CON_NUM=CON_NUM+?, TEENAGER=TEENAGER+?, TWENTIES=TWENTIES+?, THIRTIES=THIRTIES+?, FORTY=FORTY+? WHERE YEAR=? and MONTH=?");
			pstmt.setInt(1,1);
			if(age<20)			
				pstmt.setInt(2,1);
			else
				pstmt.setInt(2,0);
			if(age>=20&&age<30)
				pstmt.setInt(3,1);
			else
				pstmt.setInt(3,0);
			if(age>=30&&age<40)
				pstmt.setInt(4,1);
			else
				pstmt.setInt(4,0);
			if(age>=40)
				pstmt.setInt(5,1);
			else
				pstmt.setInt(5,0);
			pstmt.setInt(6, year);
			pstmt.setInt(7, month);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void insertConnection(int year, int month) throws Exception{
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO CONNECTION "
					+ "(YEAR, MONTH, CON_NUM, TEENAGER, TWENTIES, THIRTIES, FORTY) "
					+ "VALUES "
					+ "(?,?,0,0,0,0,0)");
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	public ConnectionDTO selectConnection(int year, int month) throws Exception{
		ConnectionDTO 		con = null;		
		Connection          conn = null;
		PreparedStatement 	pstmt = null;
		ResultSet 	        rs 	= null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM CONNECTION where YEAR=? and MONTH=?");
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				con = new ConnectionDTO();
				con.setYear(rs.getInt(1));
				con.setMonth(rs.getInt(2));
				con.setCon_num(rs.getInt(3));
				con.setTeenager(rs.getInt(4));
				con.setTwenties(rs.getInt(5));
				con.setThirties(rs.getInt(6));
				con.setForty(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		
		return con;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context) initCtx.lookup("java:comp/env");
		DataSource ds   = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
}
