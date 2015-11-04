package remark;

import java.sql.Connection;
import java.util.List;
public interface RemarkInterface {
	
	Connection getConnection() throws Exception; 
	
	void insertRemark(RemarkDTO remark) throws Exception;
	
	void deleteRemark(int r_no) throws Exception;
	
	void updateR_pno(int r_no, int updateR_pno) throws Exception;
	void updateR_title(int r_no, String updateR_title) throws Exception;
	void updateR_content(int r_no, String updateR_content) throws Exception;

	RemarkDTO selectRemark(int r_no) throws Exception;
	
	List<RemarkDTO> selectRemarkAll(int r_pno) throws Exception;
}
