package solitaire;


/**
 *
 * @author Trey Baker [21155292]
 */
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

    public static void saveHighscore(String playerName, int score) 
    {
        ArrayList<Highscore> highscores = loadHighscores();
        
        boolean updated = false;
        for (int i = 0; i < highscores.size(); i++) 
        {
            Highscore highscore = highscores.get(i);
            if (highscore.playerName.equals(playerName)) 
            {
                if (score > highscore.score) 
                {
                    highscore.score = score;
                    updated = true;
                }
                break;
            }
        }
        
        if (!updated) 
        {
            highscores.add(new Highscore(playerName, score));
        }
        
        Collections.sort(highscores);
        
        if (highscores.size() > MAX_HIGHSCORES) 
        {
            highscores.subList(MAX_HIGHSCORES, highscores.size()).clear();
        }

        try 
        {
            FileWriter writer = new FileWriter(FILENAME);
            for (Highscore highscore : highscores) {
                writer.write(highscore.playerName + "," + highscore.score + "\n");
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        if (updated) 
        {
            System.out.println("Score updated!");
        }
    }

    public static void displayHighscores() 
    {
        ArrayList<Highscore> highscores = loadHighscores();

        System.out.println("High scores:");
        
        int count = 0;
        
        for (Highscore highscore : highscores)
        {
            System.out.println(highscore);
            count++;
            if (count == MAX_HIGHSCORES) 
            {
                break;
            }
        }
    }
    
    private static ArrayList<Highscore> loadHighscores() {
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

    private static class Highscore implements Comparable<Highscore> 
    {
        private String playerName;
        private int score;

        public Highscore(String playerName, int score) 
        {
            this.playerName = playerName;
            this.score = score;
        }

        @Override
        public int compareTo(Highscore o) 
        {
            return Integer.compare(o.score, this.score);
        }

        @Override
        public String toString() 
        {
            return playerName + ": " + score;
        }
    }
}
