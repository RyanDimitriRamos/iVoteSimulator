import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class VotingService {
    private final Question question;
    private int submissionsCount = 0;

    VotingService(Question question){
        this.question = question;
    }
    //When this is called the statistics stored in a hashmap are displayed showing how many students answered each question.
    private void displayStatistics(LinkedHashMap<String, Integer> statistics){
        System.out.println("The correct answer to the question: \"" + question.getQuestionText() + "\" is : " + question.getCorrectAnswer() + ".");
        System.out.println("Total accepted submissions: " + submissionsCount);
        System.out.println("The distribution of the answers selected are as follow: ");
        for(Map.Entry<String, Integer> candidateAnswer: statistics.entrySet()){
            System.out.println(candidateAnswer.getKey() + ": " + candidateAnswer.getValue());
        }

    }
    private LinkedHashMap<String, Integer> tallySubmissions(HashMap<String, String> validatedSubmissions){
        LinkedHashMap<String, Integer> statistics = new LinkedHashMap<>();
        for(String choice : question.getCandidateAnswers().keySet()){
            statistics.put(choice, 0);
        }

        // If the students answer is a candidate answer increment the candidate answers value
        // Example: If the student's answer is 'A' then increment the key 'A' in the statistics
        // hashmap by one. This means that the number of students that selected 'A' has increased.
        for(Map.Entry<String, String> submission: validatedSubmissions.entrySet()) {
            if (statistics.containsKey(submission.getValue())){
                statistics.replace(submission.getValue(), statistics.get(submission.getValue()) + 1);
                submissionsCount++;
            }
        }
        return statistics;
    }
    private HashMap<String, String> validateSubmissions(ArrayList<Student> students){
        HashMap<String, String> validatedSubmissions= new HashMap<>();
        for(Student student : students) {
            // if the student already answered replace their answer with the new answer the just submitted
            if(validatedSubmissions.containsKey(student.getUniqueId())){
                validatedSubmissions.replace(student.getUniqueId(), student.getAnswer());
            }
            else{
                validatedSubmissions.put(student.getUniqueId(), student.getAnswer());
            }
        }
        return validatedSubmissions;
    }
    public void submit(ArrayList<Student> students){
        HashMap<String, String> validatedSubmissions = validateSubmissions(students);
        LinkedHashMap<String, Integer> statistics = tallySubmissions(validatedSubmissions);
        displayStatistics(statistics);
    }
}