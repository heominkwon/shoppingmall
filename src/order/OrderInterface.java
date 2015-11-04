package order;

import java.sql.Connection;
import java.util.List;

public interface OrderInterface {
	
	int getMemOrderCount(int O_MNO) throws Exception;
	
	int getNewOrderCount() throws Exception;
	
	int getComOrderCount() throws Exception;
	
	int getOrderCount() throws Exception;
	
	Connection getConnection() throws Exception;
	
	void insertOrder(OrderDTO order) throws Exception;
	
	void deleteOrder(OrderDTO order) throws Exception;
	
	void updateOrder(OrderDTO order) throws Exception;
	
	OrderDTO selectOrder(OrderDTO order) throws Exception;
	
	List<OrderDTO> selectsOrder() throws Exception;
	
	List<OrderDTO> selectsMemOrder(int start, int end, int O_MNO) throws Exception;
	
	List<OrderDTO> selectsNewOrder(int start, int end) throws Exception;
	
	List<OrderDTO> selectsComOrder(int start, int end) throws Exception;
	
	List<OrderDTO> selectsFinOrder()throws Exception;
	
	List<OrderDTO> selectsAgeFinOrder(int age, int year)throws Exception;
	
	void delete_NO(int NEED_orderNO) throws Exception;
	
	void delete_MNO(int NEED_memberNO) throws Exception;
	
	void updateNO_NO(int NEED_orderNO, int change_orderNO) throws Exception;
	
	void updateNO_MNO(int NEED_memberNO, int change_orderNO) throws Exception;
	
	void updateMNO_NO(int NEED_orderNO, int change_memberNO) throws Exception;
	
	void updateMNO_MNO(int NEED_memberNO, int change_memberNO) throws Exception;
	
	void updatePAY_NO(int NEED_orderNO, int change_PAY) throws Exception;
	
	void updatePAY_MNO(int NEED_memberNO, int change_PAY) throws Exception;
	
	void updateO_address(int NEED_orderNO, String change_address) throws Exception;
	
	OrderDTO selectOrder(int NEED_orderNO) throws Exception;
	
	List<OrderDTO> selectsOrders_PAY(int NEED_PAY) throws Exception;
	
	List<OrderDTO> selectsOrders_MNO(int NEED_memberNO) throws Exception;
	
	int IsPaid(int NEED_orderNO) throws Exception;
	
	List<Integer> selectsOrder_NO (int NEED_memberNO) throws Exception;
	
	List<Integer> selectsOrder_NO() throws Exception;
}
