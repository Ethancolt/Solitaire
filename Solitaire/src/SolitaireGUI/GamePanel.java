
package SolitaireGUI;



import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import java.awt.event.MouseListener;


/**
 *
 * @author Trey Baker [21155292]
 */
public class GamePanel extends JPanel implements  MouseMotionListener, MouseListener{

    private GameBoard board = new GameBoard();
    private final Deck deck = new Deck();
    private final Menu menu = new Menu();
    
    public GamePanel(){

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        
        board.addCards(deck.getCards());
        
        DBManager db = new DBManager();
        
        db.saveGame(board);
        db.loadGame(board);
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(ConstantValues.backGroundColor);
        g.fillRect(0, 0, ConstantValues.gameWidth, ConstantValues.gameHeight);
        
        board.drawBoard(g, this);
       
        menu.drawMenu(g);
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (!menu.isIsActive()) {
            this.board.draggImages(me.getX(), me.getY());
        }else{
            menu.showHideMenu(this);
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // If we click menu
        if(me.getX() <= menu.getSize()){
            if(menu.isIsActive()){
                this.validateMenuAnswer(this.menu.clickOption(me.getX(), me.getY(), this));
            }else{
                menu.showHideMenu(this);
            }
        }
        if(!menu.isIsActive()){
            board.clickProvider(me.getX(), me.getY());
        }else{
            menu.showHideMenu(this);
        }
        
        repaint();
    }
    
    private void validateMenuAnswer(int response){
        switch(response){
            case 0:
                deck.newGame();
                board = new GameBoard();
                board.addCards(deck.getCards());
                repaint();
                break;
            default:
                break;
        } 
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (!menu.isIsActive()) {
            this.board.releaseCards(me.getX(), me.getY());
        }
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
