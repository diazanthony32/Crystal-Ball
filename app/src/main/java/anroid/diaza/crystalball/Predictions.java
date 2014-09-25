package anroid.diaza.crystalball;

import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {

        // The selection of random predictions the app shall "predict"
        answers = new String[] {
            "Your Wishes Will Come True",
            "Your Wishes Will NEVER Come True",
            "Only If you Believe So",
            "Only If you Try",
            "No, Just No",
            "You Don't Need That",
            "Ask Your Mother",
            "Don't Ask Me I'm Just A Ball",
            "I Don't Know",
            "To Answer That I would Need .99 cents",
            "Why Not?",
            "42",
            "21",
            "Maybe...",
            "That Question Is Too Difficult For Me",
            "Why You Gotta And Make Things So Complicated",
            "IDK",
            "Well, That's All I Got",
            "No, I Wont Help You",
            "Id Rather Not Answer That",
            "Find Out For Yourself",
            "IDC"

        };
    }

    //checks if the predictions variable is set to null
    public static Predictions get(){
        if(predictions == null){
            predictions = new Predictions();
        }
        return predictions;
    }

    // Sends back the "prediction" to the CrystalBall.java
    public String getPredictions() {

        //Generates a random number to select an answer from
        Random prediction = new Random();

        return answers[prediction.nextInt(22)];
    }

}
