package za.co.k2.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import za.co.k2.myapplication.controllers.MainController;

public class MainActivity extends AppCompatActivity {
    private MainController mController;
    private TextView totalPoints;
    boolean backDoorActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = new MainController(this);

        totalPoints = (TextView) findViewById(R.id.totalPoints);

    }

    public void bowlBall(View view) {
        mController.ballWasBowled();
    }

    public void resetClicked(View view) {
        mController.resetClicked();
    }

    public void updateTotalScore(int pointCount) {
        totalPoints.setText(String.format(Locale.US, "%d Points", pointCount));
    }

    public void setFrameAndTry(String pinCountString, int frameNumber, int tryNumber) {
        ((TextView)findViewById(getResources().getIdentifier(
                "f" + frameNumber + "b" + tryNumber, "id", getPackageName()))).setText(pinCountString);
    }

    public void setFrameTotal(String scoreText, int frameNumber) {
        ((TextView)findViewById(getResources().getIdentifier(
                "f" + frameNumber + "total", "id", getPackageName()))).setText(scoreText);
    }

    public void bowledOne(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(1);
        }
    }

    public void bowledTwo(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(2);
        }
    }

    public void bowledThree(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(3);
        }
    }

    public void bowledFour(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(4);
        }
    }

    public void bowledFive(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(5);
        }
    }

    public void bowledSix(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(6);
        }
    }

    public void bowledSeven(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(7);
        }
    }

    public void bowledEight(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(8);
        }
    }

    public void bowledNine(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(9);
        }
    }

    public void bowledTen(View view) {
        if(backDoorActive) {
            mController.bowledPinsDown(10);
        }
    }

    public void showScore(int playerTotalScore) {
        findViewById(R.id.dimmer).setVisibility(View.VISIBLE);
        findViewById(R.id.boom).setVisibility(View.VISIBLE);
        findViewById(R.id.finalScore).setVisibility(View.VISIBLE);
        findViewById(R.id.playAgainButton).setVisibility(View.VISIBLE);

        ((TextView)findViewById(R.id.finalScore)).setText(String.format(Locale.US, "Congratulations!!\n%d Points", playerTotalScore));
    }

    public void doNothing(View view) {
        // doing nothing
    }

    public void playAgain(View view) {
        findViewById(R.id.dimmer).setVisibility(View.INVISIBLE);
        findViewById(R.id.boom).setVisibility(View.INVISIBLE);
        findViewById(R.id.finalScore).setVisibility(View.INVISIBLE);
        findViewById(R.id.playAgainButton).setVisibility(View.INVISIBLE);

        resetClicked(view);
    }
}
