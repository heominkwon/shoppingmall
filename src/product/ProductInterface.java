package product;

import java.sql.Connection;
import java.util.List;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	void deleteProduct(ProductDTO product) throws Exception;
	
	void updateProduct(ProductDTO product) throws Exception;

	ProductDTO selectProduct(ProductDTO product) throws Exception;
	
	
	
	void deleteProduct(int NEED_productNO) throws Exception;
	
	void deleteProduct(String NEED_productNAME) throws Exception;
	
	
	
	void updateNO_NO(int NEED_productNO,int change_productNO) throws Exception;
	
	void updateNO_CNO(int NEED_categoryNO,int change_productNO) throws Exception;
	
	void updateNO_NAME(String NEED_productNAME,int change_productNO) throws Exception;
	
	
	
	void updateCNO_NO(int NEED_productNO,int change_categoryNO) throws Exception;
	
	void updateCNO_CNO(int NEED_categoryNO,int change_categoryNO) throws Exception;
	
	void updateCNO_NAME(String NEED_productNAME,int change_categoryNO) throws Exception;
	
	
	
	void updateName_NO(int NEED_productNO, String change_productName) throws Exception;
	
	void updateName_CNO(int NEED_categoryNO, String change_productName) throws Exception;
	
	void updateName_NAME(String NEED_productName, String change_productName) throws Exception;
	
	
	
	void updatePrice_NO(int NEED_productNO, int change_price) throws Exception;
	
	void updatePrice_NAME(String NEED_productName, int change_price) throws Exception;
	
	
	
	void updateCount_NO(int NEED_productNO, int change_count) throws Exception;
	
	void updateCount_NAME(String NEED_productNAME, int change_count) throws Exception;
	
	
	
	void updateDesc_NO(int NEED_productNO, String change_desc) throws Exception;
	
	void updateDesc_NAME(String NEED_productNAME, String change_desc) throws Exception;
	
	
	void updatePath_NO(int NEED_productNO, String change_path) throws Exception;
	
	void updatePath_NAME(String NEED_productNAME, String change_path) throws Exception;
	
	
	
	ProductDTO selectProduct(int NEED_productNO) throws Exception;
	
	ProductDTO selectProduct(String NEED_productNAME) throws Exception;
	
	
	
	List<ProductDTO> selectsProduct_CNO(int NEED_categoryNO) throws Exception;
}
