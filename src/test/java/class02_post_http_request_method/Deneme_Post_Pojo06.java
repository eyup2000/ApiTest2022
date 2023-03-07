package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class Deneme_Post_Pojo06 extends HerOkuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/
    {
    "bookingid": 10829,
    "booking": {
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
}

When
           Url'e POST Request gonder
   Then
       Status code is 201
   And
        response body is like
        {}
     */
    @Test
    public void PostVeDeletePoja(){
        //1.adım url set et
        spec.pathParam("first","booking");

        //2. adım expected datayı set et
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo requestBody = new BookingPojo("John","Smith",111,true,bookingDatesPojo,"Breakfast");
        System.out.println(requestBody);

        //3.adım request gonder response al
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
     Integer bookingid = json.getInt("Id");

     spec.pathParams("first","booking","ıkıncı",bookingid);

    Response response1= given().spec(spec).when().delete("/{first},/{ıkıncı}");
     response1.prettyPrint();

     Map<String, Objects> actualDate = response1.as(HashMap.class);

        Assert.assertTrue(actualDate.size()==0);

    }
}
