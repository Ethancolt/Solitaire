package SolitaireGUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import solitaire.Foundation;
import solitaire.Stock;
import solitaire.Table;
import solitaire.Waste;

/**
 *
 * @author Ethan Smith [21153581]
 */
public final class DBManager {

    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:SolitaireDB; create=true";
    private static Connection conn;

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

    public void saveGame(GameBoard board) {

        try {

            if (!SaveGameExists()) {

                createSaveGame();

            }

            CardPile[] piles = board.getCardPiles();
            byte[][] data = new byte[13][];

            ByteArrayOutputStream baos = null;
            ObjectOutputStream oos = null;

            for (int i = 0; i < 13; i++) {

                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(piles[i]);
                data[i] = baos.toByteArray();

            }

            if (oos != null) {

                oos.close();

            }

            PreparedStatement statement = conn.prepareStatement("INSERT INTO saveGame "
                    + "(table1, table2, table3, table4, table5, table6, table7, "
                    + "foundation1, foundation2, foundation3, foundation4, provider1, provider2) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for (int i = 0; i < 13; i++) {

                statement.setBytes(i + 1, data[i]);

            }

            statement.executeUpdate();
            statement.close();

        } catch (SQLException | IOException ex) {

            System.out.println(ex.getMessage());

        }

    }

    public void loadGame(GameBoard board) {

        CardPile[] piles = new CardPile[13];

        try {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM saveGame");

            if (resultSet.next()) {

                byte[][] data = new byte[13][];

                for (int i = 0; i < 13; i++) {

                    data[i] = resultSet.getBytes(i + 2);

                }

                ByteArrayInputStream bais = null;
                ObjectInputStream ois = null;

                for (int i = 0; i < 13; i++) {

                    bais = new ByteArrayInputStream(data[i]);
                    ois = new ObjectInputStream(bais);
                    piles[i] = (CardPile) ois.readObject();
                    ois.close();

                }

            }

            resultSet.close();
            statement.close();

        } catch (SQLException | IOException | ClassNotFoundException ex) {

            System.out.println(ex.getMessage());

        }

        board.setCardPiles(piles);

    }

    private boolean SaveGameExists() {

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

    private void createSaveGame() {

        try {

            Statement statement = conn.createStatement();

            String sql = "CREATE TABLE saveGame ("
                    + "id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
                    + "table1 BLOB,"
                    + "table2 BLOB,"
                    + "table3 BLOB,"
                    + "table4 BLOB,"
                    + "table5 BLOB,"
                    + "table6 BLOB,"
                    + "table7 BLOB,"
                    + "foundation1 BLOB,"
                    + "foundation2 BLOB,"
                    + "foundation3 BLOB,"
                    + "foundation4 BLOB,"
                    + "provider1 BLOB,"
                    + "provider2 BLOB)";

            statement.executeUpdate(sql);

            statement.close();

            System.out.println("saveGame table created successfully");

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

}
