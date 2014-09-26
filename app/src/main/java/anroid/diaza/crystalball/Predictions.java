package anroid.diaza.crystalball;

import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {

        // The selection of random predictions the app shall "predict"
        answers = new String[] {
            "Only If you Believe So",
            "Only If you Try",
            "No, Just No",
            "You Don't Need That",
            "Ask Your Mother",
            "Don't Ask Me I'm Just A Plumber",
            "I Don't Know",
            "Oh, No",
            "Why Not?",
            "Check The Other Castle",
            "Ask Luigi...",
            "Maybe...",
            "That Question Is Too Difficult For Me",
            "First Help Me Find Peach",
            "My Stache Says Yes",
            "Well, That's All I Got",
            "No, I Wont Help You",
            "Id Rather Not Answer That",
            "Find Out For Yourself",
            "Its A Me, Mario"

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
