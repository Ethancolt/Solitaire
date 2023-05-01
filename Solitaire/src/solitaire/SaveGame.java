package solitaire;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SaveGame 
{

    private static final String FILENAME = "highscores.txt";
    private static final int MAX_HIGHSCORES = 10;

    public static void saveHighscore(String playerName, int score) //Handles the Writing of New High Scores
    {
        ArrayList<Highscore> highscores = loadHighscores();
        boolean updated = false;
        boolean added = false;
        boolean found = false;
        Highscore existingHighscore = null;

        // Check if score is within the top 10 high scores
        if (highscores.size() < MAX_HIGHSCORES || score > highscores.get(MAX_HIGHSCORES-1).score) 
        {
            for (int i = 0; i < highscores.size(); i++) 
            {
                Highscore highscore = highscores.get(i);
                if (highscore.playerName.equals(playerName)) 
                {
                    if (score > highscore.score) 
                    {
                        highscore.score = score;
                        updated = true;
                        found = true;
                    }
                    existingHighscore = highscore;
                    break;
                }
                if (score > highscore.score && !added) 
                {
                    highscores.add(i, new Highscore(playerName, score));
                    added = true;
                }   
            }
            if (!updated && !added && !found) 
            {
                if (highscores.size() < MAX_HIGHSCORES) 
                {
                    highscores.add(new Highscore(playerName, score));
                    added = true;
                }
            }
            if (updated) 
            {
                System.out.println("Score updated!");
            } 
            else if (added) 
            {
                System.out.println("Score saved!");
            } 
            else 
            {   
                System.out.println("Score not saved.");
                return;
            }
        
            Collections.sort(highscores);
        
            if (highscores.size() > MAX_HIGHSCORES) 
            {
                highscores.subList(MAX_HIGHSCORES, highscores.size()).clear();
            }
            
            // Remove any duplicates with a lower score
            if (existingHighscore != null) 
            {
                boolean updatedExisting = false;
                for (int i = 0; i < highscores.size(); i++) 
                {
                    Highscore highscore = highscores.get(i);
                    if (highscore.playerName.equals(playerName) && highscore.score < existingHighscore.score) 
                    {
                        highscores.remove(i);
                        break;
                    } 
                    else if (highscore.playerName.equals(playerName) && highscore.score > existingHighscore.score) 
                    {
                        updatedExisting = true;
                        break;
                    }
                }
                if (updatedExisting) 
                {
                    highscores.remove(existingHighscore);
                }
            }
            try 
            {
                FileWriter writer = new FileWriter(FILENAME);
                for (Highscore highscore : highscores) 
                {
                    writer.write(highscore.playerName + "," + highscore.score + "\n");
                }
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 
        else 
        {
            System.out.println("Score not saved. It is not within the top 10 high scores.");
        }
    }




    public static void displayHighscores() //Method that Displays the High Scores
    {

        ArrayList<Highscore> highscores = loadHighscores();

        System.out.println("High scores:");

        int count = 0;

        for (int i = 0; i < highscores.size(); i++) 
        {

            if (count >= MAX_HIGHSCORES) 
            {
                break;
            }   

            Highscore highscore = highscores.get(i);
            System.out.println(highscore);
            count++;
        }
    }
    
    private static ArrayList<Highscore> loadHighscores() // Handles Reading of High Scores for the Display
    {

        ArrayList<Highscore> highscores = new ArrayList<>();

        try 
        {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) 
            {

                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                Highscore highscore = new Highscore(playerName, score);
                highscores.add(highscore);
            }

            scanner.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return highscores;
    }

    private static class Highscore implements Comparable<Highscore> //Nested Class for the Highscore object
    {

        private String playerName;
        private int score;

        public Highscore(String playerName, int score) //Highscore Constructor
        {
            this.playerName = playerName;
            this.score = score;
        }

        @Override
        public int compareTo(Highscore o) //Compares two Highscores
        {
            return Integer.compare(o.score, this.score);
        }

        @Override
        public String toString() //HighScore ToString Method
        {
            return playerName + ": " + score;
        }
    }
}
