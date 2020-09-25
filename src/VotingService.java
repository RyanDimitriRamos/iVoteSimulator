import java.util.*;

public class VotingService {
    private final Question question;

    VotingService(Question question){
        this.question = question;
    }
    //When this is called the statistics stored in a hashmap are displayed showing how many students answered each question.
    private void displayStatistics(LinkedHashMap<String, Integer> statistics){
        System.out.print("The correct answer(s) to the question: \"" + question.getQuestionText() + "\" is/are : ");
        question.printAnswers();
        System.out.println("The distribution of the answers selected are as follow: ");
        for(Map.Entry<String, Integer> candidateAnswer: statistics.entrySet()){
            System.out.println(candidateAnswer.getKey() + ": " + candidateAnswer.getValue());
        }

    }
    private LinkedHashMap<String, Integer> tallySubmissions(HashMap<String, Stack<String>> validatedSubmissions){
        LinkedHashMap<String, Integer> statistics = new LinkedHashMap<>();
        for(String choice : question.getCandidateAnswers().keySet()){
            statistics.put(choice, 0);
        }

        // If the students answer is a candidate answer increment the candidate answers value
        // Example: If the student's answer is 'A' then increment the key 'A' in the statistics
        // hashmap by one. This means that the number of students that selected 'A' has increased.
        for(Map.Entry<String, Stack<String>> submission: validatedSubmissions.entrySet()) {
            if(question.getQuestionType().equals("MultiChoice")) { // this makes sure that students who submit more than one answer for single choice only have the last answer added.
                for (String answer : submission.getValue()) {
                    if (statistics.containsKey(answer)) {
                        statistics.replace(answer, statistics.get(answer) + 1);
                    }
                }
            }else{
                String answer;
                answer = submission.getValue().pop();
                statistics.replace(answer, statistics.get(answer) + 1);

            }
        }
        return statistics;
    }
    private HashMap<String, Stack<String>> validateSubmissions(ArrayList<Student> students){
        HashMap<String, Stack<String>> validatedSubmissions= new HashMap<>();
        for(Student student : students) {
            // if the student already answered replace their answer with the new answer the just submitted
            if(validatedSubmissions.containsKey(student.getUniqueId())){
                validatedSubmissions.replace(student.getUniqueId(), student.getAnswers());
            }
            else{
                validatedSubmissions.put(student.getUniqueId(), student.getAnswers());
            }
        }
        return validatedSubmissions;
    }
    public void submit(ArrayList<Student> students){
        HashMap<String, Stack<String>>  validatedSubmissions = validateSubmissions(students);
        LinkedHashMap<String, Integer> statistics = tallySubmissions(validatedSubmissions);
        displayStatistics(statistics);
    }
}