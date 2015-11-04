package sort;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OrderProduct.OrderProductDAO;
import order.OrderDTO;


public class Sort {

	public static List sortByValue(final Map map){
        List<Integer> list = new ArrayList();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator(){
             
            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v1).compareTo(v2);
            }
             
        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }
	
	public static void makeZero(List<OrderDTO> fin,Map<Integer, Integer> chart,OrderProductDAO dbPro2) throws Exception{
		
		try{
		for(int i=0;i<fin.size();i++){
			int key=dbPro2.selectOrderProduct2(fin.get(i).getO_no()).getOP_pno();
			int add=chart.get(key);
			add--;
			chart.put(key, add);		
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {			
		}
	}
	
	public static void ADD(List<OrderDTO> fin,Map<Integer, Integer> chart,OrderProductDAO dbPro2) throws Exception{
		
		try{
			for(int i=0;i<fin.size();i++){
				int key=dbPro2.selectOrderProduct2(fin.get(i).getO_no()).getOP_pno();
				int add=chart.get(key);
				add++;
				chart.put(key, add);		
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {			
		}
	}
	
	
	
	
}


