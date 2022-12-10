package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetVeObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
Given
    https://jsonplaceholder.typicode.com/todos/198
When
    Url'e GET Request gonder
Then
   Status code is 200
   And response body is like {
                                "userId": 10,
                                "id": 198,
                                "title": "quis eius est sint explicabo",
                                "completed": true
                              }
 */
    @Test
    public void getVeObjectMapper01(){
        //1.adım url set et
        spec.pathParams("first","todos","second",198);

        //2.adım expected data'yı set et
        //1.yol
      /*  String beklenenData = "{\n"+
                "\"userId\": 10,\n"+
                "\"id\": 198,\n"+
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n"+
                            "}" ;
        Map<String, Object> beklenenDataMap= JsonUtil.jsonJavayaCevir(beklenenData, HashMap.class);
        System.out.println(beklenenDataMap);*/
        //2.yol
JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
String beklenenDataMap2 = data.beklenenDataStringFormantında(10,"quis eius est sint explicabo",true);
Map<String, Object> beklenenDataMap = JsonUtil.jsonJavayaCevir(beklenenDataMap2,HashMap.class);
System.out.println(beklenenDataMap);



        //3.adım request gonder response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualMap= JsonUtil.jsonJavayaCevir(response.asString(), HashMap.class);
        System.out.println(actualMap);

        //4.adım assertion yap
        assertEquals(200,response.getStatusCode());
        assertEquals(beklenenDataMap.get("userId"),actualMap.get("userId"));
        assertEquals(beklenenDataMap.get("title"),actualMap.get("title"));

    }
}
