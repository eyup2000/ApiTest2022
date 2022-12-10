package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {
    /*
Given
 https://restful-booker.herokuapp.com/booking
    {
        "firstname": "Eyup",
        "lastname": "Ak",
        "totalprice": 2222,
        "depositpaid": true,
            "bookingdates": {
                    "checkin": "2022-09-09",
                    "checkout": "2022-09-21"
                        }
}
     }
When
     URL'e POST Request gonder
Then
    Status code is 200
    And response body asagidaki olmali
            {
          "bookingid": 11,
          "booking": {
                    "firstname": "Selim",
                    "lastname": "Ak",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                             "checkin": "2021-09-09",
                             "checkout": "2021-09-21"
                                 }
                     }
               }
 */
    @Test
    public  void post01(){
        //1. adım url set et
        spec.pathParam("first","booking");

        //2. adım expected date'yı set et
        Map<String,String> expectedBookingDates = new HashMap<>();

        expectedBookingDates.put("checkin","2022-09-09");
        expectedBookingDates.put("checkout","22022-09-21");

        System.out.println(expectedBookingDates);

        Map<String,Object> expectedDates = new HashMap<>();

        expectedDates.put("firstname","Eyup");
        expectedDates.put("lastname","Ak");
        expectedDates.put("totalprice",2222);
        expectedDates.put("depositpaid",true);
        expectedDates.put("bookingdates",expectedBookingDates);
       // expectedDates.put("additionalneeds","Dinner");

        System.out.println(expectedDates);

        //3. adın post request ve response al
    Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDates).when().post("/{first}");
    response.prettyPrint();

    //4. adım assertion yap
      Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedDates.get("firstname") , ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedDates.get("lastname") , ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedDates.get("depositpaid") , ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedDates.get("totalprice") , ((Map)actualData.get("booking")).get("totalprice"));

assertEquals(expectedBookingDates.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
assertEquals(expectedBookingDates.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
