import java.util.LinkedHashMap;

public interface Question {

    String getQuestionText();

    String getCorrectAnswer();

    LinkedHashMap<String, String> getCandidateAnswers();

    void displayQuestion();

}