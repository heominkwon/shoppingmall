package product;

import java.sql.Connection;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	void deleteProduct(ProductDTO product) throws Exception;
	
	void updateProduct(ProductDTO product) throws Exception;

	ProductDTO selectProduct(ProductDTO product) throws Exception;
	
	void updateCategoryNO(String productName,int categoryNO) throws Exception;
	
	void updateName(String productName, String Name) throws Exception;
	
	void updatePrice(String productName, int price) throws Exception;
	
	void updateCount(String productName, int count) throws Exception;
}
