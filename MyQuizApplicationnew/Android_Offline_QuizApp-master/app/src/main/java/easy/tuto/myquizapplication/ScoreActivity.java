package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTextView;
    TextView playerNameTextView;
    Button takeNewQuizButton;
    Button finishButton;

    String playerName;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get player name and score from intent extras
        playerName = getIntent().getStringExtra("PLAYER_NAME");
        score = getIntent().getIntExtra("SCORE", 0);

        // Initialize views
        playerNameTextView = findViewById(R.id.player_name_textview);
        scoreTextView = findViewById(R.id.score_textview);
        takeNewQuizButton = findViewById(R.id.take_new_quiz_button);
        finishButton = findViewById(R.id.finish_button);

        // Set player name and score on text views
        playerNameTextView.setText("Hello, " + playerName + "!");
        scoreTextView.setText("Your final score is: " + score + "/" + QuestionAnswer.question.length);

        // Set click listeners for buttons
        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewQuiz();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishApp();
            }
        });
    }

    private void startNewQuiz() {
        // Start MainActivity to begin a new quiz
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
        intent.putExtra("userName", playerName); // Pass player name to MainActivity
        startActivity(intent);
        finish(); // Finish ScoreActivity
    }

    private void finishApp() {
        // Close the app
        finishAffinity(); // Finish all activities in the task
    }
}
