package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class Deneme_Get_Pojo_03 extends HerOkuAppBaseUrl {
        /*
            https://restful-booker.herokuapp.com/booking/2697

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
        public void Get_Pojo() {
            //1.adım url set et

            spec.pathParams("first", "booking", "ikinci", 2697);

            //2.adım expected datayı set et

            BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
            System.out.println("bookingDatesPojo = " + bookingDatesPojo);

            BookingPojo requestBody = new BookingPojo("John", "Allen", 111, true, bookingDatesPojo, "super bowls");
            System.out.println("requestBody = " + requestBody);

            //3.adım request gonder response al

          Response response = given().spec(spec).when().get("/{first}/{ikinci}");
          response.prettyPrint();

          //4.adım assert et

            BookingPojo actualDate = response.as(BookingPojo.class);

            Assert.assertEquals(requestBody.getFirstname(),actualDate.getFirstname());
            Assert.assertEquals(requestBody.getLastname(),actualDate.getLastname());
            Assert.assertEquals(requestBody.getTotalprice(),actualDate.getTotalprice());
            Assert.assertEquals(requestBody.getDepositpaid(),actualDate.getDepositpaid());
            Assert.assertEquals(requestBody.getAdditionalneeds(),actualDate.getAdditionalneeds());

            Assert.assertEquals(requestBody.getBookingdates().getCheckout(),actualDate.getBookingdates().getCheckout());
            Assert.assertEquals(requestBody.getBookingdates().getCheckin(),actualDate.getBookingdates().getCheckin());

        }
    }
