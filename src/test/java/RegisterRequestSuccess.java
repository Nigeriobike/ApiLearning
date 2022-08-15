public class RegisterRequestSuccess {
    private Integer id;
    private String token ;

    public RegisterRequestSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;
    }
    public RegisterRequestSuccess(){

    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
