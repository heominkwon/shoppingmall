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

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

public class OrderDAO implements OrderInterface{

	private OrderDAO() {}
	
	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance(){
		return instance;
	}
	@Override
	public int getMemOrderCount(int O_MNO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from orders where O_MNO=?");
			//board테이블에 레코드갯수를 요청하는 쿼리문
			pstmt.setInt(1, O_MNO);
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
	public int getNewOrderCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from orders where O_PAY=0");
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
	public int getComOrderCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from orders where O_PAY=1 or O_PAY=3");
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
	public int getOrderCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from orders");
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
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context) initCtx.lookup("java:comp/env");
		DataSource ds   = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void insertOrder(OrderDTO order) throws Exception {
		
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
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
	public void deleteOrder(OrderDTO order) throws Exception {
		
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDERS WHERE O_NO=?");
			pstmt.setInt(1, order.getO_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null)  try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateOrder(OrderDTO order) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_NO=?, O_MNO=?, O_PAY=?, O_ADDRESS=?, O_DATE=? " 
					+ "WHERE O_NO=?");
			pstmt.setInt(1, order.getO_mno());
			pstmt.setInt(2, order.getO_pay());
			pstmt.setString(3, order.getO_address());
			pstmt.setDate(4, order.getO_date());
			pstmt.setInt(5, order.getO_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {} 
		}
	}

