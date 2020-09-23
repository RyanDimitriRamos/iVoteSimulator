import java.util.LinkedHashMap;
import java.util.InputMismatchException;

public class TrueOrFalseQuestion implements Question  {
    private String questionText;
    private LinkedHashMap<String, String> candidateAnswers;
    private String correctAnswer;


    TrueOrFalseQuestion(String questionText, LinkedHashMap<String, String> candidateAnswers, String correctAnswer){
        System.out.println("The following question will be a True or False question. Please select one of the following candidate answers.");
        this.questionText = questionText;
        if(candidateAnswers.size() == 2){
            this.candidateAnswers = candidateAnswers;
        }else{
            // I want the code to crash and throw an exception if the user tries to pass in an arraylist with more than two options
            // for a true or false question
            throw new InputMismatchException("A true or false question should only have two candidateAnswers. Please pass an array list with two candidateAnswers");
        }
        this.correctAnswer = correctAnswer;
        displayQuestion();

    }

    public String getQuestionText() { return questionText; }

    public String getCorrectAnswer() { return correctAnswer; }

    public LinkedHashMap<String, String> getCandidateAnswers() { return candidateAnswers; }

    public void displayQuestion(){
        System.out.println(questionText);
        for(String choice : candidateAnswers.keySet()){
            System.out.println(choice + ". " + candidateAnswers.get(choice));
        }
    }

}
