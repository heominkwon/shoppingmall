package product;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ProductDAO implements ProductInterface{
	
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance(){
		return instance;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public void InsertDTO(ProductDTO product) {
		
	}

	@Override
	public void DeleteDTO(ProductDTO product) {
		
	}

	@Override
	public void UPDATEDTO(ProductDTO product) {
		
	}

	@Override
	public void SelectDTO(ProductDTO product) {
		
	}


}
