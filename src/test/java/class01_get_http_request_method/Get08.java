package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
/*
API teste en buyuk zorluk data type'lerdir.
1) JAVA object Map vr privitive data typleri kullanır
API ise XML,JSON GİBİ FORMATLAR KULLANILIR

JAVA VE API FARKLİ DATA TYPE KULLANILIR DOLAYISIYLA A
  ABUNLARIN BİRİBİRLERİYLE İLETİŞİM KURMASI İÇİN
  BİRBİRLERİNİE ANLATACAK GETİRİLMESİ LAZIM .
  AKSİ HALDE İLETİŞİM OLMAZ

  Birbirlerini anlamaları için yapılacak iki seçenek vardır

  1) Date type Json formatına Java object formatına çeviririz.==> De serializeation denir
  Date type Java object'ten
  Json formatına çeviririz ==>serializeation


  De-serializeation ve serializeation için 2 tane seçenek vardır

   a) Gson-- Google olusturur,
   b) object Mapper-- daha popular

   Json==> JavaScrip object natation

  */
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
 */
    @Test
    public void get08(){
        //1. adım url set et
        spec.pathParams("first","todos","second",2);
        // 2. adım expected date'yı set
       Map<String, Object> expectedDate=new HashMap<>();//HashMap daha hızlıdır
       expectedDate.put("userId",1);
       expectedDate.put("title" , "quis ut nam facilis et officia qui");
        expectedDate.put("completed" , false);
        expectedDate.put("Status code",200);
        expectedDate.put("Via","1.1 vegur");
        expectedDate.put("Server","cloudflare");

        System.out.println(expectedDate);//{Status code=200, Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, userId=1, Via=1.1 vegur}

        //3. adım request gonder response al
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //GSon kullanarak API' dan gelne Json date yi JAVA object formatına çviriyoruz.
         Map<String,Object> actualDate= response.as(HashMap.class);
        System.out.println(actualDate);

         //4. adım assertion yap
        assertEquals(expectedDate.get("userId"),actualDate.get("userId"));
        assertEquals(expectedDate.get("title"),actualDate.get("title"));
        assertEquals(expectedDate.get("completed"),actualDate.get("completed"));
        assertEquals(expectedDate.get("Status code"),response.getStatusCode());
        assertEquals(expectedDate.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDate.get("Server"),response.getHeader("Server"));

    }



    @Test
    public void get08Test(){
        spec.pathParams("first","todos","second",2);
        Map<String, Object> expectedDate=new HashMap<>();//HashMap daha hızlıdır
        expectedDate.put("userId",1);
        expectedDate.put("title" , "quis ut nam facilis et officia qui");
        expectedDate.put("completed" , false);
        expectedDate.put("Status code",200);
        expectedDate.put("Via","1.1 vegur");
        expectedDate.put("Server","cloudflare");
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        Map<String,Object> actualDate= response.as(HashMap.class);
        assertEquals(expectedDate.get("userId"),actualDate.get("userId"));
        assertEquals(expectedDate.get("title"),actualDate.get("title"));
        assertEquals(expectedDate.get("completed"),actualDate.get("completed"));
        assertEquals(expectedDate.get("Status code"),response.getStatusCode());
        assertEquals(expectedDate.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDate.get("Server"),response.getHeader("Server"));

    }
}
