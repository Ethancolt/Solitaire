package solitaire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ethan Smith [21153581]
 */
public final class DBManager {

    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:SolitaireDB; create=true";
    Connection conn;

    public DBManager() {

        connect();

    }

    public void connect() {

        if (conn == null) {
            try {

                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }

        }

    }

    public void disconnect() {

        if (conn != null) {

            try {

                conn.close();
                conn = null;

            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }

        }

    }

    public void saveGame(Play game) {

        try {

            if (!SaveGameExists()) {
                
                createSaveGame();
                
            }
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(game.table);
            byte[] serializedTable = baos.toByteArray();
            
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(game.foundation);
            byte[] serializedFoundation = baos.toByteArray();
            
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(game.stock);
            byte[] serializedStock = baos.toByteArray();
            
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(game.waste);
            byte[] serializedWaste = baos.toByteArray();
            
            oos.close();

            

            PreparedStatement statement = conn.prepareStatement("INSERT INTO saveGame (serialized_table, serialized_foundation, serialized_stock, serialized_waste) VALUES (?, ?, ?, ?)");
            statement.setBytes(1, serializedTable);
            statement.setBytes(2, serializedFoundation);
            statement.setBytes(3, serializedStock);
            statement.setBytes(4, serializedWaste);
            statement.executeUpdate();

            statement.close();

        } catch (SQLException | IOException ex) {

            System.out.println(ex.getMessage());

        }

    }

    public Play loadGame() {

        Play game = new Play();

        try {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM saveGame");

            if (resultSet.next()) {

                byte[] serializedTable = resultSet.getBytes("serialized_table");
                byte[] serializedFoundation = resultSet.getBytes("serialized_foundation");
                byte[] serializedStock = resultSet.getBytes("serialized_stock");
                byte[] serializedWaste = resultSet.getBytes("serialized_waste");
                
                System.out.println(new String(serializedTable, StandardCharsets.US_ASCII));
                System.out.println(new String(serializedFoundation, StandardCharsets.US_ASCII));

                ByteArrayInputStream bais = new ByteArrayInputStream(serializedTable);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Table[] table = (Table[]) ois.readObject();
                ois.close();
                
                bais = new ByteArrayInputStream(serializedFoundation);
                ois = new ObjectInputStream(bais);
                Foundation[] foundation = (Foundation[]) ois.readObject();
                ois.close();
                
                bais = new ByteArrayInputStream(serializedStock);
                ois = new ObjectInputStream(bais);
                Stock stock = (Stock) ois.readObject();
                ois.close();
                
                bais = new ByteArrayInputStream(serializedWaste);
                ois = new ObjectInputStream(bais);
                Waste waste = (Waste) ois.readObject();
                ois.close();
                
                
                
                game.table = table;
                game.foundation = foundation;
                game.stock = stock;
                game.waste = waste;

            }

            resultSet.close();
            statement.close();

        } catch (SQLException | IOException | ClassNotFoundException ex) {

            System.out.println(ex.getMessage());

        }

        return game;

    }

    public boolean SaveGameExists() {

        try {

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "SAVEGAME", null);

            boolean exists = tables.next();

            tables.close();

            return exists;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return false;

    }

    public void createSaveGame() {
        
        try {
            
            Statement statement = conn.createStatement();

            String sql = "CREATE TABLE saveGame ("
                    + "id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
                    + "serialized_table BLOB,"
                    + "serialized_foundation BLOB,"
                    + "serialized_stock BLOB,"
                    + "serialized_waste BLOB)";

            statement.executeUpdate(sql);

            statement.close();

            System.out.println("saveGame table created successfully");
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
        
    }

}
