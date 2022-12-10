package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class PostVePojo01 extends JsonPlaceHolderBaseUrl {
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
         response body is like
         {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false,
              "id": 201
         }
 */
@Test
    public void postVePojo01(){
    //1. adım url set et
    spec.pathParam("first","todos");

    //2.adım request body set et
    JsonPlaceHolderPojo requestBodyPojo = new JsonPlaceHolderPojo(55,"Tidy your room",false);

    //3. adım
    Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyPojo).post("/{first}");
response.prettyPrint();
    //4.adım
    //1.yol
    response.then().assertThat().statusCode(201).body("userId" , equalTo(requestBodyPojo.getUserId()),
            "title",equalTo(requestBodyPojo.getTitle()),
            "completed",equalTo(requestBodyPojo.getCompleted()));

    //2.yol : de-serialization
   JsonPlaceHolderPojo actualDate = response.as(JsonPlaceHolderPojo.class);
   assertEquals(requestBodyPojo.getUserId(),actualDate.getUserId());
   assertEquals(requestBodyPojo.getTitle(),actualDate.getTitle());
   assertEquals(requestBodyPojo.getCompleted(),actualDate.getCompleted());

























}
}
