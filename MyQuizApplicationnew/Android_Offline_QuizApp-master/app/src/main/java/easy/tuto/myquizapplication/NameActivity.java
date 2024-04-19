package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {

    EditText nameEditText;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameEditText = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();
                if (!userName.isEmpty()) {
                    // Start MainActivity and pass the user's name
                    Intent intent = new Intent(NameActivity.this, MainActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish(); // Finish this activity to prevent going back to it
                }
            }
        });
    }
}
