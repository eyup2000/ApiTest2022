package class03_put_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    // put : herşeyi günceller
    //patch method kısmı güncelleme yapar

    /*
  Given
      https://jsonplaceholder.typicode.com/todos/198

    {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
  When
       URl'e PUT Request gonder
  Then
       Status code is 200
       And response body is like
       {
          "userId": 21,
          "title": "Wash the dishes",
          "completed": false
         }
   */
    @Test
    public void put01(){
        //1. adım url set et
        spec.pathParams("first","todos","second",198);
        //2. adım request body veya expected data set et
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap =  requestBody.expectedDataSetUpKey(10,"quis eius est sint explicabo", true);

       //3. adım request gonder response al
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
       response.prettyPrint();

       //4. adım asseretion yap
        //1.yol
       response.then().assertThat().statusCode(200).body("completed", equalTo(requestBodyMap.get("completed")),
               "title", equalTo(requestBodyMap.get("title")),
               "userId", equalTo(requestBodyMap.get("userId")));

        //2.yol
        //Gson kullanarak ==> de-serialization
      Map<String,Object> gercekDataMap = response.as(HashMap.class);
      assertEquals(requestBodyMap.get("completed"),gercekDataMap.get("completed"));
      assertEquals(requestBodyMap.get("title"),gercekDataMap.get("title"));

      //Gson kullanarak serialization yapmak Java object data ==> Json formatına donusturuyoruz
        Map<String , Integer> yas = new HashMap<>();
        yas.put("Ali can" , 15);
        yas.put("veli can" , 18);
        yas.put("mehmet can" , 26);
        yas.put("ayse " , 84);
        yas.put("ahmet can" , 19);
        System.out.println(yas);//{Ali can=15, ayse =84, mehmet can=26, ahmet can=19, veli can=18}

        //yas ==> Json formatına donusutur
        //1. adım
        Gson gson = new Gson();
        //2. Gson object formatını kullanark Java object Json formatına donustur
       String JavadanJsona = gson.toJson(yas);

        System.out.println(JavadanJsona);//{"Ali can":15,"ayse ":84,"mehmet can":26,"ahmet can":19,"veli can":18}


    }
}
