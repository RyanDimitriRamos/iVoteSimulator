import java.util.LinkedHashMap;
import java.util.Map;

public class MultipleChoiceQuestion implements Question{
    private String questionText;
    private LinkedHashMap<String, String> candidateAnswers;
    private String correctAnswer;

    MultipleChoiceQuestion(String questionText, LinkedHashMap<String,String> candidateAnswers, String correctAnswer){
        System.out.println("The following question will be a Multiple Choice question. Please select one of the following candidate answers.");
        this.questionText = questionText;
        this.candidateAnswers = candidateAnswers;
        this.correctAnswer = correctAnswer;
        displayQuestion();
    }

    public String getQuestionText() { return questionText; }

    public String getCorrectAnswer() { return correctAnswer; }

    public LinkedHashMap<String, String> getCandidateAnswers() { return candidateAnswers; }

    public void displayQuestion(){
        System.out.println(questionText);
        for(Map.Entry<String, String> choice: candidateAnswers.entrySet()){
            System.out.println(choice.getKey() + ": " + choice.getValue());
        }
    }

}
