import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

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
    public void registerRequestSuccess(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(200));
        Integer id =4;
        String token = "QpwL5tke4Pnpja7X4";
        Register register = new Register("eve.holt@reqres.in","pistol");
        RegisterRequestSuccess success = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(RegisterRequestSuccess.class);
        Assert.assertEquals(id,success.getId());
        Assert.assertEquals(token,success.getToken());
    }
    @Test
    public void registerRequestError(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(400));
        String error = "Missing password";
        Register register = new Register("sydney@fife","");
        RegisterRequestError requestError = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(RegisterRequestError.class);
        Assert.assertEquals(error , requestError.getError());
    }
    @Test
    public void resourceCheck(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(200));
        List<ListResource> resources = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data",ListResource.class);
        List<Integer> years = resources.stream().map(ListResource::getYear).collect(Collectors.toList());
        List<Integer> sorted = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sorted,years);
    }
    @Test
    public void deleteUser(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    @Test
    public void singleResource(){
        specifications().installSpecification(specifications().requestSpecification(URL),specifications().responseSpecification(200));
        ListResource resource = given()
                .when()
                .get("api/unknown/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data",ListResource.class);
        int i;
    }
}



