package product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.proxy.annotation.Pre;


public class ProductDAO implements ProductInterface{
	
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance(){
		return instance;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds   = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertProduct(ProductDTO product) throws Exception{
		
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO PRODUCT "
					+ "(P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE) "
					+ " VALUES "
					+ "(PRODUCT_SEQ.NEXTVAL,?,?,?,?,?,?,?)");
			pstmt.setInt(1, product.getP_cno());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3,product.getP_price());
			pstmt.setInt(4, product.getP_count());
			pstmt.setString(5, product.getP_desc());
			pstmt.setString(6, product.getP_path());
			pstmt.setTimestamp(7, product.getP_regdate());
			
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
					"DELETE FROM PRODUCT WHERE P_NO=?");
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
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_CNO=?, P_NAME=?, P_PRICE=?, P_COUNT=?, P_DESC, P_PATH, P_REGDATE "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, product.getP_cno());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setInt(4, product.getP_count());
			pstmt.setString(6, product.getP_desc());
			pstmt.setString(6, product.getP_path());
			pstmt.setTimestamp(7, product.getP_regdate());
			pstmt.setInt(8, product.getP_no());
			
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
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE "
					+ "FROM PRODUCT WHERE P_NO=?");
			pstmt.setInt(1, product.getP_no());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setP_no(rs.getInt(1));
				dto.setP_cno(rs.getInt(2));
				dto.setP_name(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_count(rs.getInt(5));
				dto.setP_desc(rs.getString(6));
				dto.setP_path(rs.getString(7));
				dto.setP_regdate(rs.getTimestamp(8));
				dto.setP_no(9);
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
	public void deleteProduct(int NEED_productNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(""
					+ "DELETE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
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
	public void deleteProduct(String NEED_productNAME) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(""
					+ "DELETE "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, NEED_productNAME);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}	
	}
	
	
	
	@Override
	public void updateNO_NO(int NEED_productNO, int change_productNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NO=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, change_productNO);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
		
	

	@Override
	public void updateNO_CNO(int NEED_categoryNO, int change_productNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NO=? "
					+ "WHERE P_CNO=?");
			pstmt.setInt(1, change_productNO);
			pstmt.setInt(2, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		
	}

	@Override
	public void updateNO_NAME(String NEED_productNAME, int change_productNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NO=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, change_productNO);
			pstmt.setString(2, NEED_productNAME);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	
	
	@Override
	public void updateCNO_NO(int NEED_productNO, int change_categoryNO) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_CNO=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, change_categoryNO);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateCNO_CNO(int NEED_categoryNO, int change_categoryNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_CNO=? "
					+ "WHERE P_CNO=?");
			pstmt.setInt(1, change_categoryNO);
			pstmt.setInt(2, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}

	@Override
	public void updateCNO_NAME(String NEED_productNAME, int change_categoryNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_CNO=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, change_categoryNO);
			pstmt.setString(2, NEED_productNAME);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updateName_NO(int NEED_productNO, String change_productName) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NAME=? "
					+ "WHERE P_NO");
			pstmt.setString(1, change_productName);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateName_CNO(int NEED_categoryNO, String change_productName) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NAME=? "
					+ "WHERE P_CNO");
			pstmt.setString(1, change_productName);
			pstmt.setInt(2, NEED_categoryNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateName_NAME(String NEED_productName, String change_productName)  throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NAME=? "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, change_productName);
			pstmt.setString(2, NEED_productName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updatePrice_NO(int NEED_productNO, int change_price)  throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PRICE=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, change_price);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updatePrice_NAME(String NEED_productName, int change_price) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PRICE=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, change_price);
			pstmt.setString(2, NEED_productName);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updateCount_NO(int NEED_productNO, int change_count) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_COUNT=?"
					+ "WHERE P_NO=?");
			pstmt.setInt(1, change_count);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateCount_NAME(String NEED_productNAME, int change_count) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_COUNT=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, change_count);
			pstmt.setString(2, NEED_productNAME);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	
	
	@Override
	public void updateDesc_NO(int NEED_productNO, String change_desc) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_DESC=? "
					+ "WHERE P_NO=?");
			pstmt.setString(1, change_desc);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateDesc_NAME(String NEED_productNAME, String change_desc) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_DESC=? "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, change_desc);
			pstmt.setString(2, NEED_productNAME);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updatePath_NO(int NEED_productNO, String change_path) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PATH=? "
					+ "WHERE P_NO=?");
			pstmt.setString(1, change_path);
			pstmt.setInt(2, NEED_productNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePath_NAME(String NEED_productNAME, String change_path) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PATH=? "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, change_path);
			pstmt.setString(2, NEED_productNAME);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	
	

	@Override
	public ProductDTO selectProduct(int NEED_productNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		ProductDTO 		  dto   = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, NEED_productNO);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setP_no(rs.getInt(1));
				dto.setP_cno(rs.getInt(2));
				dto.setP_name(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_count(rs.getInt(5));
				dto.setP_desc(rs.getString(6));
				dto.setP_path(rs.getString(7));
				dto.setP_regdate(rs.getTimestamp(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dto;
	}
	

	@Override
	public ProductDTO selectProduct(String NEED_productNAME) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		ProductDTO 		  dto   = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, NEED_productNAME);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setP_no(rs.getInt(1));
				dto.setP_cno(rs.getInt(2));
				dto.setP_name(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_count(rs.getInt(5));
				dto.setP_desc(rs.getString(6));
				dto.setP_path(rs.getString(7));
				dto.setP_regdate(rs.getTimestamp(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dto;
	}

	@Override
	public List<ProductDTO> selectsProduct_CNO(int NEED_categoryNO) throws Exception {
		
		Connection		  conn    = null;
		PreparedStatement pstmt   = null;
		ResultSet		  rs      = null;
		List<ProductDTO>  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE "
					+ "FROM PRODUCT "
					+ "WHERE P_CNO=?");
			pstmt.setInt(1, NEED_categoryNO);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_no(rs.getInt(1));
				dto.setP_cno(rs.getInt(2));
				dto.setP_name(rs.getString(3));
				dto.setP_price(rs.getInt(4));
				dto.setP_count(rs.getInt(5));
				dto.setP_desc(rs.getString(6));
				dto.setP_path(rs.getString(7));
				dto.setP_regdate(rs.getTimestamp(8));
				dtolist.add(dto);
			}
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (rs != null)    try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
		return dtolist;
	}
}
