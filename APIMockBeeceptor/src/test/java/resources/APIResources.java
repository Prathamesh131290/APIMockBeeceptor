package resources;

public enum APIResources {
	
	GetUsersAPI("/api/users"),
	GetProductsAPI("/api/products"),
	GetCartsAPI("/api/carts"),
	GetOrdersAPI("/api/orders"),
	PutOrdersAPI("/api/orders");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
