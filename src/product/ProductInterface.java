package product;

import java.sql.Connection;
import java.util.List;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void insertProduct(ProductDTO product) throws Exception;

	int deleteProduct(int num, String passwd) throws Exception;
	
	void updateProduct(ProductDTO product) throws Exception;

	ProductDTO selectProduct(ProductDTO product) throws Exception;
	
	void updateCategoryNO(String productName,int categoryNO) throws Exception;
	
	void updateName(String productName, String Name) throws Exception;
	
	void updatePrice(String productName, int price) throws Exception;
	
	void updateCount(String productName, int count) throws Exception;
	
	void updateDesc(String productName, String desc) throws Exception;
	
	void updatePath(String productName, String path) throws Exception;
	
	String getProductName(int p_no) throws Exception;
	
	int getProductCount() throws Exception;
	
	List getProducts(int start, int end) throws Exception;
	
	ProductDTO getProduct(int num) throws Exception;
	
}
