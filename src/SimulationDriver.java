import java.util.*;

public class SimulationDriver {
    //Here are three  parallel arrays storing the questions candidate answers, and actual answer
    private static ArrayList<String> mcQuestionTexts = new ArrayList<>(Arrays.asList("Who are the protagonists in Star Wars: A New Hope?",
            "Who are the antagonists in Star Wars: A New Hope?"
    ));
    private static ArrayList<LinkedHashMap<String, String>> mcCandidateAnswers = initializeCandidateAnswers(1);

    private static ArrayList<List<String>> mcAnswers = new ArrayList<>(Arrays.asList(Arrays.asList("A", "C", "D"), Arrays.asList("A", "B")));

    // three more parallel arrays storing the single choice questions, their candidate answers, and the answer
    private static ArrayList<String> scQuestionTexts = new ArrayList<>(Arrays.asList("The original Back to the Future finished filming in 1985.",
            "The original Total Recall stars Arnold Schwarzenegger"
    ));
    private static ArrayList<LinkedHashMap<String, String>> scCandidateAnswers = initializeCandidateAnswers(2);
    private static ArrayList<List<String>> scAnswers = new ArrayList<>(Arrays.asList(Arrays.asList("1"), Arrays.asList("1")));


    // Function that creates a couple of default questions  candidate answers for both single and multi choice questions.
    private static ArrayList<LinkedHashMap<String, String>> initializeCandidateAnswers(int questionType){
        if(questionType == 1){
            ArrayList<LinkedHashMap<String, String>> multipleChoice = new ArrayList<>();
            LinkedHashMap<String, String> question1 = new LinkedHashMap<>(); // question 1 hashmap
            question1.put("A", "Luke Skywalker");
            question1.put("B", "Anakin Skywalker");
            question1.put("C", "Princess Leia");
            question1.put("D", "Han Solo");
            multipleChoice.add(question1);
            LinkedHashMap<String, String> question2 = new LinkedHashMap<>();
            question2.put("A", "Grand Moff Tarkin");
            question2.put("B", "Darth Vader");
            question2.put("C", "Count Duku");
            question2.put("D", "Darth Maul");
            multipleChoice.add(question2);
            return multipleChoice;
        }
        else{
            ArrayList<LinkedHashMap<String, String>> singleChoice = new ArrayList<>();
            LinkedHashMap<String, String> question1 = new LinkedHashMap<>(); // question 1 hashmap
            question1.put("1", "True");
            question1.put("2", "False");
            singleChoice.add(question1);
            LinkedHashMap<String, String> question2 = new LinkedHashMap<>();
            question2.put("1", "True");
            question2.put("2", "False");
            singleChoice.add(question2);
            return singleChoice;
        }
    }


    private static ArrayList<Student> generateStudents(){
        Random randStudents = new Random();
        int maxStudents = 20 ; //for testing purposes, this can be changed later
        int minStudents = 1; //for testing purposes this can be changed later
        int numStudents= randStudents.nextInt((maxStudents - minStudents) +1) + minStudents;
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < numStudents; i++){
            students.add(new Student());
        }
        return students;
    }
    private static Question pickQuestion(){
        Random rand = new Random();
        // here there are two values minQuestionType and maxQuestionType
        // These are here to randomly select a type of question from the types of question.
        // The numbers are assigned as 1: Multiple Choice 2: True or False
        // The max question type variable can be change later if more question types are added.
        int minQuestionType = 1;
        int maxQuestionType = 2;
        int questionType = rand.nextInt((maxQuestionType - minQuestionType) + 1) + minQuestionType;

        if(questionType == 1){
            int maxQuestionNumber = mcQuestionTexts.size();
            int questionIndex = rand.nextInt(maxQuestionNumber);
            return new MultiChoiceQuestion(mcQuestionTexts.get(questionIndex), mcCandidateAnswers.get(questionIndex), mcAnswers.get(questionIndex));
        }
        else{
            int maxQuestionNumber = scQuestionTexts.size();
            int questionIndex = rand.nextInt(maxQuestionNumber);
            return new SingleChoiceQuestion(scQuestionTexts.get(questionIndex), scCandidateAnswers.get(questionIndex), scAnswers.get(questionIndex));
        }
    }
    // This function automatically has all of the students select an answer from the candidate answer at random and then submits each of the students answer choices.
    // This function accepts this randomly generated students, the voting service, the question the randomly generate students will be answering, and a boolean to
    // to make some of the students submit more than one answer to the testing service. This is to make sure that the testing service only accepts one submission
    // per student.
    private static void autoSubmitAnswers(ArrayList<Student> students, VotingService votingService, Question testQuestion, boolean extraSubmissions) {
        Random rand = new Random();
        int studentAnswer;
        int numberOfAnswers;
        String[] candidateAnswerKeys = testQuestion.getCandidateAnswers().keySet().toArray(new String[0]); // put the keys in an array so the student can randomly select a key
        if (testQuestion.getQuestionType().equals("MultiChoice")) {
            for (Student student : students) {
                Stack<String> answers = new Stack<>();
                numberOfAnswers = rand.nextInt(candidateAnswerKeys.length) + 1;
                for (int i = 0; i < numberOfAnswers; i++) {
                    studentAnswer = rand.nextInt(candidateAnswerKeys.length);
                    if(!answers.contains(candidateAnswerKeys[studentAnswer]))
                        answers.add(candidateAnswerKeys[studentAnswer]);
                }
                student.setAnswers(answers);
            }
        } else {
            for (Student student : students) {
                Stack<String> answers = new Stack<>();
                studentAnswer = rand.nextInt(testQuestion.getCandidateAnswers().size());
                answers.add(candidateAnswerKeys[studentAnswer]);
                student.setAnswers(answers);
            }
        }
        System.out.println("The number of student submissions is: " + students.size()); // This shows how many answers were submitted to the service
        votingService.submit(students);
    }

    public static void main(String[] args){
        ArrayList<Student> students = generateStudents();
        System.out.println("The are " + students.size() + " students playing this round."); // this shows how many students were generated for the test
        Question testQuestion = pickQuestion(); // this is the question used for the round
        VotingService votingService = new VotingService(testQuestion); // the voting service is initialized with the question and candidate answers
        autoSubmitAnswers(students, votingService, testQuestion, true); // this is a method for testing that has a classroom of students answer the question chosen earlier.
        //autoSubmitAnswers(students, votingService, testQuestion, true, 2);
    }
}
