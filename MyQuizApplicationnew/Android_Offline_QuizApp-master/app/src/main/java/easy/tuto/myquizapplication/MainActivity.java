package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    ProgressBar progressBar;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        progressBar = findViewById(R.id.progressBar);

        String userName = getIntent().getStringExtra("userName");
        if (userName != null && !userName.isEmpty()) {
            totalQuestionsTextView.setText(userName + ", Total questions: " + totalQuestion);
        }

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit_btn) {
            checkAnswer();
        } else {
            // Choices button clicked
            Button clickedButton = (Button) view;
            selectedAnswer = clickedButton.getText().toString();
            clearButtonColors();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex < totalQuestion) {
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

            int progress = (currentQuestionIndex * 100) / totalQuestion;
            progressBar.setProgress(progress);
        }
    }

    private void checkAnswer() {
        if (currentQuestionIndex < totalQuestion) {
            String correctAnswer = QuestionAnswer.correctAnswers[currentQuestionIndex];
            if (selectedAnswer.equals(correctAnswer)) {
                score++;
                // Highlight the correct answer button in green
                highlightAnswer(selectedAnswer, Color.GREEN);
            } else {
                // Highlight the selected incorrect answer button in red
                highlightAnswer(selectedAnswer, Color.RED);
                // Highlight the correct answer button in green
                highlightAnswer(correctAnswer, Color.GREEN);
            }
            currentQuestionIndex++;
            // Delay loading the next question to allow user to see answer feedback
            submitBtn.setEnabled(false); // Disable submit button temporarily
            submitBtn.postDelayed(() -> {
                clearButtonColors(); // Clear button colors after delay
                loadNewQuestion(); // Load next question after delay
                submitBtn.setEnabled(true); // Re-enable submit button
            }, 2000); // Delay in milliseconds (2 seconds)
        } else {
            finishQuiz();
        }
    }

    private void highlightAnswer(String answer, int color) {
        // Determine which button corresponds to the provided answer text
        Button answerButton = getButtonForAnswer(answer);
        if (answerButton != null) {
            // Update the button's background color immediately
            answerButton.setBackgroundColor(color);
        }
    }

    private void clearButtonColors() {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
    }

    private Button getButtonForAnswer(String answer) {
        // Determine which button corresponds to the provided answer text
        if (answer.equals(ansA.getText().toString())) {
            return ansA;
        } else if (answer.equals(ansB.getText().toString())) {
            return ansB;
        } else if (answer.equals(ansC.getText().toString())) {
            return ansC;
        } else if (answer.equals(ansD.getText().toString())) {
            return ansD;
        }
        return null; // Return null if no matching button is found
    }

    private void finishQuiz() {
        // Determine pass status based on score percentage
        String passStatus = (score > totalQuestion * 0.60) ? "Passed" : "Failed";

        // Create intent to launch ScoreActivity
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        intent.putExtra("PLAYER_NAME", getIntent().getStringExtra("userName")); // Pass player name
        intent.putExtra("SCORE", score); // Pass final score
        startActivity(intent);

        // Finish MainActivity
        finish();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_QUIZ) {
//            if (resultCode == RESULT_OK) {
//                boolean restartQuiz = data.getBooleanExtra("RESTART_QUIZ", false);
//                if (restartQuiz) {
//                    score = 0;
//                    currentQuestionIndex = 0;
//                    loadNewQuestion();
//                } else {
//                    finish(); // Close the MainActivity
//                }
//            }
//        }
//    }


    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        selectedAnswer = "";
        clearButtonColors();
        loadNewQuestion();
    }
}
