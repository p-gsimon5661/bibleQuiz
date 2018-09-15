package com.example.android.biblequiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    int questionNumber = 1;

    TextView q1;
    TextView q2;
    TextView q3;
    TextView q4;
    TextView q5;
    EditText q1Ans;
    RadioGroup q2Ans;
    RelativeLayout q3Ans;
    ToggleButton q4Ans;
    RadioGroup q5Ans;
    Context context;
    String q1Answer;
    String q2Answer;
    String q3Answer;
    String q4Answer;
    String q5Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.back_button).setEnabled(false);
        findViewById(R.id.next_button).setEnabled(false);
        findViewById(R.id.grade_button).setEnabled(false);

        //to prevent crashing for early grading
        q1Ans = findViewById(R.id.q1_ans);
        q1 = findViewById(R.id.q1);
        q2Ans = findViewById(R.id.q2_ans);
        q2 = findViewById(R.id.q2);
        q3Ans = findViewById(R.id.q3_ans);
        q3 = findViewById(R.id.q3);
        q4Ans = findViewById(R.id.q4_ans);
        q4 = findViewById(R.id.q4);
        q5Ans = findViewById(R.id.q5_ans);
        q5 = findViewById(R.id.q5);
        context = getApplicationContext();
    }

    /**
     * This method starts the quiz and gets the ball rolling
     *
     * @param view view
     */
    public void startButtonClick(View view) {
        Button StartBtn = findViewById(R.id.startGame_button);
        StartBtn.setVisibility(View.GONE);

        q1.setVisibility(View.VISIBLE);
        q1Ans.setVisibility(View.VISIBLE);

        findViewById(R.id.back_button).setEnabled(true);
        findViewById(R.id.next_button).setEnabled(true);
        findViewById(R.id.grade_button).setEnabled(true);
    }

    /**
     * This method goes to the next question
     *
     * @param view view
     */
    public void nextButtonClick(View view) {
        if (questionNumber < 5) {
            questionNumber += 1;

            if (questionNumber == 2) {
                q1.setVisibility(View.GONE);
                q1Ans.setVisibility(View.GONE);

                q2.setVisibility(View.VISIBLE);
                q2Ans.setVisibility(View.VISIBLE);
            } else if (questionNumber == 3) {
                q2.setVisibility(View.GONE);
                q2Ans.setVisibility(View.GONE);

                q3.setVisibility(View.VISIBLE);
                q3Ans.setVisibility(View.VISIBLE);
            } else if (questionNumber == 4) {
                q3.setVisibility(View.GONE);
                q3Ans.setVisibility(View.GONE);

                q4.setVisibility(View.VISIBLE);
                q4Ans.setVisibility(View.VISIBLE);
            } else if (questionNumber == 5) {
                q4.setVisibility(View.GONE);
                q4Ans.setVisibility(View.GONE);

                q5.setVisibility(View.VISIBLE);
                q5Ans.setVisibility(View.VISIBLE);
            }
        } else {
            CharSequence msg = getString(R.string.lq);
            int duration = Toast.LENGTH_SHORT;

            Toast toastLastQuestion = Toast.makeText(context, msg, duration);
            toastLastQuestion.show();
        }
    }

    /**
     * This method go the the previous question
     *
     * @param view view
     */
    public void backButtonClick(View view) {
        if (questionNumber > 1) {
            questionNumber -= 1;

            if (questionNumber == 1) {
                q2.setVisibility(View.GONE);
                q2Ans.setVisibility(View.GONE);

                q1.setVisibility(View.VISIBLE);
                q1Ans.setVisibility(View.VISIBLE);
            }
            if (questionNumber == 2) {
                q3.setVisibility(View.GONE);
                q3Ans.setVisibility(View.GONE);

                q2.setVisibility(View.VISIBLE);
                q2Ans.setVisibility(View.VISIBLE);
            } else if (questionNumber == 3) {
                q4.setVisibility(View.GONE);
                q4Ans.setVisibility(View.GONE);

                q3.setVisibility(View.VISIBLE);
                q3Ans.setVisibility(View.VISIBLE);
            } else if (questionNumber == 4) {
                q5.setVisibility(View.GONE);
                q5Ans.setVisibility(View.GONE);

                q4.setVisibility(View.VISIBLE);
                q4Ans.setVisibility(View.VISIBLE);
            }
        } else {
            CharSequence msg = getString(R.string.fq);
            int duration = Toast.LENGTH_SHORT;

            Toast toastFirstQuestion = Toast.makeText(context, msg, duration);
            toastFirstQuestion.show();
        }
    }

    /**
     * This method calculate the grade
     *
     * @param view view
     */
    public void gradeButtonClick(View view) {
        int numberOfCorrectAnswer = 0;

        q1Answer = q1Ans.getText().toString();
        q2Answer = String.valueOf(q2Ans.indexOfChild(findViewById(q2Ans.getCheckedRadioButtonId())));
        q5Answer = String.valueOf(q5Ans.indexOfChild(findViewById(q5Ans.getCheckedRadioButtonId())));

        CheckBox opt = findViewById(R.id.q2_opt);
        CheckBox opt1 = findViewById(R.id.q2_opt1);
        CheckBox opt2 = findViewById(R.id.q2_opt2);
        CheckBox opt3 = findViewById(R.id.q2_opt3);

        if (opt.isChecked() && opt1.isChecked() && opt3.isChecked() && !opt2.isChecked())
            q3Answer = "correct";
        else
            q3Answer = "incorrect";

        if (q4Ans.isChecked())
            q4Answer = "correct";
        else
            q4Answer = "incorrect";

        if (q1Answer.equalsIgnoreCase("500"))
            numberOfCorrectAnswer += 1;
        if (q2Answer.equalsIgnoreCase("1"))
            numberOfCorrectAnswer += 1;
        if (q3Answer.equalsIgnoreCase("correct"))
            numberOfCorrectAnswer += 1;
        if (q4Answer.equalsIgnoreCase("correct"))
            numberOfCorrectAnswer += 1;
        if (q5Answer.equalsIgnoreCase("1"))
            numberOfCorrectAnswer += 1;

        CharSequence msg = getString(R.string.cm, numberOfCorrectAnswer);
        int duration = Toast.LENGTH_LONG;

        Toast toastFirstQuestion = Toast.makeText(context, msg, duration);
        toastFirstQuestion.show();
        reset();
    }

    /**
     * This method resets the quiz to starting values
     */
    private void reset() {
        Button StartBtn = findViewById(R.id.startGame_button);
        StartBtn.setVisibility(View.VISIBLE);

        questionNumber = 1;

        q1.setVisibility(View.GONE);
        q1Ans.setText("");
        q1Ans.setVisibility(View.GONE);

        q2.setVisibility(View.GONE);
        q2Ans.clearCheck();
        q2Ans.setVisibility(View.GONE);

        q3.setVisibility(View.GONE);
        CheckBox opt = findViewById(R.id.q2_opt);
        CheckBox opt1 = findViewById(R.id.q2_opt1);
        CheckBox opt2 = findViewById(R.id.q2_opt2);
        CheckBox opt3 = findViewById(R.id.q2_opt3);
        opt.setChecked(false);
        opt1.setChecked(false);
        opt2.setChecked(false);
        opt3.setChecked(false);
        q3Ans.setVisibility(View.GONE);

        q4.setVisibility(View.GONE);
        q4Ans.setChecked(false);
        q4Ans.setVisibility(View.GONE);

        q5.setVisibility(View.GONE);
        q5Ans.clearCheck();
        q5Ans.setVisibility(View.GONE);

        findViewById(R.id.back_button).setEnabled(false);
        findViewById(R.id.next_button).setEnabled(false);
        findViewById(R.id.grade_button).setEnabled(false);
    }
}