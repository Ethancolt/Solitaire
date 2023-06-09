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
import java.util.ArrayList;

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

        if (!userExists()) {

            createUser();

        }
        
        if (!saveGameExists()) {

            createSaveGame();

        }

        if (!scoreExists()) {

            createScore();

        }

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

    public void saveGame(GameBoard board, int userID) {

        try {

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

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM saveGame WHERE user_id = (?)");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                statement = conn.prepareStatement("UPDATE saveGame SET "
                        + "table1 = ?, "
                        + "table2 = ?, "
                        + "table3 = ?, "
                        + "table4 = ?, "
                        + "table5 = ?, "
                        + "table6 = ?, "
                        + "table7 = ?, "
                        + "foundation1 = ?, "
                        + "foundation2 = ?, "
                        + "foundation3 = ?, "
                        + "foundation4 = ?, "
                        + "provider1 = ?, "
                        + "provider2 = ? "
                        + "WHERE user_id = ?");

                for (int i = 0; i < 13; i++) {

                    statement.setBytes(i + 1, data[i]);

                }

                statement.setInt(14, userID);

                statement.executeUpdate();
                statement.close();

            } else {

                statement = conn.prepareStatement("INSERT INTO saveGame "
                        + "(table1, table2, table3, table4, table5, table6, table7, "
                        + "foundation1, foundation2, foundation3, foundation4, provider1, provider2, user_id) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                for (int i = 0; i < 13; i++) {

                    statement.setBytes(i + 1, data[i]);

                }

                statement.setInt(14, userID);

                statement.executeUpdate();
                statement.close();

            }

        } catch (SQLException | IOException ex) {

            System.out.println(ex.getMessage());

        }

    }

    public void loadGame(GameBoard board, int userID) {

        CardPile[] piles = new CardPile[13];

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM saveGame WHERE user_id = (?)");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

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

    public boolean canBeContinued(int userID) {

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM saveGame WHERE user_id = (?)");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return true;

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return false;

    }

    public int getUserID(String username) {

        int userID = -1;

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT id FROM users WHERE name = ?");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                userID = resultSet.getInt("id");

            } else {

                statement = conn.prepareStatement("INSERT INTO users (name) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, username);
                statement.executeUpdate();

                resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {

                    userID = resultSet.getInt(1);

                }

            }

            resultSet.close();
            statement.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return userID;

    }

    public ArrayList<String> getUsers() {

        ArrayList<String> users = new ArrayList<>();

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");

                users.add(name);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return users;

    }

    public void saveScore(int score, int userID) {

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT score FROM score WHERE user_id = ?");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int oldScore = resultSet.getInt("score");

                if (oldScore < score) {

                    statement = conn.prepareStatement("UPDATE score SET score = ? WHERE user_id = ?");
                    statement.setInt(1, score);
                    statement.setInt(2, userID);
                    statement.executeUpdate();

                }

            } else {

                statement = conn.prepareStatement("INSERT INTO score (user_id, score) VALUES (?, ?)");
                statement.setInt(1, userID);
                statement.setInt(2, score);
                statement.executeUpdate();

            }

            resultSet.close();
            statement.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

    public ArrayList<Highscore> getHighscores() {

        ArrayList<Highscore> highscores = new ArrayList<>();

        try {

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM score ORDER BY score DESC LIMIT 10");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int userID = resultSet.getInt("user_id");
                int score = resultSet.getInt("score");
                String name = "";

                statement = conn.prepareStatement("SELECT name FROM users WHERE user_id = (?)");
                statement.setInt(1, userID);

                ResultSet userResultSet = statement.executeQuery();

                if (userResultSet.next()) {

                    name = userResultSet.getString("name");

                } else {

                    name = "Unknown";

                }

                highscores.add(new Highscore(name, score));

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return highscores;

    }

    private boolean saveGameExists() {

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
                    + "user_id INT PRIMARY KEY,"
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
                    + "provider2 BLOB,"
                    + "FOREIGN KEY (user_id) REFERENCES users(id)"
                    + ")";

            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

    private boolean userExists() {

        try {

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "USERS", null);

            boolean exists = tables.next();

            tables.close();

            return exists;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return false;

    }

    private void createUser() {

        try {

            Statement statement = conn.createStatement();

            String sql = "CREATE TABLE users ("
                    + "id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
                    + "name VARCHAR(25)"
                    + ")";

            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

    private boolean scoreExists() {

        try {

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "SCORE", null);

            boolean exists = tables.next();

            tables.close();

            return exists;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return false;

    }

    private void createScore() {

        try {

            Statement statement = conn.createStatement();

            String sql = "CREATE TABLE score ("
                    + "user_id INT PRIMARY KEY,"
                    + "score INT,"
                    + "FOREIGN KEY (user_id) REFERENCES users(id)"
                    + ")";

            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

}
