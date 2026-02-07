package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

public class StepDefinition extends Utils {
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	
	@Given("I issue an API request {string} with {string} HTTP request")
	public void i_issue_an_API_request_with_HTTP_request(String resource, String method) throws IOException 
	{
    // Write code here that turns the phrase above into concrete actions
		reqSpec=given().log().all().spec(requestSpecification());
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("Resource API: "+ resourceAPI.getResource());
		
		if(method.equalsIgnoreCase("GET")) 
		{
			response = reqSpec.when().get(resourceAPI.getResource());
		}
		
		else if(method.equalsIgnoreCase("PUT")) {
			response = reqSpec.when().put(resourceAPI.getResource());
		}
	}
	
	@Given("the user with id {int} is authenticated")
	public void the_user_with_id_is_authenticated(Integer userid) 
	{
		userid = 1;
	}
	
	@Given("the following items are added to the order")
	public void the_following_items_are_added_to_the_order(DataTable dataTable) 
	{
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

	    List<Map<String, Integer>> items = new ArrayList<>();

	    for(Map<String, String> row : rows) 
	    {
	        Map<String, Integer> item = new HashMap<>();
	        item.put("product_id", Integer.parseInt(row.get("product_id")));
	        item.put("quantity", Integer.parseInt(row.get("quantity")));
	        //System.out.println("item: "+ item);
	        items.add(item);
	    }
	    
	    //System.out.println("items: "+ items);

	    Map<String, Object> order = new HashMap<>();
	    order.put("user_id", 1);
	    order.put("items", items);

	    System.out.println("order: "+ order);
	}

	@Then("the HTTP response will return status {int}")
	public void the_http_response_will_return_status(Integer status) 
	{
    // Write code here that turns the phrase above into concrete actions
		resSpec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		assertEquals(response.getStatusCode(),200);
	}

	@Then("We validate API response data against a specification")
	public void we_validate_api_response_data_against_a_specification() 
	{
    // Write code here that turns the phrase above into concrete actions
		System.out.println("Response Status Line: "+ response.getStatusLine());
		assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		
		//extracting OK from above Status Line:
		String statusLine = response.getStatusLine();
		String statusText = statusLine.substring(statusLine.lastIndexOf(" ") + 1);
		System.out.println("Response Status Text: "+ statusText);
		assertEquals(statusText, "OK");
	}
	
	@Then("We validate API response schema against a specification") //not working
	public void we_validate_api_response_schema_against_a_specification() {
		
		reqSpec.when().get().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(System.getProperty("user.dir")+"//src//test//java//resources//PutApiResponseSchema.json"));
	}

}
