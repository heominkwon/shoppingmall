package category;

import java.sql.Connection;

public class CategoryDAO implements CategoryInterface{

	@Override
	public Connection getConnection() throws Exception {
		return null;
	}

	@Override
	public void insertCategory(CategoryDTO category) throws Exception {
		
	}

	@Override
	public void deleteCategory(CategoryDTO category) throws Exception {
		
	}

	@Override
	public void updateCategory(CategoryDTO category) throws Exception {
		
	}

	@Override
	public CategoryDTO selectCategory(CategoryDTO category) throws Exception {
		return null;
	}

	@Override
	public void deleteCategory(int categoryNO) throws Exception {
		
	}

	@Override
	public void deleteCategory(String categoryNAme) throws Exception {
		
	}

	@Override
	public void updateCategory(int categoryNO, int change_categoryNO) throws Exception {
		
	}

	@Override
	public void updateCategory(int categoryNO, String change_categoryName) throws Exception {
		
	}

	@Override
	public void updateCategory(String categoryName, int change_categoryNO) throws Exception {
		
	}

	@Override
	public void updateCategory(String categoryName, String change_categoryName) throws Exception {
		
	}

	@Override
	public CategoryDTO selectCategory(int categoryNO) throws Exception {
		return null;
	}

	@Override
	public CategoryDTO selectCategory(String categoryName) throws Exception {
		return null;
	}

}
