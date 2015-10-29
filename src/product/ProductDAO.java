package product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			dtolist = new ArrayList<ProductDTO>();
			
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setP_no(rs.getInt("P_NO"));
				dto.setP_cno(rs.getInt("P_CNO"));
				dto.setP_name(rs.getString("P_NAME"));
				dto.setP_price(rs.getInt("P_PRICE"));
				dto.setP_count(rs.getInt("P_COUNT"));
				dto.setP_desc(rs.getString("P_DESC"));
				dto.setP_path(rs.getString("P_PATH"));
				dto.setP_regdate(rs.getTimestamp("P_REGDATE"));
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

	@Override
	public List<Integer> selectsProduct_NO() throws Exception {
		
		Connection 			conn 		  = null;
		PreparedStatement   pstmt 		  = null;
		ResultSet 			rs 			  = null;
		List<Integer> 		productNoList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_NO "
					+ "FROM PRODUCT");
			
			rs = pstmt.executeQuery();
			productNoList = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer productNo = new Integer(rs.getInt(1));
				productNoList.add(productNo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return productNoList;
	}
	
	
	

	@Override
	public Integer selectPrice_nNO(Integer need_no) throws Exception {
			
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		Integer			  p_price 	= null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PRICE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, need_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_price = new Integer(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
		return p_price;
	}

	@Override
	public int getProductCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from PRODUCT");
			//board���̺� ���ڵ尹���� ��û�ϴ� ������
			rs = pstmt.executeQuery();
			if (rs.next()) {//�Խñ��� �����Ѵٸ� �Խñ��� �� ������ ���� x�� ����
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //�ѰԽñ��� �� ��ȯ
	}

	@Override
	public List getProducts(int start, int end) throws Exception {
		//�Ű������� ���� start�� ���� �ٺ��� end�� ���� �ٱ����� �Խñ۵��� ������ List�� ��ȯ���ִ� �޼ҵ��̴�.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List ProductList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, r from (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, rownum r from (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate from product order by p_no desc) order by p_no desc) where r >= ? and r <= ?"); 
					/* ref(�Խñ� �׷�)��ȣ�� ������������ re_step(�亯�� ���ļ���)�� ������������ �������ؼ� ��� �Խñ۵��� �������ϰ�
					 * rownum r�ν� �ٹ�ȣ�� �ű��� �̸޼ҵ忡�� �޾ƿ� �Ű������� start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ۵��� ��û�ϴ� ������ 
					 */
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ��� �����Ѵٸ�
						ProductList = new ArrayList(end); 
						do{ 
							ProductDTO product= new ProductDTO();
							product.setP_no(rs.getInt("p_no"));								
							product.setP_cno(rs.getInt("p_cno"));
							product.setP_name(rs.getString("p_name"));
							product.setP_price(rs.getInt("p_price"));
							product.setP_count(rs.getInt("p_count"));
							product.setP_desc(rs.getString("p_desc"));
							product.setP_path(rs.getString("p_path"));
							product.setP_regdate(rs.getTimestamp("p_regdate"));
							ProductList.add(product);
						}while(rs.next());
						//ProductList�� ��ǰ���� list�� �����Ѵ�
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		
		return ProductList;
	}

	@Override
	public ProductDTO getProduct(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO product=null;
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(
			"select * from product where p_no = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new ProductDTO();
				product.setP_no(rs.getInt("P_NO"));
				product.setP_cno(rs.getInt("P_CNO"));
				product.setP_name(rs.getString("P_NAME"));
				product.setP_price(rs.getInt("P_PRICE"));
				product.setP_count(rs.getInt("P_COUNT"));
				product.setP_desc(rs.getString("P_DESC"));
				product.setP_path(rs.getString("P_PATH"));				
				product.setP_regdate(rs.getTimestamp("P_REGDATE"));
			
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return product;
	}

	@Override
	public int getProductCountByCategory(int c_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT JOIN CATEGORY ON PRODUCT.P_CNO = CATEGORY.C_NO WHERE CATEGORY.C_NO = ? ORDER BY P_NO DESC");

			pstmt.setInt(1, c_no);
			
			//board���̺� ���ڵ尹���� ��û�ϴ� ������
			rs = pstmt.executeQuery();
			if (rs.next()) {//�Խñ��� �����Ѵٸ� �Խñ��� �� ������ ���� x�� ����
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //�ѰԽñ��� �� ��ȯ
	}

	@Override
	public List getProductsByCategory(int c_no, int start, int end) throws Exception {
		//�Ű������� ���� start�� ���� �ٺ��� end�� ���� �ٱ����� �Խñ۵��� ������ List�� ��ȯ���ִ� �޼ҵ��̴�.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List ProductList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM PRODUCT JOIN CATEGORY ON PRODUCT.P_CNO = CATEGORY.C_NO WHERE CATEGORY.C_NO = ? AND ROWNUM >= ? AND ROWNUM < ? ORDER BY P_NO DESC"); 

			pstmt.setInt(1, c_no);
			pstmt.setInt(2, start); 
			pstmt.setInt(3, end); 

			rs = pstmt.executeQuery();
			if (rs.next()) {//start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ��� �����Ѵٸ�
				ProductList = new ArrayList(end); 
				do{ 
					ProductDTO product= new ProductDTO();
					product.setP_no(rs.getInt("p_no"));								
					product.setP_cno(rs.getInt("p_cno"));
					product.setP_name(rs.getString("p_name"));
					product.setP_price(rs.getInt("p_price"));
					product.setP_count(rs.getInt("p_count"));
					product.setP_desc(rs.getString("p_desc"));
					product.setP_path(rs.getString("p_path"));
					product.setP_regdate(rs.getTimestamp("p_regdate"));
					ProductList.add(product);
				}while(rs.next());
				//ProductList�� ��ǰ���� list�� �����Ѵ�
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		
		return ProductList;
	}
}
