package remark;

import java.sql.Connection;
import java.util.List;
public interface RemarkInterface {
	
	Connection getConnection() throws Exception; 
	
	void insertRemark(RemarkDTO remark) throws Exception;
	
	void deleteRemark(RemarkDTO remark) throws Exception;
	
	void updateRemark(RemarkDTO remark) throws Exception;
	
	RemarkDTO selectRemark(RemarkDTO remark) throws Exception;
	
	
	
	void delete_nNO(int NEED_remarkNO) throws Exception;
	
	void delete_nPNO(int NEED_productNO) throws Exception;
	
	void delete_nWRITER(String NEED_WRITER) throws Exception;
	
	
	
	void updateNO_nNO(int change_remarkNO, int need_remarkNO) throws Exception;
	
	void updatePNO_nNO(int change_remarkPNO, int need_remarkNO) throws Exception;
	
	void updateTITLE_nNO(String change_remarkTITLE, int need_remarkNO) throws Exception;
	
	void updateCONTENT_nNO(String change_remarkCONTENT, int need_remarkNO) throws Exception;
	
	void updateWRITER_nNO(String change_remarkWRITER, int need_remarkNO) throws Exception;
	
	
	
	RemarkDTO selectRemark(int need_remarkNO) throws Exception;
	
	
	
	List<RemarkDTO> selectsRemark_nPNO(int need_remarkPNO) throws Exception;
	
	List<RemarkDTO> selectsRemark_nTITLE(String need_remarkTITLE) throws Exception;
	
	List<RemarkDTO> selectsRemark_nCONTENT(String need_remarkCONTENT) throws Exception;
	
	List<RemarkDTO> selectsRemark_nWRITER(String need_remarkWRITER) throws Exception;
}
