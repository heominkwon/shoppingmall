package product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		Context envCtx = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertProduct(ProductDTO product) throws Exception{
		
		Connection conn 		= null;
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
		Connection conn 		= null;
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
	public void updateProduct(ProductDTO product) throws Exception{
		
	}

	@Override
	public ProductDTO selectProduct(ProductDTO product) throws Exception{
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return product;
		
	}

	@Override
	public void updateCategoryNO(int categoryNO) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateName(String Name)  throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePrice(int price)  throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCount(int count) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int p_no) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProdut(String p_name) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
