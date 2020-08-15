package DemoProject;

import java.util.ArrayList;
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
public class GET_Partial_Country_Name {
	
	//We are giving the Full and Valid country Name and validate the response for the capital city of that record.
	
	public void getCountryName_InputValidPartialCountryName_ReturnSuccessResponse() {
		
				//Create a SoftAssert Object Reference to SoftAssert the Asserts for validation
					
				SoftAssert sf = new SoftAssert();
								
				//Specify base URI		
				RestAssured.baseURI="https://restcountries.eu/rest/v2/name";
				
				//Create a Request Object
				RequestSpecification httprequest = RestAssured.given();
				
				//Response Object and also define jsonpath
				Response response = httprequest.request(Method.GET,"United");
				JsonPath jsonpath = response.jsonPath();
				
				
				
				//Response Validations
				
				//Status code Validation
				int actualStatusCode = response.getStatusCode();	
				System.out.println("Status code is :"+ 200);
				sf.assertEquals(actualStatusCode, 200);
				
				//content Type valdiation	
				String responsecontentType = response.getContentType();
				System.out.println("Content Type is :"+ responsecontentType);
				sf.assertEquals(responsecontentType, "application/json;charset=utf-8");
					
				//Get the list of all the expected country capital values 
				List<String> expectedCountryNames = new ArrayList<String>();
				expectedCountryNames.add("");
				expectedCountryNames.add("Dodoma");
				expectedCountryNames.add("Abu Dhabi");
				expectedCountryNames.add("London");
				expectedCountryNames.add("Washington, D.C.");
				expectedCountryNames.add("Mexico City");
				
				System.out.println("The expected country List is"+ expectedCountryNames);
				List<String> actualCountryNames = jsonpath.get("capital");
				System.out.println("The actual country List is"+ actualCountryNames);
			    sf.assertTrue(actualCountryNames.equals(expectedCountryNames));
			    
			    sf.assertAll();
			    
	   }
									
            		
}
