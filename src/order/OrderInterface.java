package order;

import java.sql.Connection;
import java.util.List;

public interface OrderInterface {
	
	Connection getConnection() throws Exception;
	
	void insertOrder(OrderDTO order) throws Exception;
	
	void deleteOrder(int o_mno) throws Exception;
	
	void updateO_mno(int o_no, int updateO_mno) throws Exception;
	void updateO_pay(int o_no, int updateO_pay) throws Exception;
	
	OrderDTO selectOrder(int o_no) throws Exception;
	
	List<OrderDTO> selectOrderAll() throws Exception;
	List<OrderDTO> selectOrderAll(int o_mno) throws Exception;

	List<Integer> selectO_noAll() throws Exception;
	List<Integer> selectO_noAll (int o_mno) throws Exception;
	
	int IsPaid(int o_no) throws Exception;
}
