package DemoProject;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@Test
public class GET_Native_Name {
	
	
	public void getCountryName_InputValidFullNativeName_ReturnSuccessResponse() {
	
				SoftAssert sf = new SoftAssert();
								
				//Specify base URI		
				RestAssured.baseURI="https://restcountries.eu/rest/v2/name";
				
				//Create a Request Object
				RequestSpecification httprequest = RestAssured.given();
				
				//Response Object
				Response response = httprequest.request(Method.GET,"eesti");
				JsonPath jsonpath = response.jsonPath();
				
				//Print the response in the console	
				String responseString = response.getBody().asString();
				System.out.println("Response Body is "+ responseString);
				
				//Response Validations
				
				//Status code Validation
				int actualStatusCode = response.getStatusCode();	
				System.out.println("Status code is :"+ 200);
				sf.assertEquals(actualStatusCode, 200);
				
				//content Type valdiation	
				String responsecontentType = response.getContentType();
				System.out.println("Content Type is :"+ responsecontentType);
				sf.assertEquals(responsecontentType, "application/json;charset=utf-8");
					
				//Validate the Country Name 
				List<String> actualCountryName = jsonpath.get("name");
				System.out.println("The country Name  is :"+ actualCountryName);
				sf.assertEquals(actualCountryName.get(0),"Estonia");
				
				//Validate the Native Name
				List<String> actualNativeName = jsonpath.get("nativeName");
				System.out.println("The Native  Name  is :"+ actualNativeName);
				sf.assertEquals(actualNativeName.get(0),"Eesti");
				
				//Validate the capital
				List<String> actualCapital = jsonpath.get("capital");
				System.out.println("The Native  Name  is :"+ actualCapital);
				sf.assertEquals(actualCapital.get(0),"Tallinn");	
				
				sf.assertAll();
	
             }
	

}
