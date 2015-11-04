package OrderProduct;

import java.sql.Connection;
import java.util.List;

public interface OrderProductInterface {

	Connection getConnection() throws Exception;
	
	int getOrderProductCount(int num) throws Exception;
	
	void insertOrderProduct(OrderProductDTO orderproduct) throws Exception;
	
	void deleteOrderProduct(OrderProductDTO orderproduct) throws Exception;
	
	void updateOrderProduct(OrderProductDTO orderproduct) throws Exception;
	
	OrderProductDTO selectOrderProduct(OrderProductDTO orderproduct) throws Exception;
	
	List<OrderProductDTO> selectsOrderProduct() throws Exception;
	
	List<OrderProductDTO> selectsOrderProducts(int num) throws Exception;
	
	
	
	void delete_nNO(int need_orderproductNO) throws Exception;
	
	
	
	void updateNO_nNO(int change_orderproductNO, int need_orderproductNO) throws Exception;
	
	void updateNO_nONO(int change_orderproductNO, int need_ONO) throws Exception;
	
	void updateONO_nNO(int change_ONO, int need_orderproductNO) throws Exception;
	
	void updatePNO_nNO(int change_productNO, int need_orderproductNO) throws Exception;
	
	void updateCOUNT_nNO(int change_COUNT, int need_orderproductNO) throws Exception;
	
	void updatePRICE_nNO(int change_PRICE, int need_orderproductNO) throws Exception;
	
	
	
	OrderProductDTO selectOrderProduct(int need_orderproductNO) throws Exception;
	
	
	OrderProductDTO selectOrderProduct2(int need_orderproductNO) throws Exception;
	
	List<OrderProductDTO> selectsOrderProduct(int need_ONO) throws Exception;
}
