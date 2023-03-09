package class02_post_http_request_method;

public class Deneme_Post_Pojo08 {
         /*
   Given
            https://restful-booker.herokuapp.com/booking/

            {
               "firstname": "Aykut",
               "lastname": "Saglam",
               "totalprice": 998,
               "depositpaid": true,
               "bookingdates":
                       {
                        "checkin": "2022-11-05",
                        "checkout": "2022-11-21"
                        },
               "additionalneeds": "Breakfast with coffee, Dragon Juice"
             }
   When
           URL'e POST Request gonderdim
   Then
           Status code 200 olmali
   And
           Response body asagidaki gibi olmali
                 {
                "firstname": "Aykut",
                "lastname": "Saglam",
                "totalprice": 998,
                "depositpaid": true,
                "bookingdates": {
                               "checkin": "2022-11-05",
                               "checkout": "2022-11-21"
                                  },
                "additionalneeds": "Breakfast with coffee, Dragon Juice"
                    }
*/
}
