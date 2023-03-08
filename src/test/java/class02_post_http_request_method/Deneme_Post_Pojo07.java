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

public class Deneme_Post_Pojo07 extends HerOkuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/
    {
        "bookingid": 7998,
            "booking": {
        "firstname": "John",
                "lastname": "toprak",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
    }*/

    @Test
    public void TestPost(){
        //1. adım url set
        spec.pathParam("first","booking");
        //expect data set
        BookingDatesPojo request = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo requestbody = new BookingPojo("John","toprak",111,true,request,"additionalneeds");

        System.out.println("request = " + request);
        System.out.println("requestbody = " + requestbody);

        //request gönder response al
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(requestbody).post("/{first}");
        response.prettyPrint();

      //4. assert et
        HerOkuAppPostResponseBodyPojo actual = response.as(HerOkuAppPostResponseBodyPojo.class);
        Assert.assertEquals(requestbody.getFirstname(),actual.getBooking().getFirstname());
        Assert.assertEquals(requestbody.getLastname(),actual.getBooking().getLastname());
        Assert.assertEquals(requestbody.getTotalprice(),actual.getBooking().getTotalprice());
        Assert.assertEquals(requestbody.getDepositpaid(),actual.getBooking().getDepositpaid());

        Assert.assertEquals(requestbody.getBookingdates().getCheckout(),actual.getBooking().getBookingdates().getCheckout());
    }
}
