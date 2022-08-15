public class RegisterRequestError {
    private String error;

    public RegisterRequestError(String email) {
        this.error = email;
    }

    public RegisterRequestError() {
    }

    public String getError() {
        return error;
    }
}
