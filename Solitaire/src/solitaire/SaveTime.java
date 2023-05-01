
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

public class SaveTime 
{

    private static final String FILENAME = "times.txt";
    private static final int MAX_TIMES = 10;

    public static void saveTime(String playerName, long elapsedTime)  
    {

        ArrayList<TimeRecord> times = loadTimes();

        if (times.size() >= MAX_TIMES && elapsedTime >= times.get(times.size() - 1).elapsedTime) 
        {
            System.out.println("\nYour time was not fast enough to make the leaderboard!");
            return;
        }

        times.add(new TimeRecord(playerName, elapsedTime));
        Collections.sort(times);

        if (times.size() > MAX_TIMES) 
        {
            times.subList(MAX_TIMES, times.size()).clear();
        }

        try 
        {
            FileWriter writer = new FileWriter(FILENAME);
            for (TimeRecord time : times) 
            {
                writer.write(time.playerName + "," + time.elapsedTime + "\n");
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void displayTimes() 
    {

        ArrayList<TimeRecord> times = loadTimes();

        System.out.println("Top 10 times:");

        int count = 0;

        for (int i = 0; i < times.size(); i++) 
        {

            if (count >= MAX_TIMES) 
            {
                break;
            }

            TimeRecord time = times.get(i);
            long minutes = time.elapsedTime / 60;
            time.elapsedTime = time.elapsedTime % 60;
            
            long hours = minutes / 60;
            minutes = minutes % 60;
            
            if (minutes == 0) {
                
                System.out.println(time.playerName + ": " + time.elapsedTime + " seconds");
                
            } else if (hours == 0) {
                
                System.out.println(time.playerName + ": " + minutes + " minutes, " + time.elapsedTime + " seconds");
                
            } else {
                
                System.out.println(time.playerName + ": " + hours + " hours, " + minutes + " minutes, "+ time.elapsedTime + " seconds");
                
            }
            
            count++;
        }
    }

    private static ArrayList<TimeRecord> loadTimes() 
    {

        ArrayList<TimeRecord> times = new ArrayList<>();

        try 
        {
            File file = new File(FILENAME);
            if (!file.exists()) 
            {
                return times;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length == 2) 
                {
                    String playerName = parts[0];
                    long elapsedTime = Long.parseLong(parts[1]);
                    times.add(new TimeRecord(playerName, elapsedTime));
                }
            }
            scanner.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return times;
    }

    private static class TimeRecord implements Comparable<TimeRecord> 
    {
        public String playerName;
        public long elapsedTime;

        public TimeRecord(String playerName, long elapsedTime) 
        {
            this.playerName = playerName;
            this.elapsedTime = elapsedTime;
        }

        @Override
        public int compareTo(TimeRecord other) 
        {
            return Long.compare(this.elapsedTime, other.elapsedTime);
        }
    }

}