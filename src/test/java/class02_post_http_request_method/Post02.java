package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
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
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);


        //3. adom request gonder response al
        Response response = given().
                            spec(spec).
                            contentType(ContentType.JSON).
                            body(expectedData).when().
                            post("/{first}");
        response.prettyPrint();

        //4. adım assertion yap
       Map<String,Object> actualDate = response.as(HashMap.class); // DE- SERİALİZATİON
       System.out.println(actualDate);

       response.then().statusCode(201);

// assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
assertEquals(expectedData.get("userId"), actualDate.get("userId"));
assertEquals(expectedData.get("completed"), actualDate.get("completed"));
assertEquals(expectedData.get("title"), actualDate.get("title"));















    }
}
