package anroid.diaza.crystalball;

import java.util.Random;

public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {
        // The selection of random predictions the app shall "predict"
        answers = new String[] {
            "Your wishes will come true",
            "Your wishes will NEVER come true.",
            "Your mama said so...",
            "That's what She said"
        };
    }

    public static Predictions get(){
        if(predictions == null){
            predictions = new Predictions();
        }
        return predictions;
    }

    // Sends back the "prediction" to the CrystalBall.java
    public String getPredictions() {

        Random prediction = new Random();

        return answers[prediction.nextInt(4)];
    }

}
