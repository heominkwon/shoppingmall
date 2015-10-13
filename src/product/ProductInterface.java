package product;

import java.sql.Connection;

public interface ProductInterface {

	Connection getConnection() throws Exception;

	void InsertDTO(ProductDTO product);

	void DeleteDTO(ProductDTO product);

	void UPDATEDTO(ProductDTO product);

	void SelectDTO(ProductDTO product);

}
