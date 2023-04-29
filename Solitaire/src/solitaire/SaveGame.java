package solitaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try 
        {
            File file = new File(FILENAME);
            FileWriter writer = new FileWriter(file, true);
            writer.write(playerName + "," + score + "\n");
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void displayHighscores() 
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

        Collections.sort(highscores);
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

