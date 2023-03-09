package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Deneme_Get_Pojo05 extends HerOkuAppBaseUrl {

/*

   https://restful-booker.herokuapp.com/booking/476

    {
        "firstname": "Ahmet",
            "lastname": "Toprak",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-02"
    },
        "additionalneeds": "Admsıııınnn"
    }

     */

    @Test
    public void AhmetAbiTest01(){
        //1.adım url set
        spec.pathParams("first","booking","second",2872);

        //2.expected datayı set et
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-02");
        System.out.println("bookingDatesPojo = " + bookingDatesPojo);

        BookingPojo requestBody = new BookingPojo("Ahmet","Toprak",111,true,bookingDatesPojo,"Admsıııınnn");
        System.out.println("requestBody = " + requestBody);

        //3. adım request gonder response al

      Response response = given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();

        //4. adım assert yap

        BookingPojo actual =response.as(BookingPojo.class);
        System.out.println("actual = " + actual);


        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("İsimler eşleşmiyor",requestBody.getFirstname(),actual.getFirstname());
        Assert.assertEquals(requestBody.getLastname(),actual.getLastname());

        Assert.assertEquals(requestBody.getBookingdates().getCheckin(),actual.getBookingdates().getCheckin());

        Assert.assertEquals(requestBody.getAdditionalneeds(),actual.getAdditionalneeds());
    }
}
