package SolitaireGUI;

/**
 *
 * @author Ethan Smith [21153581]
 */
public class Highscore {
    
    private final String name;
    private final int score;
    
    public Highscore(String name, int score) {
        
        this.name = name;
        this.score = score;
        
    }
    
    public String getName() {
        
        return this.name;
        
    }
    
    public int getScore() {
        
        return this.score;
        
    }
    
}
