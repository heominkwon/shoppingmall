package product;

import java.sql.Connection;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	void deleteProduct(ProductDTO product) throws Exception;
	
	void updateProduct(ProductDTO product) throws Exception;

	ProductDTO selectProduct(ProductDTO product) throws Exception;
	
	void updateCNO_NO(String NEED_productNO,int change_categoryNO) throws Exception;
	
	void updateName_NAME(String NEED_productName, String change_productName) throws Exception;
	
	void updatePrice_NO(String NEED_productName, int change_price) throws Exception;
	
	void updateCount_NO(String NEED_productName, int change_count) throws Exception;
}
