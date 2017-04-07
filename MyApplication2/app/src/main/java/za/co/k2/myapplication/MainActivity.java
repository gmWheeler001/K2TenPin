package za.co.k2.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import za.co.k2.myapplication.controllers.MainController;
import za.co.k2.myapplication.models.Player;

public class MainActivity extends AppCompatActivity {
    private MainController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = new MainController();

    }

    public void bowlBall(View view) {
        mController.ballWasBowled();
    }

    public void resetClicked(View view) {
        mController.resetClicked();
    }
}