	@Override
	public OrderDTO selectOrder(OrderDTO order) throws Exception {
		
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;;
		OrderDTO 		  dto 	= null;
		
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO, O_MNO, O_PAY, O_ADDRESS, O_DATE "
					+ "FROM ORDERS "
					+ "WHERE "
					+ "O_NO=?");
			pstmt.setInt(1, order.getO_no());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new OrderDTO();
				dto.setO_no(rs.getInt(1));
				dto.setO_mno(rs.getInt(2));
				dto.setO_pay(rs.getInt(3));
				dto.setO_address(rs.getString(4));
				dto.setO_date(rs.getDate(5));
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
	public List<OrderDTO> selectsMemOrder(int start, int end, int O_MNO) throws Exception{
		//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List OrderList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select o_no, o_mno, o_pay, o_address, o_date, r from (select o_no, o_mno, o_pay, o_address, o_date, rownum r from (select o_no, o_mno, o_pay, o_address, o_date from orders where O_MNO=? order by o_date desc) order by o_date desc) where r >= ? and r <= ?"); 
					/* ref(게시글 그룹)번호는 내림차순으로 re_step(답변글 정렬순서)는 오름차순으로 정렬을해서 모든 게시글들을 정렬을하고
					 * rownum r로써 줄번호를 매긴후 이메소드에서 받아온 매개변수값 start(시작줄번호)와 end(끝줄번호)값 사이에 게시글들을 요청하는 쿼리문 
					 */
					pstmt.setInt(1, O_MNO);
					pstmt.setInt(2, start); 
					pstmt.setInt(3, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
						OrderList = new ArrayList(end); 
						do{ 
							OrderDTO order= new OrderDTO();
							order.setO_no(rs.getInt("o_no"));								
							order.setO_mno(rs.getInt("o_mno"));
							order.setO_pay(rs.getInt("o_pay"));
							order.setO_address(rs.getString("o_address"));
							order.setO_date(rs.getDate("o_date"));							
							OrderList.add(order);
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

		
		return OrderList;
	}
	
	
	@Override
	public List<OrderDTO> selectsComOrder(int start, int end) throws Exception{
		//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List OrderList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select o_no, o_mno, o_pay, o_address, o_date, r from (select o_no, o_mno, o_pay, o_address, o_date, rownum r from (select o_no, o_mno, o_pay, o_address, o_date from orders where O_PAY=1 or O_PAY=3 order by o_date desc) order by o_date desc) where r >= ? and r <= ?"); 
					/* ref(게시글 그룹)번호는 내림차순으로 re_step(답변글 정렬순서)는 오름차순으로 정렬을해서 모든 게시글들을 정렬을하고
					 * rownum r로써 줄번호를 매긴후 이메소드에서 받아온 매개변수값 start(시작줄번호)와 end(끝줄번호)값 사이에 게시글들을 요청하는 쿼리문 
					 */
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
						OrderList = new ArrayList(end); 
						do{ 
							OrderDTO order= new OrderDTO();
							order.setO_no(rs.getInt("o_no"));								
							order.setO_mno(rs.getInt("o_mno"));
							order.setO_pay(rs.getInt("o_pay"));
							order.setO_address(rs.getString("o_address"));
							order.setO_date(rs.getDate("o_date"));							
							OrderList.add(order);
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

		
		return OrderList;
	}
	
	@Override
	public List<OrderDTO> selectsNewOrder(int start, int end) throws Exception{
		//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List OrderList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select o_no, o_mno, o_pay, o_address, o_date, r from (select o_no, o_mno, o_pay, o_address, o_date, rownum r from (select o_no, o_mno, o_pay, o_address, o_date from orders where O_PAY=0 order by o_date desc) order by o_date desc) where r >= ? and r <= ?"); 
					/* ref(게시글 그룹)번호는 내림차순으로 re_step(답변글 정렬순서)는 오름차순으로 정렬을해서 모든 게시글들을 정렬을하고
					 * rownum r로써 줄번호를 매긴후 이메소드에서 받아온 매개변수값 start(시작줄번호)와 end(끝줄번호)값 사이에 게시글들을 요청하는 쿼리문 
					 */
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
						OrderList = new ArrayList(end); 
						do{ 
							OrderDTO order= new OrderDTO();
							order.setO_no(rs.getInt("o_no"));								
							order.setO_mno(rs.getInt("o_mno"));
							order.setO_pay(rs.getInt("o_pay"));
							order.setO_address(rs.getString("o_address"));
							order.setO_date(rs.getDate("o_date"));							
							OrderList.add(order);
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

		
		return OrderList;
	}
	@Override
	public List<OrderDTO> selectsFinOrder()throws Exception{
		Connection 		  conn 	  = null;
		PreparedStatement pstmt   = null;
		ResultSet 		  rs 	  = null;
		List<OrderDTO>	  dtoList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM ORDERS WHERE O_PAY=4");
			
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
	public List<OrderDTO> selectsAgeFinOrder(int age, int year)throws Exception{
		Connection 		  conn 	  = null;
		PreparedStatement pstmt   = null;
		ResultSet 		  rs 	  = null;
		ResultSet 		  rs2 	  = null;
		List<OrderDTO>	  dtoList = new ArrayList<OrderDTO>();
		int year2=year-2000;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement("select M_NO from member where 1000000+(?*10000)-M_BIRTH>? and 1000000+(?*10000)-M_BIRTH<?");
			pstmt.setInt(1, year2);
			pstmt.setInt(2, age*10000);
			pstmt.setInt(3, year2);
			pstmt.setInt(4, age*10000+100000);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				pstmt=conn.prepareStatement("SELECT * FROM ORDERS WHERE O_PAY=4 and O_MNO=?");				
				pstmt.setInt(1, rs.getInt(1));
				rs2 = pstmt.executeQuery();
				while(rs2.next()){
					OrderDTO dto = new OrderDTO();
					dto.setO_no(rs2.getInt(1));					
					dto.setO_mno(rs2.getInt(2));
					dto.setO_pay(rs2.getInt(3));
					dto.setO_address(rs2.getString(4));
					dto.setO_date(rs2.getDate(5));
					dtoList.add(dto);
				}
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
	public List<OrderDTO> selectsOrder() throws Exception {
		
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
	public void delete_NO(int NEED_orderNO) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDERS WHERE O_NO=?");
			pstmt.setInt(1, NEED_orderNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void delete_MNO(int NEED_memberNO) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"DELETE FROM ORDERS WHERE O_MNO=?");
			pstmt.setInt(1, NEED_memberNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateNO_NO(int NEED_orderNO, int change_orderNO) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_NO=? "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, change_orderNO);
			pstmt.setInt(2, NEED_orderNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateNO_MNO(int NEED_memberNO, int change_orderNO) throws Exception {
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_NO=? "
					+ "WHERE O_MNO=?");
			pstmt.setInt(1, change_orderNO);
			pstmt.setInt(2, NEED_memberNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateMNO_NO(int NEED_orderNO, int change_memberNO) throws Exception {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_MNO=? "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, change_memberNO);
			pstmt.setInt(2, NEED_orderNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updateMNO_MNO(int NEED_memberNO, int change_memberNO) throws Exception {
		
		Connection 		  conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS SET O_MNO=? "
					+ "WHERE O_MNO=?");
			pstmt.setInt(1, change_memberNO);
			pstmt.setInt(2, NEED_memberNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePAY_NO(int NEED_orderNO, int change_PAY) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS set O_PAY=? "
					+ "WHERE O_NO=?");
			pstmt.setInt(1, change_PAY);
			pstmt.setInt(2, NEED_orderNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}
	@Override
	public void updateO_address(int NEED_orderNO, String change_address) throws Exception {
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS set O_ADDRESS=? "
					+ "WHERE O_NO=?");
			pstmt.setString(1, change_address);
			pstmt.setInt(2, NEED_orderNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public void updatePAY_MNO(int NEED_memberNO, int change_PAY) throws Exception {
		
		Connection 	      conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE ORDERS O_PAY=? "
					+ "WHERE O_MNO");
			pstmt.setInt(1, change_PAY);
			pstmt.setInt(2, NEED_memberNO);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
	}

	@Override
	public OrderDTO selectOrder(int NEED_orderNO) throws Exception {
		
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
			pstmt.setInt(1, NEED_orderNO);
			
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
	public List<OrderDTO> selectsOrders_PAY(int NEED_PAY) throws Exception {
		
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
			pstmt.setInt(1, NEED_PAY);
			
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
	public List<OrderDTO> selectsOrders_MNO(int NEED_memberNO) throws Exception {
		
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
			pstmt.setInt(1, NEED_memberNO);
			
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
	public List<Integer> selectsOrder_NO(int NEED_memberNO) throws Exception {
		
		Connection 		  conn 			= null;
		PreparedStatement pstmt 		= null;
		ResultSet 		  rs 			= null;
		List<Integer>	  orderNO_list  = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO "
					+ "FROM ORDERS "
					+ "WHERE O_MNO=?");
			pstmt.setInt(1, NEED_memberNO);
			
			rs = pstmt.executeQuery();
			orderNO_list = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer orderNO = new Integer(rs.getInt(1));
				orderNO_list.add(orderNO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();}    catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return orderNO_list;
	}

	@Override
	public List<Integer> selectsOrder_NO() throws Exception {
		
		Connection		  conn		  = null;
		PreparedStatement pstmt		  = null;
		ResultSet		  rs 		  = null;
		List<Integer> 	  orderNOList = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT O_NO "
					+ "FROM ORDERS ");
			
			rs = pstmt.executeQuery();
			orderNOList = new ArrayList<Integer>();
			
			while (rs.next()) {
				Integer orderNO = new Integer(rs.getInt(1));
				orderNOList.add(orderNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs 	  != null) try {rs.close();} 	catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		return orderNOList;
	}
}
