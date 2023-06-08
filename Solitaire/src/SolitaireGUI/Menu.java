
package SolitaireGUI;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trey Baker[21155292]
 */
public class Menu implements Runnable{
    private int size;
    
    private MenuOption[] options;
    
    private boolean isActive;
    private GamePanel myCanvas;

    public Menu() {
        this.size = ConstantValues.MENU_MIN_LENGT;
        this.isActive = false;
        
        options = new MenuOption[1];
        
        for (int i = 0; i < options.length; i++) {
            options[i] = new MenuOption(this.size - 200, 150 * (i + 1) + ConstantValues.MENU_MIN_LENGT * i, 150, 150, i);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MenuOption[] getOptions() {
        return options;
    }

    public void setOptions(MenuOption[] options) {
        this.options = options;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void showHideMenu(GamePanel my){
        myCanvas = my;
        new Thread(this).start();
    }
    
    public void drawMenu(Graphics g){
        g.setColor(ConstantValues.groupsColor);
        
        g.fillRect(0, 0, this.getSize(), ConstantValues.gameHeight);

        MenuOption option;
        for (int i = 0; i < options.length; i++) {
            option = options[i];
            g.drawImage(option.getImage(), this.size - option.getX_size() - ConstantValues.MENU_MIN_LENGT, option.getY_size() * (i + 1) + ConstantValues.MENU_MIN_LENGT * i, option.getX_size(), option.getY_size(), myCanvas);
        }

        }
    
    
    public int clickOption(int x, int y, GamePanel my){
        MenuOption option;
        for (int i = 0; i < options.length; i++) {
            option = options[i];
            if(x > this.size - option.getX_size() - ConstantValues.MENU_MIN_LENGT && x < this.size - ConstantValues.MENU_MIN_LENGT
                    && y > (option.getY_size() * (i + 1) + ConstantValues.MENU_MIN_LENGT * i) && y < (option.getY_size() * (i + 1) + ConstantValues.MENU_MIN_LENGT * i) + option.getY_size()){
                
                option.setIsActive(true);
                return i;
            }
        }
        this.showHideMenu(my);
        return -1;
    }

    @Override
    public void run() {
        if (!isActive) {
            while( this.size < ConstantValues.MENU_MAX_LENGT) {
                this.size ++;
                myCanvas.repaint();
                try {
                    Thread.sleep(1l);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.setIsActive(true);
        }else{
            while(this.size > ConstantValues.MENU_MIN_LENGT){
                this.size --;
                myCanvas.repaint();

                try {
                    Thread.sleep(1l);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.setIsActive(false);
        }
    }
    
}
