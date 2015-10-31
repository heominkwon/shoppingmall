package product;

import java.sql.Connection;
import java.util.List;
import java.sql.Timestamp;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	void deleteProduct(int p_no) throws Exception;
	void deleteProduct(String p_name) throws Exception;

	void updateP_cno(int p_no,int updateP_cno) throws Exception;
	void updateP_cno(String p_name,int updateP_cno) throws Exception;
	
	void updateP_name(int p_no, String updateP_name) throws Exception;
	void updateP_name(String p_name, String updateP_name) throws Exception;
	
	void updateP_price(int p_no, int updateP_price) throws Exception;
	void updateP_price(String p_name, int updateP_price) throws Exception;
	
	void updateP_count(int p_no, int updateP_count) throws Exception;
	void updateP_count(String p_name, int updateP_count) throws Exception;
	
	void updateP_desc(int p_no, String updateP_desc) throws Exception;
	void updateP_desc(String p_name, String updateP_desc) throws Exception;
	
	void updateP_path(int p_no, String updateP_path) throws Exception;
	void updateP_path(String p_name, String updateP_path) throws Exception;
	
	ProductDTO selectProduct(int p_no) throws Exception;
	ProductDTO selectProduct(String p_name) throws Exception;
	
	int selectP_no(String p_name) throws Exception;
	
	String selectP_name(int p_no) throws Exception;
	
	int selectP_price(int p_no) throws Exception;
	int selectP_price(String p_name) throws Exception;
	
	int selectP_count(int p_no) throws Exception;
	int selectP_count(String p_name) throws Exception;
	
	String selectP_desc(int p_no) throws Exception;
	String selectP_desc(String p_name) throws Exception;
	
	String selectP_path(int p_no) throws Exception;
	String selectP_path(String p_name) throws Exception;
	
	Timestamp selectP_regdate(int p_no) throws Exception;
	Timestamp selectP_regdate(String p_name) throws Exception;
	
	
	List<Integer> selectP_noAll() throws Exception;
	List<String>  selectP_nameAll() throws Exception;

	List<ProductDTO> selectAll() throws Exception;
	List<ProductDTO> selectAll(int p_cno) throws Exception;
	
	int getProductCount() throws Exception;
	List getProducts(int start, int end) throws Exception;
	
	int getProductCountByCategory(int c_no) throws Exception;
	List getProductsByCategory(int c_no, int start, int end) throws Exception;
}
