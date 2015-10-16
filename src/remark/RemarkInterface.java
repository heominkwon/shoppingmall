package remark;

import java.sql.Connection;

public interface RemarkInterface {
	
	Connection getConnection() throws Exception; 
	
	void insertRemark(RemarkDTO remark) throws Exception;
	
	void deleteRemark(RemarkDTO remark) throws Exception;
	
	void updateRemark(RemarkDTO remark) throws Exception;
	
	RemarkDTO selectRemark(RemarkDTO remark) throws Exception;	
}
