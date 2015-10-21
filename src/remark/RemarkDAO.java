package remark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RemarkDAO implements RemarkInterface{

	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertRemark(RemarkDTO remark) throws Exception {
		Connection    		 conn 	= null;
		PreparedStatement    pstmt	= null;
		
		try{
			conn = getConnection();
			pstmt= conn.prepareStatement(""
					+ "INSERT INTO REMARK "
					+ "(R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER) "
					+ "VALUES "
					+ "(REMARK_SEQ.NEXTVAL,?,?,?,?)");
			pstmt.setInt(1,remark.getR_pno());
			pstmt.setString(2, remark.getR_title());
			pstmt.setString(3, remark.getR_content());
			pstmt.setString(4, remark.getR_writer());
			
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();} catch (SQLException se) {}
			if(conn !=null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void deleteRemark(RemarkDTO remark) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try{
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM REMARK WHERE R_NO=?");
			pstmt.setInt(1, remark.getR_no());
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try{pstmt.close();} catch(SQLException se) {}
			if(conn  != null) try{conn.close();}  catch(SQLException se) {}
		}
	}

	@Override
	public void updateRemark(RemarkDTO remark) throws Exception {
		
		Connection  		conn  = null;
		PreparedStatement   pstmt = null; 
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_PNO=?, R_TITLE=? , R_CONTENT=? , R_WRITER=? "
					+ "WHERE R_NO=?");
			pstmt.setInt(1, remark.getR_pno());
			pstmt.setString(2, remark.getR_title());
			pstmt.setString(3, remark.getR_content());
			pstmt.setString(4, remark.getR_writer());
			pstmt.setInt(5, remark.getR_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try{pstmt.close();} catch(SQLException se) {}
			if(conn  != null) try{conn.close();}  catch(SQLException se) {}
		}
	}


	@Override
	public RemarkDTO selectRemark(RemarkDTO remark) throws Exception {
			Connection    		conn  = null;
			PreparedStatement   pstmt = null;
			ResultSet           rs    = null;
			RemarkDTO			dto   = null;
		
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"SELECT "
						+ "R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
						+ "FROM REMARK "
						+ "WHERE R_NO=?");
				
				pstmt.setInt(1,remark.getR_no());
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					dto = new RemarkDTO();
					dto.setR_no(rs.getInt(1));
					dto.setR_pno(rs.getInt(2));
					dto.setR_title(rs.getString(3));
					dto.setR_content(rs.getString(4));
					dto.setR_writer(rs.getString(5));
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs    !=null) try{rs.close();}    catch(SQLException se){}
				if(pstmt !=null) try{pstmt.close();} catch(SQLException se){}
				if(conn  !=null) try{conn.close();}  catch(SQLException se){}
			}
			return dto;
	}
	

}
