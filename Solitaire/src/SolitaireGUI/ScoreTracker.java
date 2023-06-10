/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolitaireGUI;

/**
 *
 * @author Itstr
 */
public class ScoreTracker {
    private  int score = 1000;
    private  boolean stopDecrement = false;



    public void startCountdown() {
        while (score > 0 && !stopDecrement) {
            System.out.println(score);
            score--;
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopCountdown() 
    {
        stopDecrement = true;
    }
    
    public void setStopDecrement(boolean isGameOver)
    {
        this.stopDecrement = isGameOver;
    }
    
    public void resetScore()
    {
       this.score = 1000;
    }
}
