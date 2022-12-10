package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_update extends JsonPlaceHolderBaseUrl {
    /*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }
    When
           Url'e POST Request gonder
    Then
        Status code is 201
    And
        response body is like {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
         }
 */
    @Test

    public void post02(){
        //1. adım url set et
        spec.pathParam("first","todos");

        //2.adım expected date set et

        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap = expectedData.expectedDataSetUp();




        //3. adim request gonder response al
        Response response = given().
                spec(spec).auth().basic("admin","1234").
                contentType(ContentType.JSON).
                body(expectedDataMap).when().
                post("/{first}");
        response.prettyPrint();

        //4. adım assertion yap
        Map<String,Object> actualDate = response.as(HashMap.class); // DE- SERİALİZATİON
        System.out.println(actualDate);

        response.then().statusCode(201);

// assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDate.get("userId"));
        assertEquals(expectedDataMap.get("completed"), actualDate.get("completed"));
        assertEquals(expectedDataMap.get("title"), actualDate.get("title"));















    }
}
