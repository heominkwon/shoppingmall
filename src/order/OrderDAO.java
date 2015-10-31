package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDAO implements OrderInterface{

	private OrderDAO() {}
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance(){
		return instance;
	}	
	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context) initCtx.lookup("java:comp/env");
		DataSource ds   = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertOrder(OrderDTO order) throws Exception {
		
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO ORDERS "
					+ "(O_NO, O_MNO, O_PAY, O_ADDRESS, O_DATE) "
					+ "VALUES "
					+ "(ORDER_SEQ.NEXTVAL,?,?,?,?)");
			pstmt.setInt(1, order.getO_mno());
			pstmt.setInt(2, order.getO_pay());
			pstmt.setString(3, order.getO_address());
			pstmt.setDate(4, order.getO_date());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	
	@Override
	public void deleteOrder(int o_mno) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDEOS WHERE O_MNO=?");
			pstmt.setInt(1, o_mno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateO_mno(int o_no, int updateO_mno) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_MNO=? "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, updateO_mno);
			pstmt.setInt(2, o_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateO_pay(int o_no, int updateO_pay) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS O_PAY=? "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, updateO_pay);
			pstmt.setInt(2, o_no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	
	@Override
	public OrderDTO selectOrder(int o_no) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		OrderDTO 		  dto   = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO, O_MNO, O_PAY "
					+ "FROM ORDERS "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, o_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new OrderDTO();
				dto.setO_no(rs.getInt(1));
				dto.setO_mno(rs.getInt(2));
				dto.setO_pay(rs.getInt(3));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (rs    != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {pstmt.close();} catch (SQLException se) {}
		}
		return dto;
	}	
	
	@Override
	public List<OrderDTO> selectOrderAll() throws Exception {
		
		Connection 		  conn 	  = null;
		PreparedStatement pstmt   = null;
		ResultSet 		  rs 	  = null;
		List<OrderDTO>	  dtoList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO, O_MNO, O_PAY, O_ADDRESS, O_DATE "
					+ "FROM ORDERS");
			
			rs = pstmt.executeQuery();
			dtoList = new ArrayList<OrderDTO>();
			
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_no(rs.getInt(1));
				dto.setO_mno(rs.getInt(2));
				dto.setO_pay(rs.getInt(3));
				dto.setO_address(rs.getString(4));
				dto.setO_date(rs.getDate(5));
				dtoList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs	  != null) try {rs.close();}	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dtoList;
	}
	@Override
	public List<OrderDTO> selectOrderAllByO_pay(int o_pay) throws Exception {
		
		Connection		  conn    = null;
		PreparedStatement pstmt   = null;
		ResultSet		  rs      = null;
		List<OrderDTO>    dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO, O_MNO, O_PAY "
					+ "FROM ORDERS "
					+ "WHERE O_PAY=?");
			pstmt.setInt(1, o_pay);
			
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<OrderDTO>();
			
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_no(rs.getInt(1));
				dto.setO_mno(rs.getInt(2));
				dto.setO_pay(rs.getInt(3));
				dtolist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return dtolist;
	}
	@Override
	public List<OrderDTO> selectOrderAllByO_mno(int o_mno) throws Exception {
		
		Connection 		  conn    = null;
		PreparedStatement pstmt   = null;
		ResultSet		  rs      = null;
		List<OrderDTO>    dtolist = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO, O_MNO, O_PAY "
					+ "FROM ORDERS "
					+ "WHERE O_MNO=?");
			pstmt.setInt(1, o_mno);
			
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<OrderDTO>();
			
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_no(rs.getInt(1));
				dto.setO_mno(rs.getInt(2));
				dto.setO_pay(rs.getInt(3));
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
	public int IsPaid(int NEED_orderNO) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		int               IsPaid= 0;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_PAY "
					+ "FROM ORDERS "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, NEED_orderNO);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				IsPaid = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return IsPaid;
	}

	@Override
	public List<Integer> selectO_noAll() throws Exception {
		
		Connection		  conn		  = null;
		PreparedStatement pstmt		  = null;
		ResultSet		  rs 		  = null;
		List<Integer> 	  o_noList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO "
					+ "FROM ORDERS ");
			
			rs = pstmt.executeQuery();
			o_noList = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer o_no = new Integer(rs.getInt(1));
				o_noList.add(o_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return o_noList;
	}
	@Override
	public List<Integer> selectO_noAll(int o_mno) throws Exception {
		
		Connection 		  conn 			= null;
		PreparedStatement pstmt 		= null;
		ResultSet 		  rs 			= null;
		List<Integer>	  o_noList  = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO "
					+ "FROM ORDERS "
					+ "WHERE O_MNO=?");
			pstmt.setInt(1, o_mno);
			
			rs = pstmt.executeQuery();
			o_noList = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer o_no = new Integer(rs.getInt(1));
				o_noList.add(o_no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return o_noList;
	}

}
