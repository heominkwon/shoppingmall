package product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO implements ProductInterface{
	
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance(){
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
	public void insertProduct(ProductDTO product) throws Exception{
		
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO PRODUCT VALUES ("
					+ "PRODUCT_SEQ.NEXTVAL,?,?,?,?)");
			pstmt.setInt(2, product.getP_cno());
			pstmt.setString(3, product.getP_name());
			pstmt.setInt(4,product.getP_price());
			pstmt.setInt(5, product.getP_count());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!= null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void deleteProduct(ProductDTO product) throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM PRODUCT WHERE P_NAME = ?");
			pstmt.setString(1, product.getP_name());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateProduct(ProductDTO product) throws Exception {
		
		Connection 		  conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_CNO=?, P_NAME=?, P_PRICE=?, P_COUNT=?"
					+ "WHERE P_NO=?");
			pstmt.setInt(1, product.getP_cno());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setInt(4, product.getP_count());
			pstmt.setInt(5, product.getP_no());
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		
	}

	@Override
	public ProductDTO selectProduct(ProductDTO product) throws Exception{
		
		Connection 		  conn 	   = null;
		PreparedStatement pstmt    = null;
		ResultSet         rs 	   = null;
		ProductDTO        dto      = null;

		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM PRODUCT WHERE P_NAME = ?");
			pstmt.setString(1, product.getP_name());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setP_no(rs.getInt(1));
				dto.setP_cno(rs.getInt(2));
				dto.setP_name(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_count(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();} 	catch (SQLException se) {}
		}
		return dto;
	}

	@Override
	public void updateCategoryNO(String productName, int categoryNO) throws Exception{
		
		Connection		  conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE PRODUCT SET P_CNO=?"
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, categoryNO);
			pstmt.setString(2, productName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}

	@Override
	public void updateName(String productName, String Name)  throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_NAME=?"
					+ "WHERE P_NAME=?");
			pstmt.setString(1, Name);
			pstmt.setString(2, productName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePrice(String productName, int price)  throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_PRICE=?"
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, price);
			pstmt.setString(2, productName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateCount(String productName, int count) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_COUNT=?"
					+ "WHER P_NAME=?");
			pstmt.setInt(1, count);
			pstmt.setString(2, productName);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
}
