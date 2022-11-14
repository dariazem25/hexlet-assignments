package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    private String message;

    public NegativeRadiusException(String s) {
        this.message = s;
    }

    public String getErrorDescription() {
        return this.message;
    }
}
// END
