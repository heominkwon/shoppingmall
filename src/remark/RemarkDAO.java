package remark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RemarkDAO implements RemarkInterface{

	private RemarkDAO() {};
	private static RemarkDAO instance = new RemarkDAO();
	public static RemarkDAO getInstance() {
		return instance;
	}
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
	public void deleteRemark(int r_no) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM REMARK WHERE R_NO=?");
			pstmt.setInt(1, r_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
	}

	@Override
	public void updateR_pno(int r_no, int updateR_pno) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_PNO=? "
					+ "WHERE R_NO=?");
			pstmt.setInt(1, updateR_pno);
			pstmt.setInt(2, r_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateR_title(int r_no, String updateR_title) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_TITLE=? "
					+ "WHERE R_NO=?");
			pstmt.setString(1, updateR_title);
			pstmt.setInt(2, r_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateR_content(int r_no, String updateR_content) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_CONTENT=? "
					+ "WHERE R_NO=?");
			pstmt.setString(1, updateR_content);
			pstmt.setInt(2, r_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public RemarkDTO selectRemark(int r_no) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		RemarkDTO 		  dto   = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
					+ "FROM REMARK "
					+ "WHERE R_NO=?");
			pstmt.setInt(1, r_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new RemarkDTO();
				dto.setR_no(rs.getInt(1));
				dto.setR_pno(rs.getInt(2));
				dto.setR_title(rs.getString(3));
				dto.setR_content(rs.getString(4));
				dto.setR_writer(rs.getString(5));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dto;
	}
	
	@Override
	public List<RemarkDTO> selectRemarkAll(int r_pno) throws Exception {
		
		Connection		  conn    = null;
		PreparedStatement pstmt	  = null;
		ResultSet 		  rs 	  = null;
		List<RemarkDTO>	  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
					+ "FROM REMARK "
					+ "WHERE R_PNO=?");
			pstmt.setInt(1, r_pno);
			
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<RemarkDTO>();
			
			while (rs.next()) {
				RemarkDTO dto = new RemarkDTO();
				dto.setR_no(rs.getInt(1));
				dto.setR_pno(rs.getInt(2));
				dto.setR_title(rs.getString(3));
				dto.setR_content(rs.getString(4));
				dto.setR_writer(rs.getString(5));
				dtolist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dtolist;
	}
}
