package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Deneme_Post_Pojo03 extends HerOkuAppBaseUrl {
     /*
   Given
            https://restful-booker.herokuapp.com/booking/

            {
                "firstname": "Carlos",
                "lastname": "Dominguez",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
   When
           URL'e POST Request gonderdim
   Then
           Status code 200 olmali
   And
           Response body asagidaki gibi olmali
               {
                    "firstname": "Carlos",
                    "lastname": "Dominguez",
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
    public void deneme_Post_Pojo03 (){
        //1.adım url set et
        spec.pathParam("first","booking");

        //2.expected datayı set et
        BookingDatesPojo bookingDate = new BookingDatesPojo("2018-01-01","2019-01-01");
        System.out.println(bookingDate);
        BookingPojo requestData = new BookingPojo("Carlos", "Dominguez",111,true,bookingDate,"Breakfast");
        System.out.println(requestData);

        //3.adım requset gonder response al
     Response response = given().spec(spec).contentType(ContentType.JSON).when().body(requestData).post("/{first}");
     response.prettyPrint();


        JsonPath json = response.jsonPath();
        Integer bookingId = json.getInt("bookingid");

        spec.pathParams("first","booking","ikinci",bookingId);

       Response response1 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{ikinci}");
       response1.prettyPrint();

       BookingPojo actualData = response1.as(BookingPojo.class);
        System.out.println(actualData);

        response1.then().assertThat().statusCode(200);

        assertEquals(requestData.getFirstname(),actualData.getFirstname());
        assertEquals(requestData.getLastname(),actualData.getLastname());
        assertEquals(requestData.getTotalprice(),actualData.getTotalprice());
        assertEquals(requestData.getDepositpaid(),actualData.getDepositpaid());

        assertEquals(requestData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(requestData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        assertEquals(requestData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
