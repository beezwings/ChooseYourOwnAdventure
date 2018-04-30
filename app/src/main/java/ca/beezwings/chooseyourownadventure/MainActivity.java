package ca.beezwings.chooseyourownadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Buttons, TextView, and ImageView initialization
    public TextView storyDisplay;
    public Button choiceLeft;
    public Button choiceRight;
    public ImageView storyImage;
    public ScrollView scrollTop;

    // Creates the array of adventures (stories and endings)
    // Each adventure is an object of the "Adventure" class
    Adventure[] adventures= new Adventure[] {
            new Adventure(R.string.opening_story, R.string.choice_1, R.string.choice_2, 1, 2, R.drawable.glasses),
            new Adventure(R.string.story_1, R.string.choice_3, R.string.choice_4,3,4, R.drawable.capebreton),
            new Adventure(R.string.story_2, R.string.choice_5, R.string.choice_6,5, 6, R.drawable.food),
            new Adventure(R.string.story_3, R.string.choice_7, R.string.choice_8,7,8, R.drawable.mtt),
            new Adventure(R.string.story_4, R.string.choice_9, R.string.choice_10,10,9, R.drawable.moneypoint),
            new Adventure(R.string.story_5, R.string.choice_11, R.string.choice_12,12,13, R.drawable.snow),
            new Adventure(R.string.story_6, R.string.choice_13, R.string.choice_14, 10, 11, R.drawable.fire),
            new Adventure(R.string.ending_1, R.string.choice_15, 0, R.drawable.lightning, true),
            new Adventure(R.string.ending_2, R.string.choice_15, 0,R.drawable.woman, true),
            new Adventure(R.string.ending_3, R.string.choice_15, 0,R.drawable.rainbows, true),
            new Adventure(R.string.ending_4, R.string.choice_15, 0,R.drawable.huh, true),
            new Adventure(R.string.ending_5, R.string.choice_15, 0,R.drawable.race, true),
//            new Adventure(R.string.ending_6, R.string.choice_15, 0,R.drawable.fireplace, true),
            new Adventure(R.string.ending_6, R.string.choice_15, 0,R.drawable.kids, true),
            new Adventure(R.string.ending_7, R.string.choice_15, 0,R.drawable.teahouse, true)
    };

    // Sets the adventure index to the first initial story
    int adventureIndex = 0;
    Adventure currentAdventure = adventures[adventureIndex];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adventures begin!

        // Displayed buttons and views
        storyDisplay = findViewById(R.id.storyDisplay);
        choiceLeft = findViewById(R.id.optionLeft);
        choiceRight = findViewById(R.id.optionRight);
        storyImage = findViewById(R.id.adventureImage);
        scrollTop = findViewById(R.id.scrollView);


        // Makes sure that if rotated, the state is saved
        // Note, if it's an ending, app still crashes on rotate (GONE/VISIBLE toggle?)
        if(savedInstanceState != null) {
            currentAdventure = adventures[savedInstanceState.getInt("CurrentIndex")];
        } else {
            adventureIndex = 0;
            currentAdventure = adventures[0];
        }

        // Sets the current story and buttons and images
        // (For saved state) if it isn't an ending, set the right button text
        if (!currentAdventure.isEnding()) {
            choiceRight.setText(currentAdventure.getChoice2());
        }
        // If it is an ending, makes sure to keep that right button hidden!
        if (currentAdventure.isEnding()) {
            choiceRight.setVisibility(View.GONE);

        }
        // Sets the text, left button text, and story image
        storyDisplay.setText(currentAdventure.getStory());
        choiceLeft.setText(currentAdventure.getChoice1());
        storyImage.setImageResource(currentAdventure.getStoryImage());


        // Runs the chooseAdventure function (see below onCreate) for the left button
        choiceLeft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // function goes here
                chooseAdventure("choiceLeft");
            }
        });

        // Runs the chooseAdventure function (see below onCreate) for the right button
        choiceRight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // function goes here
                chooseAdventure("choiceRight");
            }
        });

    }

    // Saved state function
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentIndex", adventureIndex);
    }


    // Function that allows progression of adventure
    public void chooseAdventure(String choice){

        if (choice == "choiceLeft"){
            Toast toastMessage = Toast.makeText(getApplicationContext(), currentAdventure.getChoice1(), Toast.LENGTH_SHORT);
            toastMessage.show();
            // Goes to the adventure index that's set to choice1
            adventureIndex = currentAdventure.getChoice1index();
        }

        if (choice == "choiceRight"){
            Toast toastMessage = Toast.makeText(getApplicationContext(), currentAdventure.getChoice2(), Toast.LENGTH_SHORT);
            toastMessage.show();
            // Goes to the adventure index that's set to choice2
            adventureIndex = currentAdventure.getChoice2index();
        }

        // What happens when you get to the end, ie is.Ending()== true? Reset and play again!
        if (choice == "choiceLeft" && currentAdventure.isEnding()){
            // Go back to the beginning and
            adventureIndex = 0;
            choiceRight.setVisibility(View.VISIBLE); // Sets the right button to visible again.
        }

        currentAdventure = adventures[adventureIndex];  // Based on adventureIndex above, the currentAdventure is set
        storyDisplay.setText(currentAdventure.getStory()); // Based on current adventure, the current story is set
        choiceLeft.setText(currentAdventure.getChoice1());  // Based on current adventure, the current left button is set
        storyImage.setImageResource(currentAdventure.getStoryImage()); // Based on current adventure, the current image is set
        scrollTop.scrollTo(0,0); //Brings the view back to the top

        // If it's an ending adventure, it hides the right button. Otherwise, it updates right button text based on current adventure
        if (currentAdventure.isEnding()) {
            choiceRight.setVisibility(View.GONE);

        } else {
            choiceRight.setText(currentAdventure.getChoice2());
        }




    }
}
