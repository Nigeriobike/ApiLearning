import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public  RequestSpecification requestSpecification(String url){
        return  new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
    public ResponseSpecification responseSpecification(int i){
        return  new ResponseSpecBuilder()
                .expectStatusCode(i)
                .build();
    }
    public void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.responseSpecification=response;
        RestAssured.requestSpecification = request;
    }
}
