package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Deneme_Get_Pojo01 extends HerOkuAppBaseUrl {
    /*
        https://restful-booker.herokuapp.com/booking/3528

    {
    "firstname": "Bob",
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
    public void denemeGetPojo(){
        //url set et
        spec.pathParams("first","booking","todos",3528);

        //expected data set et
        BookingDatesPojo expectedData = new BookingDatesPojo("2018-01-01","2019-01-01");

        BookingPojo requestBody = new BookingPojo("Josh","Allen",111,true,expectedData,"super bowls");

        System.out.println(expectedData);
        System.out.println(requestBody);

       // request et response al
     Response response = given().
             spec(spec).
             contentType(ContentType.JSON).
             when().
             get("/{first}/{todos}");

     response.prettyPrint();

     BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(requestBody.getFirstname(),actualData.getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(requestBody.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());








    }
}
