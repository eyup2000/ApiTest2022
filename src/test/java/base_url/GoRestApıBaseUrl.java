package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestApıBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp(){
        spec= new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();
    }



}
