package product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public void deleteProduct(int p_no) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(""
					+ "DELETE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void deleteProduct(String p_name) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(""
					+ "DELETE "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}	
	}
	
	@Override
	public void updateP_cno(int p_no, int updateP_cno) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_CNO=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, updateP_cno);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}	
	@Override
	public void updateP_cno(String p_name, int updateP_cno) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_CNO=? "
					+ "WHERE P_CNO=?");
			pstmt.setInt(1, updateP_cno);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}

	@Override
	public void updateP_name(int p_no, String updateP_name) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UODATE "
					+ "PRODUCT "
					+ "SET P_NAME=? "
					+ "WHERE P_NO=?");
			pstmt.setString(1, updateP_name);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null)  try {conn.close();} catch (SQLException se) {}
		}
	}
	@Override
	public void updateP_name(String p_name, String updateP_name) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_NAME=? "
					+ "WHERE P_NAME");
			pstmt.setString(1, updateP_name);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateP_price(int p_no, int updateP_price)  throws Exception{
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PRICE=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, updateP_price);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}	
	@Override
	public void updateP_price(String p_name, int updteP_price) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PRICE=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, updteP_price);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateP_count(int p_no, int updateP_count) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_COUNT=? "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, updateP_count);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}	
	@Override
	public void updateP_count(String p_name, int updateP_count) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_COUNT=? "
					+ "WHERE P_NAME=?");
			pstmt.setInt(1, updateP_count);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
		
	@Override
	public void updateP_desc(int p_no, String updateP_desc) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_DESC=? "
					+ "WHERE P_NO=?");
			pstmt.setString(1, updateP_desc);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateP_desc(String p_name, String updateP_desc) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_DESC=? "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, updateP_desc);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void updateP_path(int p_no, String updateP_path) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PATH=? "
					+ "WHERE P_NO=?");
			pstmt.setString(1, updateP_path);
			pstmt.setInt(2, p_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateP_path(String p_name, String updateP_path) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "PRODUCT "
					+ "SET P_PATH=? "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, updateP_path);
			pstmt.setString(2, p_name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public ProductDTO selectProduct(int p_no) throws Exception {
		
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
			pstmt.setInt(1, p_no);
			
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
	public ProductDTO selectProduct(String p_name) throws Exception {
		
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
			pstmt.setString(1, p_name);
			
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
	public int selectP_no(String p_name) throws Exception {

		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		int			  	  p_no 	= 0;

		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_NO "
							+ "FROM PRODUCT "
							+ "WHERE P_NAME=?");
			pstmt.setString(1, p_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_no = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
		return p_no;
	}
	@Override
	public String selectP_name(int p_no) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		String			  p_name 	= "";
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_NAME "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_name = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
		return p_name;
	}

	@Override
	public int selectP_price(int p_no) throws Exception {
			
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		int			  	  p_price 	= 0;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PRICE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_price = rs.getInt(1);
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
	public int selectP_price(String p_name) throws Exception {
		
	Connection		  conn 		= null;
	PreparedStatement pstmt 	= null;
	ResultSet		  rs 		= null;
	int			  	  p_price 	= 0;
	
	try {
		conn  = getConnection();
		pstmt = conn.prepareStatement(
				"SELECT P_PRICE "
				+ "FROM PRODUCT "
				+ "WHERE P_NAME=?");
		pstmt.setString(1, p_name);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			p_price = rs.getInt(1);
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
	public int selectP_count(int p_no) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		int			  	  p_price 	= 0;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PRICE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_price = rs.getInt(1);
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
	public int selectP_count(String p_name) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		int			  	  p_price 	= 0;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PRICE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setString(1, p_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_price = rs.getInt(1);
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
	public String selectP_desc(int p_no) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		String			  p_desc 	= "";
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_DESC "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_desc = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_desc;
	}
	@Override
	public String selectP_desc(String p_name) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		String			  p_desc 	= "";
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_DESC "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, p_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_desc = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_desc;
	}

	@Override
	public String selectP_path(int p_no) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		String			  p_desc 	= "";
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PATH "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_desc = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_desc;
	}
	@Override
	public String selectP_path(String p_name) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		String			  p_desc 	= "";
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_PATH "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, p_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_desc = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_desc;
	}

	@Override
	public Timestamp selectP_regdate(int p_no) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		Timestamp		  p_regdate = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_REGDATE "
					+ "FROM PRODUCT "
					+ "WHERE P_NO=?");
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_regdate = rs.getTimestamp(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_regdate;
	}
	@Override
	public Timestamp  selectP_regdate(String p_name) throws Exception {
		
		Connection		  conn 		= null;
		PreparedStatement pstmt 	= null;
		ResultSet		  rs 		= null;
		Timestamp		  p_regdate = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_REGDATE "
					+ "FROM PRODUCT "
					+ "WHERE P_NAME=?");
			pstmt.setString(1, p_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p_regdate = rs.getTimestamp(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}	catch (SQLException se) {}
		}
			return p_regdate;
	}
	
	@Override
	public List<Integer> selectP_noAll() throws Exception {
		
		Connection 			conn 		  = null;
		PreparedStatement   pstmt 		  = null;
		ResultSet 			rs 			  = null;
		List<Integer> 		p_noList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_NO "
					+ "FROM PRODUCT");
			
			rs = pstmt.executeQuery();
			p_noList = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer p_no = new Integer(rs.getInt(1));
				p_noList.add(p_no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return p_noList;
	}
	@Override
	public List<String>  selectP_nameAll() throws Exception {
		
		Connection 			conn 		  = null;
		PreparedStatement   pstmt 		  = null;
		ResultSet 			rs 			  = null;
		List<String> 		p_nameList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT P_NAME "
					+ "FROM PRODUCT");
			
			rs = pstmt.executeQuery();
			p_nameList = new ArrayList<String>();
			
			while (rs.next()) {
				String p_name = rs.getString(1);
				p_nameList.add(p_name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return p_nameList;
	}
	
	@Override
	public List<ProductDTO> selectAll() throws Exception {
		
		Connection		  conn    = null;
		PreparedStatement pstmt   = null;
		ResultSet		  rs      = null;
		List<ProductDTO>  dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "P_NO, P_CNO, P_NAME, P_PRICE, P_COUNT, P_DESC, P_PATH, P_REGDATE "
					+ "FROM PRODUCT ");
			
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
	public List<ProductDTO> selectAll(int NEED_categoryNO) throws Exception {
		
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
	public int getProductCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from PRODUCT");
			//board테이블에 레코드갯수를 요청하는 쿼리문
			rs = pstmt.executeQuery();
			if (rs.next()) {//게시글이 존재한다면 게시글의 총 갯수를 변수 x에 저장
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //총게시글의 수 반환
	}
	@Override
	public List getProducts(int start, int end) throws Exception {
		//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List ProductList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, r from (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, rownum r from (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate from product order by p_no desc) order by p_no desc) where r >= ? and r <= ?"); 
					/* ref(게시글 그룹)번호는 내림차순으로 re_step(답변글 정렬순서)는 오름차순으로 정렬을해서 모든 게시글들을 정렬을하고
					 * rownum r로써 줄번호를 매긴후 이메소드에서 받아온 매개변수값 start(시작줄번호)와 end(끝줄번호)값 사이에 게시글들을 요청하는 쿼리문 
					 */
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
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
						//ProductList에 상품들을 list로 저장한다
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
	public int getProductCountByCategory(int c_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT JOIN CATEGORY ON PRODUCT.P_CNO = CATEGORY.C_NO WHERE CATEGORY.C_NO = ? ORDER BY P_NO DESC");

			pstmt.setInt(1, c_no);
			
			//board테이블에 레코드갯수를 요청하는 쿼리문
			rs = pstmt.executeQuery();
			if (rs.next()) {//게시글이 존재한다면 게시글의 총 갯수를 변수 x에 저장
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //총게시글의 수 반환
	}
	@Override
	public List getProductsByCategory(int c_no, int start, int end) throws Exception {
		//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
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
			if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
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
				//ProductList에 상품들을 list로 저장한다
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