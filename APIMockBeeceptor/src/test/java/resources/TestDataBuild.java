package resources;

import java.util.List;

import pojo.Items;
import pojo.UpdateOrder;

public class TestDataBuild {
	
	public UpdateOrder updateOrderPayload(int user_id, List<Items> items)
	{
		UpdateOrder p = new UpdateOrder();
		
		p.setUser_id(user_id);
		
		Items i = new Items();
		i.setProduct_id(1);
		i.setQuantity(2);
		p.setItems(items);
		
		return p;
		
	}

}
