import java.util.*;

record QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
}

class Quiz {
    private final List<QuizQuestion> questions;
    private int score;
    private final Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        for (QuizQuestion question : questions) {
            displayQuestion(question);
            boolean isCorrect = attemptAnswer(question);
            if (isCorrect) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }
        displayResult();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.question());
        List<String> options = question.options();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private boolean attemptAnswer(QuizQuestion question) {
        int correctAnswerIndex = question.correctAnswerIndex();
        System.out.print("Enter your choice (1-" + question.options().size() + "): ");
        int userChoice = scanner.nextInt();
        return userChoice == (correctAnswerIndex + 1); // Adjust index as user input starts from 1
    }

    private void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Creating Quiz Questions
        QuizQuestion q1 = new QuizQuestion("What is the capital of France?",
                Arrays.asList("London", "Paris", "Berlin", "Madrid"), 1);
        QuizQuestion q2 = new QuizQuestion("What is 2+2?", Arrays.asList("3", "4", "5", "6"), 1);
        QuizQuestion q3 = new QuizQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("Jupiter", "Mars", "Saturn", "Venus"), 1);

        // Adding questions to the quiz
        List<QuizQuestion> quizQuestions = Arrays.asList(q1, q2, q3);

        // Creating and starting the Quiz
        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}
