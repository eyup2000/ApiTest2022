package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GetVeObjectMapper02 extends JsonPlaceHolderBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/2
    When
         Url'e GET Request gonder
    Then
         Status code is 200
        {
            "firstname": "Mark",
            "lastname": "Ericsson",
            "totalprice": 726,
            "depositpaid": true,
            "bookingdates": {
                    "checkin": "2015-08-07",
                    "checkout": "2020-10-25"
                            },
            "additionalneeds": "Breakfast"
           }
 */
    @Test
    public void getVeObjectMapper02(){
        //1.adım url set et
        spec.pathParams("first","booking","second",2);

        //2.adım expected datayı set et
        String expectedData = "{\n"+
                    "\"firstname\": \"Mark\",\n"+
                    "\"lastname\": \"Ericsson\",\n"+
                    "\"totalprice\": 726,\n"+
                    "\"depositpaid\": true,\n"+
                    "\"bookingdates\": {\n"+
                    "\"checkin\": \"2015-08-07\",\n"+
                    "\"checkout\": \"2020-10-25\"\n"+
                    " },\n"+
                    "\"additionalneeds\": \"Breakfast\"\n"+
                   " }";
        HashMap<String,Object>ecpectedDataMap = JsonUtil.jsonJavayaCevir(expectedData,HashMap.class);
        System.out.println(ecpectedDataMap);

        Response response = given().spec(spec).when().get("/{first}/{second}");
response.prettyPrint();
        HashMap<String,Object> actualData = JsonUtil.jsonJavayaCevir(response.asString(),HashMap.class);
        System.out.println(actualData);



    }
}
