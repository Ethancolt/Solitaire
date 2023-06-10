package SolitaireGUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import java.awt.event.MouseListener;

/**
 * 
 * @author Trey Baker [21155292]
 */
public class GamePanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    private GameBoard board;
    private final Deck deck = new Deck();
    private PauseMenu pauseMenu;
    private ScoreTracker scoreTracker = new ScoreTracker();

    public GamePanel(GameBoard board) {
        if (board != null) {
            this.board = board;
        } else {
            this.board = new GameBoard();
        }
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.board.addCards(deck.getCards());
    }

    public Deck getDeck()
    {
        return deck;
    }
    
        public GameBoard getGameBoard()
    {
        return board;
    }
    
        public void resetGame()
        {
                deck.newGame();
                board = new GameBoard();
                board.addCards(deck.getCards());
                repaint();
                scoreTracker.resetScore();
                pauseMenu.dispose();
        }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(ConstantValues.backGroundColor);
        g.fillRect(0, 0, ConstantValues.gameWidth, ConstantValues.gameHeight);

        board.drawBoard(g, this);
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        this.board.draggImages(me.getX(), me.getY());
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        this.board.clickProvider(me.getX(), me.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        this.board.releaseCards(me.getX(), me.getY());
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
            pauseMenu = new PauseMenu(this);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
