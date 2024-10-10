package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView playerChoiceImg;
    private ImageView botChoiceImg;
    private int playerChoice = 0;
    private int playerPoints = 0;
    private int playerHp = 3;
    private int botChoice = 0;
    private int botPoints = 0;
    private int botHp = 3;
    private Random rand;
    private TextView standingTextView;
    private int playerStanding = 0;
    private int botStanding = 0;
    private int drawRounds = 0;
    private TextView drawRoundsTextView;
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private ImageView pHeart1;
    private ImageView pHeart2;
    private ImageView pHeart3;
    private ImageView bHeart1;
    private ImageView bHeart2;
    private ImageView bHeart3;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        rockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                playerChoice = 0;
                playerChoiceImg.setImageResource(R.drawable.rock);
                botChoice = rand.nextInt(3);
                botDisplay(botChoice);
                logic(playerChoice, botChoice);
                hpChange();
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                playerChoice = 1;
                playerChoiceImg.setImageResource(R.drawable.paper);
                botChoice = rand.nextInt(3);
                botDisplay(botChoice);
                logic(playerChoice, botChoice);
                hpChange();
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                playerChoice = 2;
                playerChoiceImg.setImageResource(R.drawable.scissors);
                botChoice = rand.nextInt(3);
                botDisplay(botChoice);
                logic(playerChoice, botChoice);
                hpChange();
            }
        });
    }

    public void logic(int pC, int bC){
        if (pC == 0 && bC == 1){
            botPoints++;
            playerHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 0 && bC == 2){
            playerPoints++;
            botHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 0 && bC == 0){
            drawRounds++;
            drawRoundsTextView.setText("Döntetlenek: " + drawRounds);
            standingTextView.setText(botPoints + " : " + playerPoints);
        }
        if (pC == 1 && bC == 0){
            playerPoints++;
            botHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 1 && bC == 2){
            botPoints++;
            playerHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 1 && bC == 1){
            drawRounds++;
            drawRoundsTextView.setText("Döntetlenek: " + drawRounds);
            standingTextView.setText(botPoints + " : " + playerPoints);
        }
        if (pC == 2 && bC == 0){
            botPoints++;
            playerHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 2 && bC == 1){
            playerPoints++;
            botHp--;
            standingTextView.setText(botPoints + " : " + playerPoints);
        } else if (pC == 2 && bC == 2){
            drawRounds++;
            drawRoundsTextView.setText("Döntetlenek: " + drawRounds);
            standingTextView.setText(botPoints + " : " + playerPoints);
        }
    }

    public void hpChange(){
        if (playerHp == 2){
            pHeart3.setImageResource(R.drawable.heart1);
        } if (playerHp == 1){
            pHeart2.setImageResource(R.drawable.heart1);
        } if (playerHp == 0){
            pHeart1.setImageResource(R.drawable.heart1);
            Toast.makeText(MainActivity.this, "Vesztettél...", Toast.LENGTH_SHORT).show();
            alertDialog.show();
        }
        if (botHp == 2){
            bHeart3.setImageResource(R.drawable.heart1);
        } if (botHp == 1){
            bHeart2.setImageResource(R.drawable.heart1);
        } if (botHp == 0){
            bHeart1.setImageResource(R.drawable.heart1);
            Toast.makeText(MainActivity.this, "Nyertél!", Toast.LENGTH_SHORT).show();
            alertDialog.show();
        }
    }

    private void botDisplay(int choice) {
        if (choice == 0) {
            botChoiceImg.setImageResource(R.drawable.rock);
        } else if (choice == 1) {
            botChoiceImg.setImageResource(R.drawable.paper);
        } else if (choice == 2) {
            botChoiceImg.setImageResource(R.drawable.scissors);
        }
    }

    public void resetPlayers(){
        playerPoints = 0;
        botPoints = 0;
        playerHp = 3;
        botHp = 3;
        drawRounds = 0;
        standingTextView.setText(botPoints + " : " + playerPoints);
        drawRoundsTextView.setText("Döntetlenek: " + drawRounds);
        playerChoiceImg.setImageResource(R.drawable.rock);
        botChoiceImg.setImageResource(R.drawable.rock);
        pHeart1.setImageResource(R.drawable.heart2);
        pHeart2.setImageResource(R.drawable.heart2);
        pHeart3.setImageResource(R.drawable.heart2);
        bHeart1.setImageResource(R.drawable.heart2);
        bHeart2.setImageResource(R.drawable.heart2);
        bHeart3.setImageResource(R.drawable.heart2);
    }

    public void init() {
        playerChoiceImg = findViewById(R.id.playerChoiceImg);
        botChoiceImg = findViewById(R.id.botChoiceImg);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        rand = new Random();
        standingTextView = findViewById(R.id.standingTextView);
        drawRoundsTextView = findViewById(R.id.drawRoundsTextView);
        pHeart1 = findViewById(R.id.pHeart1);
        pHeart2 = findViewById(R.id.pHeart2);
        pHeart3 = findViewById(R.id.pHeart3);
        bHeart1 = findViewById(R.id.bHeart1);
        bHeart2 = findViewById(R.id.bHeart2);
        bHeart3 = findViewById(R.id.bHeart3);
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Játék vége")
                .setMessage("Szeretnél új játékot?")
                .setCancelable(false)
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetPlayers();
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
    }
}
