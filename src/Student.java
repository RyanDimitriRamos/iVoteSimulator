import java.util.UUID;

public class Student {
    private String uniqueId = UUID.randomUUID().toString();
    private String answer;

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer(){ return answer; }

    public String getUniqueId() {
        return uniqueId;
    }

}