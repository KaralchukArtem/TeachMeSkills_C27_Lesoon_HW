package pack;

public class SubTask {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void getSubTaskMessage(){
        System.out.println("SubTask - " + this.message);
    }

}
