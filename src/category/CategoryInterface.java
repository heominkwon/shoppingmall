package category;

import java.sql.Connection;
import java.util.List;

public interface CategoryInterface {

	Connection getConnection() throws Exception;

	void insertCategory(CategoryDTO category) throws Exception;

	void deleteCategory(int c_no) throws Exception;
	void deleteCategory(String c_name) throws Exception;

	void updateC_name(int c_no, String updateC_name) throws Exception;
	void updateC_name(String c_name, String updateC_name) throws Exception;
	
	CategoryDTO selectCategory(int c_no) throws Exception;
	CategoryDTO selectCategory(String c_name) throws Exception;
	
	List<Integer> selectC_noAll() throws Exception;
	List<Integer> selectC_nameAll() throws Exception;
	
	List<CategoryDTO> selectCategoryAll() throws Exception;

}
