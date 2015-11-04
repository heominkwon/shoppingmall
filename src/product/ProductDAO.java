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
		Context envCtx = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
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
	public void insertProduct(ProductDTO product) throws Exception{
		
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		
		try {		
				
			conn  = getConnection();
			pstmt = conn.prepareStatement(
					"insert into product(p_no,p_cno,p_name,p_price,p_count,p_desc,p_path,p_regdate) VALUES "
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
	public int deleteProduct(int num, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		int x=-1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select M_PW from member where M_ID =?");
			pstmt.setString(1,"admin");
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("M_PW");
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement(
					"delete from product where P_NO=?");//매개변수로 받아온 num <즉 해당 상품번호> 의 상품을 지우겠다는 쿼리문
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x= 1; 
				}else
					x= 0; 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}

	
	@Override
	public void updateProduct(ProductDTO product) throws Exception {	
		Connection 		  conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_CNO=?, P_NAME=?, P_PRICE=?, P_COUNT=?, P_DESC=?, P_PATH=?"
					+ "WHERE P_NO=?");
			pstmt.setInt(1, product.getP_cno());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setInt(4, product.getP_count());
			pstmt.setString(5, product.getP_desc());
			pstmt.setString(6, product.getP_path());
			pstmt.setInt(7, product.getP_no());			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn  != null) try {conn.close();}  catch (SQLException se) {}
		}
		
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
	
	/*"select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, r from 
	 * (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate, rownum r from 
	 * (select p_no, p_cno, p_name, p_price, p_count, p_desc, p_path, p_regdate from product order by p_no desc) order by p_no desc) 
	 * where r >= ? and r <= ?" 		
	 제일 안쪽 괄호안에 쿼리문 부터 보면 된다. (상품테이블의  P_no를 내림차순으로 정리함) -> (중간괄호 P_no가 내림차순으로 정렬된 상품 테이블을 rownum함 (table에 r생성 내림차순))
	 정렬된 rownum r 에서 상품을 검색 함 (조건 r이 ? 부터 ? 까지)  
	 */
				
				
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
	public String getProductName(int p_no) throws Exception{	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name="";
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(
			"select P_NAME from product where P_NO = ?"); 
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				name=rs.getString("P_NAME");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return name;
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
				dto.setP_no(rs.getInt("P_NO"));
				dto.setP_cno(rs.getInt("P_CNO"));
				dto.setP_name(rs.getString("P_NAME"));
				dto.setP_price(rs.getInt("P_PRICE"));
				dto.setP_count(rs.getInt("P_COUNT"));
				dto.setP_desc(rs.getString("P_DESC"));
				dto.setP_path(rs.getString("P_PATH"));
				dto.setP_regdate(rs.getTimestamp("P_REGDATE"));
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
					"UPDATE PRODUCT SET P_CNO=?"
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
	@Override
	public void updateDesc(String productName, String desc) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_DESC=?"
					+ "WHER P_NAME=?");
			pstmt.setString(1, desc);
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
	public void updatePath(String productName, String path) throws Exception{
		
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE PRODUCT SET P_PATH=?"
					+ "WHER P_NAME=?");
			pstmt.setString(1, path);
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
