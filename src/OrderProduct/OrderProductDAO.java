package OrderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderProductDAO implements OrderProductInterface{

	private OrderProductDAO() {}
	
	private static OrderProductDAO instance = new OrderProductDAO();
	
	public static OrderProductDAO getInstace() {
		return instance;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds   = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@SuppressWarnings("resource")
	@Override
	public void insertOrderProduct(OrderProductDTO orderproduct) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int orderNo = -1;
		try {
			conn  = getConnection();
/*			pstmt = conn.prepareStatement("SELECT "
					+ "LAST_NUMBER "
					+ "FROM USER_SEQUENCES "
					+ "WHERE SEQUENCE_NAME = UPPER('ORDER_SEQ')");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				orderNo = rs.getInt(1);
			}
			*/
			pstmt = conn.prepareStatement(
					"INSERT INTO ORDER_PRODUCT "
					+ "(OP_NO, OP_ONO, OP_PNO, OP_COUNT, OP_PRICE) "
					+ "VALUES "
					+ "(ORDER_PRODUCT_SEQ.NEXTVAL,ORDER_SEQ.CURRVAL,?,?,?)");
			pstmt.setInt(1, orderproduct.getOP_pno());
			pstmt.setInt(2, orderproduct.getOP_count());
			pstmt.setInt(3, orderproduct.getOP_price());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void deleteOrderProduct(OrderProductDTO orderproduct) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDER_PRODUCT WHERE OP_NO=?");
			pstmt.setInt(1, orderproduct.getOP_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateOrderProduct(OrderProductDTO orderproduct) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_ONO=?, OP_PNO=?, OP_COUNT, OP_PRICT "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, orderproduct.getOP_ono());
			pstmt.setInt(2, orderproduct.getOP_pno());
			pstmt.setInt(3, orderproduct.getOP_count());
			pstmt.setInt(4, orderproduct.getOP_price());
			pstmt.setInt(5, orderproduct.getOP_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public OrderProductDTO selectOrderProduct(OrderProductDTO orderproduct) throws Exception {
		
		Connection		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		OrderProductDTO   dto 	= null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "OP_NO, OP_ONO, OP_PNO, OP_COUNT, OP_PRICE "
					+ "FROM ORDER_PRODUCT "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, orderproduct.getOP_no());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new OrderProductDTO();
				dto.setOP_no(rs.getInt(1));
				dto.setOP_ono(rs.getInt(2));
				dto.setOP_no(rs.getInt(3));
				dto.setOP_count(rs.getInt(4));
				dto.setOP_price(rs.getInt(5));
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
	public List<OrderProductDTO> selectsOrderProduct() throws Exception {
		
		Connection		  	  conn    = null;
		PreparedStatement	  pstmt   = null;
		ResultSet 		  	  rs 	  = null;
		List<OrderProductDTO> dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "OP_NO, OP_ONO, OP_PNO, OP_COUNT, OP_PRICE "
					+ "FROM ORDER_PRODUCT ");
			
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<OrderProductDTO>();
			
			while (rs.next()) {
				OrderProductDTO dto = new OrderProductDTO();
				dto.setOP_no(rs.getInt(1));
				dto.setOP_ono(rs.getInt(2));
				dto.setOP_pno(rs.getInt(3));
				dto.setOP_count(rs.getInt(4));
				dto.setOP_price(rs.getInt(5));
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
	public void delete_nNO(int need_orderproductNO) throws Exception {

		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDER_PRODUCT WHERE OP_NO=?");
			pstmt.setInt(1, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public void updateNO_nNO(int change_orderproductNO, int need_orderproductNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_NO=?  "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, change_orderproductNO);
			pstmt.setInt(2, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateNO_nONO(int change_orderproductNO, int need_ONO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_NO=?  "
					+ "WHERE OP_ONO=?");
			pstmt.setInt(1, change_orderproductNO);
			pstmt.setInt(2, need_ONO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateONO_nNO(int change_ONO, int need_orderproductNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_ONO=?  "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, change_ONO);
			pstmt.setInt(2, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}	
	}

	@Override
	public void updatePNO_nNO(int change_productNO, int need_orderproductNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_PNO=?  "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, change_productNO);
			pstmt.setInt(2, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}	
	}

	@Override
	public void updateCOUNT_nNO(int change_COUNT, int need_orderproductNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_COUNT=?  "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, change_COUNT);
			pstmt.setInt(2, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePRICE_nNO(int change_PRICE, int need_orderproductNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "ORDER_PRODUCT "
					+ "SET OP_PRICE=?  "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, change_PRICE);
			pstmt.setInt(2, need_orderproductNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	
	@Override
	public OrderProductDTO selectOrderProduct(int need_orderproductNO) throws Exception {
		
		Connection		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet		  rs 	= null;
		OrderProductDTO	  dto 	= null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "OP_NO, OP_ONO, OP_PNO, OP_COUNT, OP_PRICE "
					+ "FROM ORDER_PRODUCT "
					+ "WHERE OP_NO=?");
			pstmt.setInt(1, need_orderproductNO);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new OrderProductDTO();
				dto.setOP_no(rs.getInt(1));
				dto.setOP_no(rs.getInt(2));
				dto.setOP_pno(rs.getInt(3));
				dto.setOP_count(rs.getInt(4));
				dto.setOP_price(rs.getInt(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dto;
	}

	
	
	@Override
	public List<OrderProductDTO> selectsOrderProduct(int need_ONO) throws Exception {
		
		Connection			  conn    = null;
		PreparedStatement 	  pstmt   = null;
		ResultSet 			  rs 	  = null;
		List<OrderProductDTO> dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT OP_NO, OP_ONO, OP_PNO, OP_COUNT, OP_PRICE "
					+ "FROM ORDER_PRODUCT "
					+ "WHERE OP_ONO=?");
			pstmt.setInt(1, need_ONO);
			
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<OrderProductDTO>();
			
			while (rs.next()) {
				OrderProductDTO dto =  new OrderProductDTO();
				dto.setOP_no(rs.getInt(1));
				dto.setOP_ono(rs.getInt(2));
				dto.setOP_pno(rs.getInt(3));
				dto.setOP_count(rs.getInt(4));
				dto.setOP_price(rs.getInt(5));
				dtolist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dtolist;
	}
}
