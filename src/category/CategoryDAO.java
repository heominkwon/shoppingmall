package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public int getcategoryCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from category");
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
	public List getCategorys(int start, int end) throws Exception{
		//�Ű������� ���� start�� ���� �ٺ��� end�� ���� �ٱ����� �Խñ۵��� ������ List�� ��ȯ���ִ� �޼ҵ��̴�.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List CategoryList=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select c_no, c_name, r from (select c_no, c_name, rownum r from (select c_no, c_name from category order by c_no desc) order by c_no desc) where r >= ? and r <= ?"); 
					/* ref(�Խñ� �׷�)��ȣ�� ������������ re_step(�亯�� ���ļ���)�� ������������ �������ؼ� ��� �Խñ۵��� �������ϰ�
					 * rownum r�ν� �ٹ�ȣ�� �ű��� �̸޼ҵ忡�� �޾ƿ� �Ű������� start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ۵��� ��û�ϴ� ������ 
					 */
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {//start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ��� �����Ѵٸ�
						CategoryList = new ArrayList(end); 
						do{ 
							CategoryDTO category= new CategoryDTO();
							category.setC_no(rs.getInt("c_no"));
							category.setC_name(rs.getString("c_name"));						
							CategoryList.add(category);
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

		
		return CategoryList;
	
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
	public int deleteCategory(int num, String passwd) throws Exception{
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
					"delete from category where C_NO=?");
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
				dto.setC_name(rs.getString(2));
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
