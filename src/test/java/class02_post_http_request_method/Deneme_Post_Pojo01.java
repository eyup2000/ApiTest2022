package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Deneme_Post_Pojo01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos


        "userId": 7,
        "id": 124,
        "title": "qui consectetur id",
        "completed": false
        */

    @Test
    public void Deneme(){
        //1.adım url set et
        spec.pathParam("first","todos");
        //2.adım expected data al
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(7,"qui consectetur id",false);
        System.out.println(expectedData);
        //3.adım request gonder response al
      Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
      response.prettyPrint();
      //4.adım assertion et

        response.then().assertThat().statusCode(201);

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());




    }
}
