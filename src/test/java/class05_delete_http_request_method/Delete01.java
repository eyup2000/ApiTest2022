package class05_delete_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {

    @Test
    public void delete01(){
        //1. adım url set et
        spec.pathParams("first", "todos","second",198);
        //2. expected datayı set et
        Map<String,Object> beklenenDataMap = new HashMap<>();
        System.out.println(beklenenDataMap);
        //3. adım request gonder respond al
       Response response = given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();

      HashMap<String,Object> actualMap = response.as(HashMap.class);
        System.out.println(actualMap);


      //4.adım Asertion yap/ verification
      response.then().assertThat().statusCode(200);

      assertEquals(beklenenDataMap,actualMap);
      assertTrue(actualMap.size()==0);
    }
}
