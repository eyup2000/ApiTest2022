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

public class Deneme_Post_Pojo02 extends HerOkuAppBaseUrl {
     /*
   Given
            https://restful-booker.herokuapp.com/booking/

                {
                    "firstname": "Guoqiang",
                    "lastname": "Bonfanti",
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
    public void deneme_Post_Pojo02(){
    //1.adım url set
    spec.pathParam("first", "booking");

    //2.adım expected datayı request set et
    BookingDatesPojo bookingData = new BookingDatesPojo("2018-01-01","2019-01-01");
    System.out.println(bookingData);

    BookingPojo expectedData = new BookingPojo("Guoqiang","Bonfanti",111,true,bookingData,"Breakfast");
    System.out.println(expectedData);

    //3.adım request gonder response al
  Response response =  given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
  response.prettyPrint();

    HerOkuAppPostResponseBodyPojo actualsData = response.as(HerOkuAppPostResponseBodyPojo.class);
    System.out.println(actualsData);

    //4.adım assetion yap
    assertEquals(expectedData.getFirstname(),actualsData.getBooking().getFirstname());
    assertEquals(expectedData.getLastname(),actualsData.getBooking().getLastname());
    assertEquals(expectedData.getTotalprice(),actualsData.getBooking().getTotalprice());
    assertEquals(expectedData.getDepositpaid(),actualsData.getBooking().getDepositpaid());

    assertEquals(expectedData.getBookingdates().getCheckout(),actualsData.getBooking().getBookingdates().getCheckout());
    assertEquals(expectedData.getBookingdates().getCheckin(),actualsData.getBooking().getBookingdates().getCheckin());

    assertEquals(expectedData.getAdditionalneeds(),actualsData.getBooking().getAdditionalneeds());

}

}
