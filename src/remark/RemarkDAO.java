package remark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;
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
	
	
	

	@Override
	public void delete_nNO(int NEED_remarkNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM REMARK WHERE R_NO=?");
			pstmt.setInt(1, NEED_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
	}

	@Override
	public void delete_nPNO(int NEED_productNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM REMARK WHERE R_PNO=?");
			pstmt.setInt(1, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void delete_nWRITER(String NEED_WRITER) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM REMARK WHERE R_WRITER=?");
			pstmt.setString(1, NEED_WRITER);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}	
	}
	
	
	

	@Override
	public void updateNO_nNO(int change_remarkNO, int need_remarkNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_NO=? "
					+ "WHERE R_NO=?");
			pstmt.setInt(1, change_remarkNO);
			pstmt.setInt(2, need_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePNO_nNO(int change_remarkPNO, int need_remarkNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_PNO=? "
					+ "WHERE R_NO=?");
			pstmt.setInt(1, change_remarkPNO);
			pstmt.setInt(2, need_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateTITLE_nNO(String change_remarkTITLE, int need_remarkNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_TITLE=? "
					+ "WHERE R_NO=?");
			pstmt.setString(1, change_remarkTITLE);
			pstmt.setInt(2, need_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCONTENT_nNO(String change_remarkCONTENT, int need_remarkNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_CONTENT=? "
					+ "WHERE R_NO=?");
			pstmt.setString(1, change_remarkCONTENT);
			pstmt.setInt(2, need_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateWRITER_nNO(String change_remarkWRITER, int need_remarkNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "REMARK "
					+ "SET R_WRITER=? "
					+ "WHERE R_NO=?");
			pstmt.setString(1, change_remarkWRITER);
			pstmt.setInt(2, need_remarkNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	
	

	@Override
	public RemarkDTO selectRemark(int need_remarkNO) throws Exception {
		
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
			pstmt.setInt(1, need_remarkNO);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
	public List<RemarkDTO> selectsRemark_nPNO(int need_remarkPNO) throws Exception {
		
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
			pstmt.setInt(1, need_remarkPNO);
			
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

	@Override
	public List<RemarkDTO> selectsRemark_nTITLE(String need_remarkTITLE) throws Exception {
		
		Connection		  conn    = null;
		PreparedStatement pstmt	  = null;
		ResultSet 		  rs 	  = null;
		List<RemarkDTO>	  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
					+ "FROM REMARK "
					+ "WHERE R_TITLE=?");
			pstmt.setString(1, need_remarkTITLE);
			
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

	@Override
	public List<RemarkDTO> selectsRemark_nCONTENT(String need_remarkCONTENT) throws Exception {

		Connection		  conn    = null;
		PreparedStatement pstmt	  = null;
		ResultSet 		  rs 	  = null;
		List<RemarkDTO>	  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
					+ "FROM REMARK "
					+ "WHERE R_CONTENT=?");
			pstmt.setString(1, need_remarkCONTENT);
			
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

	@Override
	public List<RemarkDTO> selectsRemark_nWRITER(String need_remarkWRITER) throws Exception {

		Connection		  conn    = null;
		PreparedStatement pstmt	  = null;
		ResultSet 		  rs 	  = null;
		List<RemarkDTO>	  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT R_NO, R_PNO, R_TITLE, R_CONTENT, R_WRITER "
					+ "FROM REMARK "
					+ "WHERE R_WRITER=?");
			pstmt.setString(1, need_remarkWRITER);
			
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
