package fr.Mathis_Bruel.spacecube.spacecubeapi.api.players;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * It's a class that represents a player in the database
 */
public class SpaceCubePlayer {

    private UUID uuid;
    private Ranks rank;
    private Money money;
    private String permissions;

    public SpaceCubePlayer() {
        this.uuid = uuid;
        this.rank = rank;
        this.money = money;
    }

    /**
     * > This function returns the UUID of the player
     *
     * @return The UUID of the user.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * This function returns the rank of the card.
     *
     * @return The rank of the card.
     */
    public Ranks getRank() {
        return rank;
    }

    /**
     * This function returns the money attribute of the object.
     *
     * @return The money object.
     */
    public Money getMoney() {
        return money;
    }

    /**
     * > This function returns the permissions of the current user
     *
     * @return The permissions object.
     */
    public String getPermissions() {
        return permissions;
    }



    /**
     * This function sets the rank of the card to the rank passed in as a parameter.
     *
     * @param rank The rank of the card.
     */
    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    /**
     * This function sets the money attribute of the object to the money parameter.
     *
     * @param money The amount of money to be added to the player's balance.
     */
    public void setMoney(Money money) {
        this.money = money;
    }

    /**
     * > This function sets the permissions of the current user
     *
     * @param permissions The permissions to be set for the user.
     */
    public void setPermissions(String permissions) {
        /**
         * It creates a player in the database if it doesn't exist
         *
         * @param player The player to create
         * @return The SpaceCubePlayer object
         */
        this.permissions = permissions;
    }

    // update player

    /**
     * It creates a player in the database if it doesn't exist
     *
     * @param player The player to create
     * @return The player's information.
     */
    private SpaceCubePlayer register(Player player) {
        this.rank = Ranks.getDefaultRank();
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank FROM Players WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getUniqueId().toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.rank = Ranks.getRank(resultSet.getString("Rank"));
            } else {
                // Player doesn't exist
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Players`(`UUID`, `Money`, `Rank`, `Permissions`) VALUES (?,?,?,?)");
                preparedStatement2.setString(1, player.getUniqueId().toString());
                preparedStatement2.setInt(2, 0);
                preparedStatement2.setString(3, Ranks.getDefaultRank().getName());
                preparedStatement2.setString(4, "");
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        this.uuid = player.getUniqueId();
        this.money = null;
        this.permissions = null;
        Main.getApi().scp.put(player.getUniqueId(), this);
        return this;
    }

    /**
     * It updates the player's data in the database
     *
     * @return A boolean
     */
    public boolean updatePlayer() {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `Players` SET `Money`=?,`Rank`=?,`Permissions`=? WHERE UUID=?");
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, this.getRank().getName());
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, this.getUuid().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // isRegistered

    /**
     * It checks if the player is registered in the database
     *
     * @param SpaceCubePlayer The player to check
     * @return A boolean
     */
    public boolean isRegistered(SpaceCubePlayer SpaceCubePlayer) {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID FROM Players WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, SpaceCubePlayer.getUuid().toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }








    /**
     * It gets all the players from the database and puts them in a map
     */
    public static final void init(){
        // create all scp and put in map in Main
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank, Permissions FROM Players";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SpaceCubePlayer scp = new SpaceCubePlayer().register(Bukkit.getOfflinePlayer(UUID.fromString(resultSet.getString("UUID"))).getPlayer());

            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


    }


    /**
     * It gets the player from the map
     *
     * @param player The player to get
     * @return The player's information.
     */
    public static SpaceCubePlayer getSCP(Player player) {
        SpaceCubePlayer scp = Main.getApi().scp.get(player.getUniqueId());
        if (scp == null) {
            scp = new SpaceCubePlayer().register(player);
        }
        return scp;
    }



}
