package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/5450
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
                    "checkin": "2013-02-23",
                    "checkout": "2014-10-23"
                     }
            "additionalneeds": "Breakfast"
        }
 */@Test
    public void get06(){
        // 1. adım url set et
        spec.pathParams("first","booking","second",620);

        // 2. adım beklenen datatyı set et
        // 3. adım get request gonder response al
 Response response= given().spec(spec).when().get("/{first}/{second}");
 response.prettyPrint();
        // 4. adım assert yap
          //1.YOL
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Sally")).
                body("lastname", equalTo("Brown")).
                body("totalprice",equalTo(111)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2013-02-23")).
                body("bookingdates.checkout",equalTo("2014-10-23")).
                body("additionalneeds",equalTo("Breakfast"));

         //2. yol jsonPath kullanarak assertion yaparız
         response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        JsonPath json = response.jsonPath();

                assertEquals("isimler eşleşmiyor","Sally" ,json.getString("firstname" ));
                assertEquals("soyisimler eşleşmiyor","Brown" ,json.getString("lastname" ));
                assertEquals("total price eşleşmiyor",111 ,json.getInt("totalprice" ));
                assertEquals("depositpaid eşleşmiyor" , true,json.get("depositpaid"));
                assertEquals("checkin eşleşmiyor" , "2013-02-23",json.getString("bookingdates.checkin"));
                assertEquals("checkout eşleşmiyor" , "2014-10-23",json.getString("bookingdates.checkout"));







    }
}
