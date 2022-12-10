package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/3794
    When
        Url'e GET Request gonder
    Then
        Response body asagidaki gibi olmali;
        {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates":
                    {
                    "checkin": "2018-01-01",
                    "checkout": "2018-01-05"
                     }
            "additionalneeds": "Breakfast"
        }
 */
    @Test
    public void get09(){
        //1. adım url set et
spec.pathParams("first","booking","second",3794);
        //2. adım expected date'yı set et
        Map<String,String> expectedBookingDates = new HashMap<>();

        expectedBookingDates.put("checkin","2018-01-01");
        expectedBookingDates.put("checkout","2018-01-05");

        System.out.println(expectedBookingDates);

        Map<String,Object> expectedDates = new HashMap<>();

        expectedDates.put("firstname","Nastiia");
        expectedDates.put("lastname","Kochurenkova");
        expectedDates.put("totalprice",170);
        expectedDates.put("depositpaid",true);
        expectedDates.put("bookingdates",expectedBookingDates);
        expectedDates.put("additionalneeds","Dinner");

        System.out.println(expectedDates);

        //3.adım request gonder response et

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualDate = response.as(HashMap.class);
        System.out.println(actualDate);

        //4. adım assertion yap

       assertEquals(expectedDates.get("firstname"),actualDate.get("firstname"));
       assertEquals(expectedDates.get("lastname"),actualDate.get("lastname"));
       assertEquals(expectedDates.get("totalprice"),actualDate.get("totalprice"));
       assertEquals(expectedDates.get("depositpaid"),actualDate.get("depositpaid"));
       assertEquals(expectedDates.get("additionalneeds"),actualDate.get("additionalneeds"));

       assertEquals(expectedBookingDates.get("checkin"),((Map)actualDate.get("bookingdates")).get("checkin"));
       assertEquals(expectedBookingDates.get("checkout"),((Map)actualDate.get("bookingdates")).get("checkout"));




    }
}
