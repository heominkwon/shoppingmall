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



public class NotDBBean {
private static NotDBBean instance = new NotDBBean();
	
	public static NotDBBean getInstance(){
		return instance;
	}
	private NotDBBean(){}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	public void insertNotice(NotDataBean notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int n_no=notice.getN_no();
		int ref=notice.getRef();
		int n_step=notice.getN_step();
		int n_level=notice.getN_level();
		
		int num=0;
		String sql="";
		try{
			conn=getConnection();
			pstmt = conn.prepareStatement("select max(n_no) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1)+1;
			else
				num=1;
			if(num!=0)
			{
				sql="update notice set n_step=n_step+1 where ref=? and n_step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, n_step);
				pstmt.executeUpdate();
				
				n_step=n_step+1;
				n_level=n_level+1;
			}else{
				ref=num;
				n_step=0;
				n_level=0;
			}
			sql = "insert into notice(n_no,n_title,n_content,n_writer,n_regdate,n_readcount,";
			sql+="ref,n_step,n_level) values(notice_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getN_title());
			pstmt.setString(2, notice.getN_content());
			pstmt.setString(3, notice.getN_writer());
			pstmt.setTimestamp(4, notice.getN_regdate());
			pstmt.setInt(5, notice.getN_readcount());
			
			pstmt.setInt(6, ref);
			pstmt.setInt(7, n_step);
			pstmt.setInt(8, n_level);
			pstmt.executeUpdate();
			//board테이블에 매개변수 BoardDataBean객체 article에 들어있는 값을 이용해 레코드를 추가한다
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
		public int getNoticeCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from notice");
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
		public List getNotices(int start, int end) throws Exception {
			//매개변수로 받은 start의 값의 줄부터 end의 값의 줄까지의 게시글들의 묶음을 List로 반환해주는 메소드이다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List noticeList=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select n_no,n_title,n_content,n_regdate,n_writer,ref,n_step,n_level,n_readcount,r "
						+
						"from (select n_no,n_title,n_content,n_regdate,n_writer,ref,n_step,n_level,n_readcount,rownum r " +
						"from (select n_no,n_title,n_content,n_regdate,n_writer,ref,n_step,n_level,n_readcount " +
						"from notice order by ref desc, n_step asc) order by ref desc, n_step asc ) where r >= ? and r <= ? ");
						/* ref(게시글 그룹)번호는 내림차순으로 re_step(답변글 정렬순서)는 오름차순으로 정렬을해서 모든 게시글들을 정렬을하고
						 * rownum r로써 줄번호를 매긴후 이메소드에서 받아온 매개변수값 start(시작줄번호)와 end(끝줄번호)값 사이에 게시글들을 요청하는 쿼리문 
						 */
						pstmt.setInt(1, start); 
						pstmt.setInt(2, end); 

						rs = pstmt.executeQuery();
						if (rs.next()) {//start(시작줄번호)와 end(끝줄번호)값 사이에 게시글이 존재한다면
							noticeList = new ArrayList(end); 
							do{ 
								NotDataBean notice= new NotDataBean();
								notice.setN_no(rs.getInt("n_no"));
								notice.setN_title(rs.getString("n_title"));
								notice.setN_content(rs.getString("n_content"));
								notice.setN_writer(rs.getString("n_writer"));
								notice.setN_regdate(rs.getTimestamp("n_regdate"));
								notice.setN_readcount(rs.getInt("n_readcount"));
								notice.setRef(rs.getInt("ref"));
								notice.setN_step(rs.getInt("n_step"));
								notice.setN_level(rs.getInt("n_level"));
								noticeList.add(notice);
							}while(rs.next());
							//articleList에 게시글들을 list로 저장한다
						}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}

			
			return noticeList;
		}
		public List getNotices(String id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List noticeList=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from notice where n_writer=?order by n_regdate");
						pstmt.setString(1, id); 

						rs = pstmt.executeQuery();
						if (rs.next()) {
							noticeList = new ArrayList(); 
							do{ 
								NotDataBean notice= new NotDataBean();
								notice.setN_no(rs.getInt("n_no"));
								notice.setN_title(rs.getString("n_title"));
								notice.setN_content(rs.getString("n_content"));
								notice.setN_regdate(rs.getTimestamp("n_regdate"));
								notice.setN_writer(rs.getString("n_writer"));
								notice.setN_readcount(rs.getInt("n_readcount"));
								notice.setRef(rs.getInt("ref"));
								notice.setN_step(rs.getInt("n_step"));
								notice.setN_level(rs.getInt("n_level"));
							}while(rs.next());
						}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}

			
			return noticeList;
		}
		public NotDataBean getNotice(int n_no) throws Exception {
			//매개변수로 받은 num의 값의 번호에 해당하는 게시글을 반환하는 메소드
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NotDataBean notice=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
				"update notice set n_readcount=n_readcount+1 where n_no = ?"); 
				//게시글을 반환(조회)할때 마다 readcount(조회수)를 1늘려준다.
				pstmt.setInt(1, n_no);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(
				"select * from notice where n_no = ?");
				//매개변수로 받은 num의 값의 번호에 해당하는 게시글을 요청하는 쿼리
				pstmt.setInt(1, n_no);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					notice = new NotDataBean();
					notice.setN_no(rs.getInt("n_no"));
					notice.setN_title(rs.getString("n_title"));
					notice.setN_content(rs.getString("n_content"));
					notice.setN_regdate(rs.getTimestamp("n_regdate"));
					notice.setN_writer(rs.getString("n_writer"));
					notice.setN_readcount(rs.getInt("n_readcount"));
					notice.setRef(rs.getInt("ref"));
					notice.setN_step(rs.getInt("n_step"));
					notice.setN_level(rs.getInt("n_level"));
				}//BoardDataBean 객체 article에 매개변수 num에 해당번호 게시물 저장 
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return notice;//num에 해당번호 게시물 반환
		}
		public NotDataBean updateGetNotice(int n_no) throws Exception {
			//매개변수로 받은 num의 값의 번호에 해당하는 게시글을 반환하는 메소드
			//getArticle과의 차이점은 게시물을 반환해도 조회수가 올라가지 않는다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NotDataBean notice=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
				"select * from notice where n_no = ?");			
				pstmt.setInt(1, n_no);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					notice = new NotDataBean();
					notice.setN_no(rs.getInt("n_no"));
					notice.setN_title(rs.getString("n_title"));
					notice.setN_content(rs.getString("n_content"));
					notice.setN_regdate(rs.getTimestamp("n_regdate"));
					notice.setN_readcount(rs.getInt("n_readcount"));
					notice.setN_writer(rs.getString("n_writer"));
					notice.setRef(rs.getInt("ref"));
					notice.setN_step(rs.getInt("n_step"));
					notice.setN_level(rs.getInt("n_level"));
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}

			return notice;
		}
		public NotDataBean updateNotice(NotDataBean notice)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			
		
			try{
				conn=getConnection();
				
						sql="update notice set n_title=?,n_content=?";
						sql+=",n_writer=? where n_no=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, notice.getN_title());
						pstmt.setString(2, notice.getN_content());
						pstmt.setString(3, notice.getN_writer());
						pstmt.setInt(4, notice.getN_no());
						pstmt.executeUpdate();
			
					
					
				
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return notice;
		}
		public int deleteNotice(int n_no, String n_writer)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String dbn_writer="";
			int x=-1;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select n_writer from notice where n_no=?");
				pstmt.setInt(1, n_no);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					dbn_writer=rs.getString("n_writer");
					if(dbn_writer.equals(n_writer)){
				
						pstmt = conn.prepareStatement(
								"delete from notice where n_no=?");
						pstmt.setInt(1, n_no);
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
