package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class her extends HerOkuAppBaseUrl {
    @Test
    public void AhmetAbiTest01(){
        //1.adım url set
        spec.pathParams("first","booking","second",476);

        //2.expected datayı set et
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-02");
        System.out.println("bookingDatesPojo = " + bookingDatesPojo);

        BookingPojo requestBody = new BookingPojo("Ahmet","Toprak",111,true,bookingDatesPojo,"Admsıııınnn");
        System.out.println("requestBody = " + requestBody);

        //3. adım request gonder response al

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //4. adım assert yap
    }
}
