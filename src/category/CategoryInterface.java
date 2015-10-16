package category;

import java.sql.Connection;

public interface CategoryInterface {

	Connection getConnection() throws Exception;

	void insertCategory(CategoryDTO category) throws Exception;

	void deleteCategory(CategoryDTO category) throws Exception;
	
	void updateCategory(CategoryDTO category) throws Exception;

	CategoryDTO selectCategory(CategoryDTO category) throws Exception;
	
	void deleteCategory(int categoryNO) throws Exception;
	
	void deleteCategory(String categoryNAme) throws Exception;
	
	void updateCategory(int categoryNO, int change_categoryNO) throws Exception;
	
	void updateCategory(int categoryNO, String change_categoryName) throws Exception;
	
	void updateCategory(String categoryName, int change_categoryNO) throws Exception;
	
	void updateCategory(String categoryName, String change_categoryName) throws Exception;
	
	CategoryDTO selectCategory(int categoryNO) throws Exception;
	
	CategoryDTO selectCategory(String categoryName) throws Exception;

}
