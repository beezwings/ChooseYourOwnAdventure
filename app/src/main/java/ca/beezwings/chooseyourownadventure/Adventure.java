package ca.beezwings.chooseyourownadventure;

/**
 * Created by beezw on 2018-03-20.
 */

public class Adventure{

    private int story;
    private int choice1;
    private int choice2;
    private int choice1index;
    private int choice2index;
    private int storyImage;
    private boolean isEnding;

    //For adventures with all the options
    public Adventure(int story, int choice1, int choice2, int choice1index, int choice2index, int storyImage) {
        this.story = story;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice1index = choice1index;
        this.choice2index = choice2index;
        this.storyImage = storyImage;
    }

    //For endings, ie adventures with only choice to play again
    public Adventure(int story, int choice1, int choice1index, int storyImage, boolean isEnding) {
        this.story = story;
        this.choice1 = choice1;
        this.choice1index = choice1index;
        this.storyImage = storyImage;
        this.isEnding = isEnding;
    }

    // Getter Functions
    public int getStory() {
        return story;
    }

    public int getChoice1() {
        return choice1;
    }

    public int getChoice2() {
        return choice2;
    }

    public int getStoryImage() {
        return storyImage;
    }

    public int getChoice1index() {
        return choice1index;
    }

    public int getChoice2index() {
        return choice2index;
    }

    public boolean isEnding() {
        return isEnding;
    }
}
