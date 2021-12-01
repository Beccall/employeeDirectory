package employeeDirectory;


import lombok.Data;

@Data
public class ErrorMessage {

    private String errorMessage = "Manager is required for this role";

    /**
     * Creates an error message object
     *
     * @param errorMessage the error message that is getting relayed
     */
    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
