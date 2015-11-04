package category;

import java.sql.Connection;
import java.util.List;

public interface CategoryInterface {

	Connection getConnection() throws Exception;
	
	int getcategoryCount() throws Exception;

	void insertCategory(CategoryDTO category) throws Exception;

	void deleteCategory(CategoryDTO category) throws Exception;
	
	void updateCategory(CategoryDTO category) throws Exception;

	CategoryDTO selectCategory(CategoryDTO category) throws Exception;
	
	int deleteCategory(int num, String passwd) throws Exception;
	
	void deleteCategory(int NEED_categoryNO) throws Exception;
	
	void deleteCategory(String NEED_categoryNAme) throws Exception;
	
	void updateCategory(int NEED_categoryNO, int change_categoryNO) throws Exception;
	
	void updateCategory(int NEED_categoryNO, String change_categoryName) throws Exception;
	
	void updateCategory(String NEED_categoryName, int change_categoryNO) throws Exception;
	
	void updateCategory(String NEED_categoryName, String change_categoryName) throws Exception;
	
	CategoryDTO selectCategory(int NEED_categoryNO) throws Exception;
	
	CategoryDTO selectCategory(String NEED_categoryName) throws Exception;
	
	List getCategorys(int start, int end) throws Exception;

}
