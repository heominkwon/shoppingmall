package event.att;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Date;


public class EventDBBean {
private static EventDBBean instance = new EventDBBean();
	
	public static EventDBBean getInstance(){
		return instance;
	}
	private EventDBBean(){}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public int getEvents(Date day, int e_no) throws Exception {		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		int x=0;		
		try{
			conn=getConnection();
			pstmt = conn.prepareStatement(
					"select * from att where day=? and e_no=?");
			pstmt.setDate(1, day);
			pstmt.setInt(2, e_no);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=1;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}	
	
	public void insertEvent(EventDataBean event)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			pstmt=conn.prepareStatement(
					"insert into att values(?,?)");
		
			pstmt.setDate(1, event.getDay());
			pstmt.setInt(2, event.getE_no());
			pstmt.executeUpdate();
		} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	}
}

	
		
		
		
	