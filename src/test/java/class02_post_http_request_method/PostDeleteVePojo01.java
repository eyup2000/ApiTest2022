package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class PostDeleteVePojo01 extends JsonPlaceHolderBaseUrl {
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
        {}

     */
    @Test
    public void postDeleteVePojo01(){
        //1.adım url set et
        spec.pathParam("first","todos");
        //2.adım request body veya ecpected data set et
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        //3.adım request gonder response al
       Response response = given().spec(spec).contentType(ContentType.JSON).when().body(requestBody).post("/{first}");
        response.prettyPrint();

        //delete request / işlemi
       JsonPath json = response.jsonPath();
       Integer id = json.getInt("id");

       spec.pathParams("first","todos","second",id);

      Response response1 = given().spec(spec).when().delete("/{first}/{second}");
      response1.prettyPrint();

    Map<String,Object> actualData = response1.as(HashMap.class);
        System.out.println(actualData);
    assertTrue(actualData.size()==0);













    }
}
