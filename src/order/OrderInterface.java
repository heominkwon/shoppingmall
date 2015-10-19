package order;

import java.sql.Connection;
import java.util.List;

public interface OrderInterface {
	
	Connection getConnection();
	
	void insertOrder(OrderDTO order);
	
	void deleteOrder(OrderDTO order);
	
	void updateOrder(OrderDTO order);
	
	OrderDTO selectOrder(OrderDTO order);
	
	List<OrderDTO> selectsOrder();
	
	void delete_NO(int NEED_orderNO);
	
	void delete_MNO(int NEED_memberNO);
	
	void updateNO_NO(int NEED_orderNO, int change_orderNO);
	
	void updateNO_MNO(int NEED_memberNO, int change_orderNO);
	
	void updateMNO_NO(int NEED_orderNO, int change_memberNO);
	
	void updateMNO_MNO(int NEED_memberNO, int change_memberNO);
	
	void updatePAY_NO(int NEED_orderNO, int change_PAY);
	
	void updatePAY_MNO(int NEED_memberNO, int change_PAY);
	
	OrderDTO selectOrder(int NEED_orderNO);
	
	List<OrderDTO> selectsOrder_PAY(int NEED_PAY);
	
	List<OrderDTO> selectsOrder_MNO(int NEED_memberNO);
	
	int INTEGER_IsPaid(int NEED_orderNO);
	
	boolean BOOLEAN_IsPaid(int NEED_orderNO);
	

}
