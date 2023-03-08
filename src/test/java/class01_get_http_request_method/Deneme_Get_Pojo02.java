package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Deneme_Get_Pojo02 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/44

    {
    "userId": 3,
    "id": 44,
    "title": "cum debitis quis accusamus doloremque ipsa natus sapiente omnis",
    "completed": true
 }
     */

    @Test

    public void test() {

        //1.url set et

        spec.pathParams("first","todos","ıkıncı",44);

        //2.expected data set et

        JsonPlaceHolderPojo request = new JsonPlaceHolderPojo(3,"cum debitis quis accusamus doloremque ipsa natus sapiente omnis",true);
        //System.out.println(request);

        //request gönderip response al
      Response response = given().spec(spec).when().get("/{first}/{ıkıncı}");
      //response.prettyPrint();

      //assert et

        JsonPlaceHolderPojo actualdata = response.as(JsonPlaceHolderPojo.class);//json dan java object çevirdik

        assertEquals(request.getUserId(),actualdata.getUserId());
        assertEquals(request.getTitle(),actualdata.getTitle());
        assertEquals(request.getCompleted(),actualdata.getCompleted());

    }



}
