package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Deneme_Post_Pojo05 extends HerOkuAppBaseUrl {

   /* https://restful-booker.herokuapp.com/booking/2379

    {
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "super bowls"
}
      */


    @Test
    public void deneme_Pojo(){
        spec.pathParams("first","booking");

        BookingDatesPojo requestBody = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedBody = new BookingPojo("John","Allen",111,true,requestBody,"Breakfast");
        System.out.println(expectedBody);

      Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedBody).post("/{first}");
      response.prettyPrint();

        HerOkuAppPostResponseBodyPojo actualData =response.as(HerOkuAppPostResponseBodyPojo.class);

        Assert.assertEquals(expectedBody.getFirstname(),actualData.getBooking().getFirstname());
        Assert.assertEquals(expectedBody.getLastname(),actualData.getBooking().getLastname());
        Assert.assertEquals(expectedBody.getTotalprice(),actualData.getBooking().getTotalprice());
        Assert.assertEquals(expectedBody.getDepositpaid(),actualData.getBooking().getDepositpaid());
        Assert.assertEquals(expectedBody.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        Assert.assertEquals(expectedBody.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(expectedBody.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

    }
    }

