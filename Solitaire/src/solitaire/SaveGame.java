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
 * @author Ethan Smith [21153581]
 */
public class SaveGame {
    
    public int save() {
        
        PrintWriter output = null;
        
        try {
            
            output = new PrintWriter(new FileOutputStream("savefile.txt"));
            
            
            
        } catch (FileNotFoundException e) {
            
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, e);
            
        } catch (IOException e) {
            
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, e);
            
        } finally {
        }
        
        return -1;
        
    }
    
    public int load() {
        
        BufferedReader input = null;
        
        return -1;
        
    }
    
}
