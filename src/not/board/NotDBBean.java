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
			//board���̺� �Ű����� BoardDataBean��ü article�� ����ִ� ���� �̿��� ���ڵ带 �߰��Ѵ�
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
		public List getNotices(int start, int end) throws Exception {
			//�Ű������� ���� start�� ���� �ٺ��� end�� ���� �ٱ����� �Խñ۵��� ������ List�� ��ȯ���ִ� �޼ҵ��̴�.
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
						/* ref(�Խñ� �׷�)��ȣ�� ������������ re_step(�亯�� ���ļ���)�� ������������ �������ؼ� ��� �Խñ۵��� �������ϰ�
						 * rownum r�ν� �ٹ�ȣ�� �ű��� �̸޼ҵ忡�� �޾ƿ� �Ű������� start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ۵��� ��û�ϴ� ������ 
						 */
						pstmt.setInt(1, start); 
						pstmt.setInt(2, end); 

						rs = pstmt.executeQuery();
						if (rs.next()) {//start(�����ٹ�ȣ)�� end(���ٹ�ȣ)�� ���̿� �Խñ��� �����Ѵٸ�
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
							//articleList�� �Խñ۵��� list�� �����Ѵ�
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
			//�Ű������� ���� num�� ���� ��ȣ�� �ش��ϴ� �Խñ��� ��ȯ�ϴ� �޼ҵ�
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NotDataBean notice=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
				"update notice set n_readcount=n_readcount+1 where n_no = ?"); 
				//�Խñ��� ��ȯ(��ȸ)�Ҷ� ���� readcount(��ȸ��)�� 1�÷��ش�.
				pstmt.setInt(1, n_no);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(
				"select * from notice where n_no = ?");
				//�Ű������� ���� num�� ���� ��ȣ�� �ش��ϴ� �Խñ��� ��û�ϴ� ����
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
				}//BoardDataBean ��ü article�� �Ű����� num�� �ش��ȣ �Խù� ���� 
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return notice;//num�� �ش��ȣ �Խù� ��ȯ
		}
		public NotDataBean updateGetNotice(int n_no) throws Exception {
			//�Ű������� ���� num�� ���� ��ȣ�� �ش��ϴ� �Խñ��� ��ȯ�ϴ� �޼ҵ�
			//getArticle���� �������� �Խù��� ��ȯ�ص� ��ȸ���� �ö��� �ʴ´�.
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
