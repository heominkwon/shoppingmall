package category;

import java.sql.Connection;
import java.util.List;

public interface CategoryInterface {

	Connection getConnection() throws Exception;

	void insertCategory(CategoryDTO category) throws Exception;
	void deleteCategory(CategoryDTO category) throws Exception;
	void updateCategory(CategoryDTO category) throws Exception;

	CategoryDTO selectCategory(CategoryDTO category) throws Exception;
	
	void deleteCategory(int c_no) throws Exception;
	void deleteCategory(String c_name) throws Exception;
	
	void updateCategory(int c_no, int updateC_no) throws Exception;
	void updateCategory(int c_no, String updateC_name) throws Exception;
	void updateCategory(String c_name, int updateC_no) throws Exception;
	void updateCategory(String c_name, String updateC_name) throws Exception;
	
	CategoryDTO selectCategory(int c_no) throws Exception;
	CategoryDTO selectCategory(String c_name) throws Exception;
	
	List<Integer> selectAllC_no() throws Exception;

}
