import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class Student {
    private String uniqueId = UUID.randomUUID().toString();
    //private String answer;
    private Stack<String> answers;

    //public void setAnswer(String answer){ this.answer = answer; }

    public void setAnswers(Stack<String> answers){ this.answers = answers; }

    public Stack<String> getAnswers(){
        return answers;
    }


    //public String getAnswer(){ return answer; }

    public String getUniqueId() { return uniqueId; }

}