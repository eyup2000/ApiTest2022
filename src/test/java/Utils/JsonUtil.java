package Utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
    //1.method Json datayı Java object çevirmek için kullanılır ==> De-serialization

    public static <T> T jsonJavayaCevir(String json,Class<T>cls){
      T javaSonuc =null;
        try {
            javaSonuc = mapper.readValue(json,cls);
        } catch (IOException e) {
            System.out.println("Json formatını Java Object formatına dönüştüremedi"+e.getMessage());
        }
        return javaSonuc;
    }

    //2. method Java Object Json data ya cevirmek için kullanılır ==> serialization
    public static String JavayıJsonCevir(Object obje){
       String jsonSonuc = null;

        try {
         jsonSonuc =   mapper.writeValueAsString(obje);
        } catch (IOException e) {
            System.out.println("java object Json formatına dönüştüremedi"+e.getMessage());
        }
        return jsonSonuc;
    }
















}
