package css.cis3334roomrbheartrate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    EditText editTextPulse;
    EditText editTextAge;
    EditText editTextDisplay;
    Button buttonInsert;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAge = findViewById(R.id.editTextAge);
        editTextPulse = findViewById(R.id.editTextPulse);
        editTextDisplay = findViewById(R.id.editTextDisplay);
        buttonInsert = findViewById(R.id.buttonInsert);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
                Integer age = Integer.parseInt(editTextAge.getText().toString());
                mainViewModel.insert(pulse, age);
                String strRates = mainViewModel.getHeartratesAsString();
                editTextDisplay.setText(strRates);
            }
        });

    }
}