package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO implements CategoryInterface{

	private CategoryDAO() {};
	private static CategoryDAO instance = new CategoryDAO();
	public static CategoryDAO getInstance(){
		return instance;
	}	
	@Override
	public Connection getConnection() throws Exception {
		Context    initCtx = new InitialContext();
		Context    envCtx  = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds      = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertCategory(CategoryDTO category) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO CATEGORY "
					+ "(C_NO, C_NAME) "
					+ "VALUES "
					+ "(CATEGORY_SEQ.NEXTVAL,?)");
			pstmt.setString(1, category.getC_name());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void deleteCategory(int c_no) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM CATEGORY WHERE C_NO=?");
			pstmt.setInt(1, c_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void deleteCategory(String c_name) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM CATEGORY WHERE C_NAME=?");
			pstmt.setString(1, c_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateC_name(int c_no, String updateC_name) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NAME=? "
					+ "WHERE C_NO=?");
			pstmt.setString(1, updateC_name);
			pstmt.setInt(2, c_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateC_name(String c_name, String updateC_name) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NAME=? "
					+ "WHERE C_NAME=?");
			pstmt.setString(1, updateC_name);
			pstmt.setString(2, c_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public CategoryDTO selectCategory(int c_no) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		CategoryDTO 	  dto 	= null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NO, C_NAME "
					+ "FROM CATEGORY "
					+ "WHERE C_NO=?");
			pstmt.setInt(1, c_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new CategoryDTO();
				dto.setC_no(rs.getInt(1));
				dto.setC_name(rs.getInt(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {pstmt.close();} catch (SQLException se) {}
		}
		return dto;
	}
	@Override
	public CategoryDTO selectCategory(String c_name) throws Exception {		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		CategoryDTO 	  dto 	= null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NO, C_NAME "
					+ "FROM CATEGORY "
					+ "WHERE C_NAME=?");
			pstmt.setString(1, c_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new CategoryDTO();
				dto.setC_no(rs.getInt(1));
				dto.setC_name(rs.getInt(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {pstmt.close();} catch (SQLException se) {}
		}
		return dto;
	}

	@Override
	public List<Integer> selectC_noAll() throws Exception {
		
		Connection 		  conn	   = null;
		PreparedStatement pstmt	   = null;
		ResultSet		  rs 	   = null;
		List<Integer> 	  c_noList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NO "
					+ "FROM "
					+ "CATEGORY");
			rs = pstmt.executeQuery();
			c_noList = new ArrayList<Integer>();
			
			while (rs.next()) {
				c_noList.add(rs.getInt("C_NO"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return c_noList;
	}
	@Override
	public List<Integer> selectC_nameAll() throws Exception {
		
		Connection 	      conn 		 = null;
		PreparedStatement pstmt 	 = null;
		ResultSet 		  rs		 = null;
		List<Integer> 	  c_nameList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NAME "
					+ "FROM "
					+ "CATEGORY");
			rs = pstmt.executeQuery();
			c_nameList = new ArrayList<Integer>();
			
			while (rs.next()) {
				c_nameList.add(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return c_nameList;
	}

	@Override
	public List<CategoryDTO> selectCategoryAll() throws Exception {
		
		Connection 	      conn 	  = null;
		PreparedStatement pstmt   = null;
		ResultSet 		  rs	  = null;
		List<CategoryDTO> dtoList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NO, C_NAME "
					+ "FROM "
					+ "CATEGORY");
			rs = pstmt.executeQuery();
			dtoList = new ArrayList<CategoryDTO>();
			
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setC_no(rs.getInt(1));
				dto.setC_name(rs.getString(2));
				dtoList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dtoList;
	}
}
