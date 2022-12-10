package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Deneme_Post_Pojo04 extends HerOkuAppBaseUrl {
    /*
            https://restful-booker.herokuapp.com/booking/5579

    {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void DenemePostPojo(){
        //url set et
        spec.pathParams("first","booking");

        //expected datayı set
        BookingDatesPojo expectedData = new BookingDatesPojo( "2018-01-01","2019-01-01");
        System.out.println(expectedData);

        BookingPojo requestBody = new BookingPojo("John","Smith",111,true,expectedData,"Breakfast");
        System.out.println(requestBody);

        //request gonder reponse al
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
       response.prettyPrint();


        HerOkuAppPostResponseBodyPojo actualData = response.as(HerOkuAppPostResponseBodyPojo.class);
        System.out.println(actualData);

        //assertion yap

        response.then().statusCode(200);

        assertEquals(requestBody.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getBooking().getDepositpaid());

        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());

        assertEquals(requestBody.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());











    }
}
