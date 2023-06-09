
package SolitaireGUI;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;


/**
 *
 * @author Trey Baker [21155292]
 */
public class Card implements Serializable {
    
    private int x_location;
    private int y_location;
    
    private int x_size = ConstantValues.cardWidth;
    private int y_size = ConstantValues.CARD_Y_SIZE;
    
    private int number;
    private boolean isRedColor;
    private int group;
    private String path;
    private transient BufferedImage image;
    
    private boolean isSelected = false;
    private boolean isVisile = false;
    
    public Card(){
    }
    
    public Card(String path){
        this.path = path;
        
        image = ImageReader.readImage(path);
    }
    
    public Card(String path, int x_location, int y_location){
        this.path = path;
        this.x_location = x_location;
        this.y_location = y_location;
        
        image = ImageReader.readImage(path);
    }
    
    public Card(String path, int x_location, int y_location, boolean isRedColor, int number, int group){
        this.path = path;
        this.x_location = x_location;
        this.y_location = y_location;
        this.isRedColor = isRedColor;
        this.number = number;
        this.group = group;
        
        image = ImageReader.readImage(path);
    }    
    
    public void setPath(String path){
        this.path = path;
        image = ImageReader.readImage(path);
    }
    
    public void setImageXLocation(int x_location){
        this.x_location = x_location;
    }
    
    public void setImageYLocation(int y_location){
        this.y_location = y_location;
    }
    
    public void setImageXSize(int x_size){
        this.x_size = x_size;
    }
    
    public void setImageYSize(int y_size){
        this.y_size = y_size;
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
    }
    
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    
    public void setIsVisible(boolean isVisible){
        this.isVisile= isVisible;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public int getImageXLocation(){
        return this.x_location;
    }
    
    public int getImageYLocation(){
        return this.y_location;
    }
    
    public int getImageXSize(){
        return this.x_size;
    }
    
    public int getImageYSize(){
        return this.y_size;
    }
    
    public BufferedImage getImage(){
        return this.image;
    }
    
    public boolean isSelected(){
        return this.isSelected;
    }
    
    public boolean isVisible(){
        return this.isVisile;
    }
    
    public boolean isRedColor(){
        return this.isRedColor;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public int getGroup(){
        return this.group;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Convert the BufferedImage to a byte array
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", byteStream);
        byte[] imageBytes = byteStream.toByteArray();

        // Write the byte array to the ObjectOutputStream
        out.writeObject(imageBytes);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Read the byte array from the ObjectInputStream
        byte[] imageBytes = (byte[]) in.readObject();

        // Reconstruct the BufferedImage from the byte array
        ByteArrayInputStream byteStream = new ByteArrayInputStream(imageBytes);
        image = ImageIO.read(byteStream);
    }
    
}
