package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetVePojo01 extends HerOkuAppBaseUrl {
    /*
Given
        https://restful-booker.herokuapp.com/booking/2
When
   Url'e GET Request gonder
Then
    Status code is 200
And response body is like
        {
            "firstname": "James",
            "lastname": "Brown",
            "totalprice": 2400,
            "depositpaid": true,
            "bookingdates": {
                               "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                }
             "additionalneeds": "Breakfast"
          }

 */
@Test
    public void getVePojo01(){
    spec.pathParams("first","booking","second",2);

    BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo requestBody = new BookingPojo("James","Brown",2400,true,bookingDates,"Breakfast");
    System.out.println(requestBody);
  Response response = given().spec(spec).when().get("/{first}/{second}");
  response.prettyPrint();
}


}
