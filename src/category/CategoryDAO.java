package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
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
	public void deleteCategory(CategoryDTO category) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM CATEGORY WHERE C_NO=?");
			pstmt.setInt(1, category.getC_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCategory(CategoryDTO category) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NAME=? "
					+ "WHERE C_NO=?");
			pstmt.setString(1, category.getC_name());
			pstmt.setInt(2, category.getC_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public CategoryDTO selectCategory(CategoryDTO category) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		CategoryDTO       dto   = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "C_NO, C_NAME "
					+ "WHERE "
					+ "C_NO=?");
			pstmt.setInt(1, category.getC_no());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new CategoryDTO();
				dto.setC_no(rs.getInt(1));
				dto.setC_name(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dto;
	}

	
	
	@Override
	public void deleteCategory(int NEED_categoryNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM CATEGORY WHERE C_NO=?");
			pstmt.setInt(1, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void deleteCategory(String NEED_categoryNAme) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM CATEGORY WHERE C_NAME=?");
			pstmt.setString(1, NEED_categoryNAme);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updateCategory(int NEED_categoryNO, int change_categoryNO) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NO=? "
					+ "WHERE C_NO=?");
			pstmt.setInt(1, change_categoryNO);
			pstmt.setInt(2, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCategory(int NEED_categoryNO, String change_categoryName) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NAME=? "
					+ "WHERE C_NO=?");
			pstmt.setString(1, change_categoryName);
			pstmt.setInt(2, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCategory(String NEED_categoryName, int change_categoryNO) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NO=? "
					+ "WHERE C_NAME=?");
			pstmt.setInt(1, change_categoryNO);
			pstmt.setString(2, NEED_categoryName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCategory(String NEED_categoryName, String change_categoryName) throws Exception {

		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "CATEGORY "
					+ "SET C_NAME=? "
					+ "WHERE C_NAME=?");
			pstmt.setString(1, change_categoryName);
			pstmt.setString(2, NEED_categoryName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)  try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public CategoryDTO selectCategory(int NEED_categoryNO) throws Exception {
		
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
			pstmt.setInt(1, NEED_categoryNO);
			
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
	public CategoryDTO selectCategory(String NEED_categoryName) throws Exception {		
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
			pstmt.setString(1, NEED_categoryName);
			
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
}
