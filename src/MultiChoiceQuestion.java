import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class MultiChoiceQuestion implements Question{
    private String questionText;
    private LinkedHashMap<String, String> candidateAnswers;
    private List<String> correctAnswers;
    private String questionType = "MultiChoice";

    MultiChoiceQuestion(String questionText, LinkedHashMap<String,String> candidateAnswers, List<String> correctAnswers){
        System.out.println("The following question will be a Multiple Choice question. Please select one of the following candidate answers.");
        this.questionText = questionText;
        this.candidateAnswers = candidateAnswers;
        this.correctAnswers = correctAnswers;
        displayQuestion();
    }

    public String getQuestionText() { return questionText; }

    public List<String> getCorrectAnswer() { return correctAnswers; }

    public LinkedHashMap<String, String> getCandidateAnswers() { return candidateAnswers; }

    public void displayQuestion(){
        System.out.println(questionText);
        for(Map.Entry<String, String> choice: candidateAnswers.entrySet()){
            System.out.println(choice.getKey() + ": " + choice.getValue());
        }
    }
    public void printAnswers(){
        for(String correctAnswer: correctAnswers){
            System.out.print(correctAnswer + " ");
        }
    }

    public String getQuestionType() { return questionType; }


}
