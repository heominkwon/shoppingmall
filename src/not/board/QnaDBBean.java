package not.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDBBean {
	private static QnaDBBean instance = new QnaDBBean();
	
	public static QnaDBBean getInstance(){
		return instance;
	}
	private QnaDBBean(){}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public void insertQnaboard(QnaDataBean qnaboard)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int q_no = qnaboard.getQ_no();
		int ref = qnaboard.getRef();
		int q_step = qnaboard.getQ_step();
		int q_level = qnaboard.getQ_level();
		
		int num=0;
		String sql="";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(q_no)from qnaboard");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1)+1;
			else 
				num=1;
			if(q_no!=0)
			{
				sql="update qnaboard set q_step=q_step+1 where ref=? and q_step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, q_step);
				pstmt.executeUpdate();
				
				q_step=q_step+1;
				q_level=q_level+1;
			}else{
				ref=num;
				q_step=0;
				q_level=0;
			}
			sql = "insert into qnaboard(q_no,q_mid,q_title,q_content,q_writer,q_phone,q_email,";
			sql+="ref,q_step,q_level) values(qna_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaboard.getQ_mid());
			pstmt.setString(2, qnaboard.getQ_title());
			pstmt.setString(3, qnaboard.getQ_content());
			pstmt.setString(4, qnaboard.getQ_writer());
			pstmt.setString(5, qnaboard.getQ_phone());
			pstmt.setString(6, qnaboard.getQ_email());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, q_step);
			pstmt.setInt(9, q_level);
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	public int getQnaboardCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from qnaboard");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	return x; //총게시글의 수 반환
	}
	
	public List getQnaboards(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List qnaboardList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select q_no,q_mid,q_title,q_content,q_writer,q_phone,q_email,ref,q_step,q_level,r "
					+
					"from (select q_no,q_mid,q_title,q_content,q_writer,q_phone,q_email,ref,q_step,q_level,rownum r " +
					"from (select q_no,q_mid,q_title,q_content,q_writer,q_phone,q_email,ref,q_step,q_level " +
					"from qnaboard order by ref desc, q_step asc)order by ref desc, q_step asc) where r >= ? and r <= ?");
					
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					
					rs = pstmt.executeQuery();
					if(rs.next()){
						qnaboardList = new ArrayList(end);
						
						do{
							QnaDataBean qnaboard = new QnaDataBean();
							qnaboard.setQ_no(rs.getInt("q_no"));
							qnaboard.setQ_mid(rs.getInt("q_mid"));
							qnaboard.setQ_title(rs.getString("q_title"));
							qnaboard.setQ_content(rs.getString("q_content"));
							qnaboard.setQ_writer(rs.getString("q_writer"));
							qnaboard.setQ_phone(rs.getString("q_phone"));
							qnaboard.setQ_email(rs.getString("q_email"));
							qnaboard.setRef(rs.getInt("ref"));
							qnaboard.setQ_step(rs.getInt("q_step"));
							qnaboard.setQ_level(rs.getInt("q_level"));
							
							qnaboardList.add(qnaboard);
						}while(rs.next());
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
		return qnaboardList;
	}
	public List getQnaboards(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List qnaboardList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from qnaboard where q_writer=?");
					
					pstmt.setString(1, id);
					
					rs = pstmt.executeQuery();
					if(rs.next()){
						qnaboardList = new ArrayList();
						
						do{
							QnaDataBean qnaboard = new QnaDataBean();
							qnaboard.setQ_no(rs.getInt("q_no"));
							qnaboard.setQ_mid(rs.getInt("q_mid"));
							qnaboard.setQ_title(rs.getString("q_title"));
							qnaboard.setQ_content(rs.getString("q_content"));
							qnaboard.setQ_writer(rs.getString("q_writer"));
							qnaboard.setQ_phone(rs.getString("q_phone"));
							qnaboard.setQ_email(rs.getString("q_email"));
							qnaboard.setRef(rs.getInt("ref"));
							qnaboard.setQ_step(rs.getInt("q_step"));
							qnaboard.setQ_level(rs.getInt("q_level"));
							
							qnaboardList.add(qnaboard);
						}while(rs.next());
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
		return qnaboardList;
	}
	public QnaDataBean getQnaboard(int q_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaDataBean qnaboard=null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from qnaboard where q_no = ?");
			pstmt.setInt(1, q_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				qnaboard = new QnaDataBean();
				qnaboard.setQ_no(rs.getInt("q_no"));
				qnaboard.setQ_mid(rs.getInt("q_mid"));
				qnaboard.setQ_title(rs.getString("q_title"));
				qnaboard.setQ_content(rs.getString("q_content"));
				qnaboard.setQ_writer(rs.getString("q_writer"));
				qnaboard.setQ_phone(rs.getString("q_phone"));
				qnaboard.setQ_email(rs.getString("q_email"));
				qnaboard.setRef(rs.getInt("ref"));
				qnaboard.setQ_step(rs.getInt("q_step"));
				qnaboard.setQ_level(rs.getInt("q_level"));
			}
				
				
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return qnaboard;
		
	}
	public QnaDataBean updateGetQnaboard(int q_no)throws Exception{
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		QnaDataBean qnaboard = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from qnaboard where q_no=?");
			pstmt.setInt(1, q_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				qnaboard=new QnaDataBean();
				qnaboard.setQ_no(rs.getInt("q_no"));
				qnaboard.setQ_mid(rs.getInt("q_mid"));
				qnaboard.setQ_title(rs.getString("q_title"));
				qnaboard.setQ_content(rs.getString("q_content"));
				qnaboard.setQ_writer(rs.getString("q_writer"));
				qnaboard.setQ_phone(rs.getString("q_phone"));
				qnaboard.setQ_email(rs.getString("q_email"));
				qnaboard.setRef(rs.getInt("ref"));
				qnaboard.setQ_step(rs.getInt("q_step"));
				qnaboard.setQ_level(rs.getInt("q_level"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return qnaboard;
	}
	public QnaDataBean updateQnaboard(QnaDataBean qnaboard)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
	
		try{
			conn=getConnection();
			
					sql="update qnaboard set q_title=?,q_content=?,q_phone=?";
					sql+=",q_email=? where q_no=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, qnaboard.getQ_title());
					pstmt.setString(2, qnaboard.getQ_content());
					pstmt.setString(3, qnaboard.getQ_phone());
					pstmt.setString(4, qnaboard.getQ_email());
					pstmt.setInt(5, qnaboard.getQ_no());
					pstmt.executeUpdate();
		
				
				
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return qnaboard;
	}
	public int deleteQnaboard(int q_no, String q_writer)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbq_writer="";
		int x=-1;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select q_writer from qnaboard where q_no=?");
			pstmt.setInt(1, q_no);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbq_writer=rs.getString("q_writer");
				if(dbq_writer.equals(q_writer)){
			
					pstmt = conn.prepareStatement(
							"delete from qnaboard where q_no=?");
					pstmt.setInt(1, q_no);
					pstmt.executeUpdate();
					x=1;
				}else
					x=0;
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
	
}