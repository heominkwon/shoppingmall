package OrderProduct;

import java.sql.Connection;
import java.util.List;

public interface OrderProductInterface {

	Connection getConnection() throws Exception;
	
	void insertOrderProduct(OrderProductDTO orderproduct) throws Exception;
	
	void deleteOrderProduct(int op_no) throws Exception;
	
	void updateOP_ono(int op_no, int updateOP_ono) throws Exception;
	void updateOP_pno(int op_no, int updateOP_pno) throws Exception;
	void updateOP_count(int op_no, int updateOP_count) throws Exception;
	void updateOP_price(int op_no, int updateOP_price) throws Exception;	
	
	OrderProductDTO selectOrderProduct(int op_no) throws Exception;
	
	List<OrderProductDTO> selectOrderProductAll() throws Exception;
	List<OrderProductDTO> selectOrderProductAll(int op_ono) throws Exception;
}
