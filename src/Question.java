import java.util.LinkedHashMap;
import java.util.List;

public interface Question {

    String getQuestionText();

    List<String> getCorrectAnswer();

    LinkedHashMap<String, String> getCandidateAnswers();

    void displayQuestion();

    void printAnswers();

    String getQuestionType();


}