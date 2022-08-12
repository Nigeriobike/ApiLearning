import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Reqres extends Init {
    private  String URL = "https://reqres.in";
    @Test
    public  void checkGet(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(200));
        List<UserData> users = given().
                when().contentType(ContentType.JSON).
                get(URL+"/api/users?page=2")
                .then().log().all().
                extract().body().jsonPath().getList("data",UserData.class);
        //users.forEach(x-> System.out.println(x.getId()));
        //users.forEach(x->Assert.assertTrue(x.getEmail().startsWith(x.getFirst_name().toLowerCase())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().contains("@")));
    }
    @Test
    public void register(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(200));
    }
}



