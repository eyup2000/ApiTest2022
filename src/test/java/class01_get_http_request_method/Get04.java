package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends JsonPlaceHolderBaseUrl {
@Test
    public void get04 (){
    // 1. adım url set et
    spec.pathParam("birinci","todos");
    // 2. adım beklenen datatyı set et
    // 3. adım get request gonder response al
   Response response= given().spec(spec).accept(ContentType.JSON).when().get("/{birinci})");
   response.prettyPrint();
    // 4. adım assert yap
}
}
