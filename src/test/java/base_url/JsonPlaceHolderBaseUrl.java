package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;

    @Before

    public void setup(){
    spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();

        }

    }
