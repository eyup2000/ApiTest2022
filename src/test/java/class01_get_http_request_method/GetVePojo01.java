package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
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
                "firstname": "Sally",
                "lastname": "Smith",
                "totalprice": 565,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2017-10-26",
                    "checkout": "2021-12-25"
                },
                "additionalneeds": "Breakfast"
            }

 */
@Test
    public void getVePojo01(){
    spec.pathParams("first","booking","second",2);

    BookingDatesPojo bookingDates = new BookingDatesPojo("2017-03-26","2020-11-13");

    BookingPojo requestBody = new BookingPojo("Mark","Brown",709,true,bookingDates,"Breakfast");

    System.out.println(requestBody);

    Response response = given().spec(spec).when().get("/{first}/{second}");

    response.prettyPrint();

    BookingPojo actualData = response.as(BookingPojo.class);

    Assert.assertEquals(requestBody.getFirstname(),actualData.getFirstname());
    Assert.assertEquals(requestBody.getLastname() , actualData.getLastname());
    Assert.assertEquals(requestBody.getTotalprice(),actualData.getTotalprice());
    Assert.assertEquals(requestBody.getDepositpaid(),actualData.getDepositpaid());
    Assert.assertEquals(requestBody.getAdditionalneeds(),actualData.getAdditionalneeds());

    Assert.assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
    Assert.assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());



}


}
