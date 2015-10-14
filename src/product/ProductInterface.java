package product;

import java.sql.Connection;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	void deleteProduct(ProductDTO product) throws Exception;

	void updateProduct(ProductDTO product) throws Exception;

	ProductDTO selectProduct(ProductDTO product) throws Exception;
	
	void deleteProduct(int p_no) throws Exception;
	
	void deleteProdut(String p_name) throws Exception;
	
	void updateCategoryNO(int categoryNO) throws Exception;
	
	void updateName(String Name) throws Exception;
	
	void updatePrice(int price) throws Exception;
	
	void updateCount(int count) throws Exception;
}
