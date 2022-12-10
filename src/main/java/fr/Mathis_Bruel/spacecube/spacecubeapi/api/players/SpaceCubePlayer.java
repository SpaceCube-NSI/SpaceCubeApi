package fr.Mathis_Bruel.spacecube.spacecubeapi.api.players;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
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
    private Rank rank;
    private Money money;
    private Permissions permissions;

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
    public Rank getRank() {
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
    public Permissions getPermissions() {
        return permissions;
    }



    /**
     * This function sets the rank of the card to the rank passed in as a parameter.
     *
     * @param rank The rank of the card.
     */
    public void setRank(Rank rank) {
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
    public void setPermissions(Permissions permissions) {
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
    public SpaceCubePlayer createPlayer(Player player) {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank FROM Players WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getUniqueId().toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Player already exist
            } else {
                // Player doesn't exist
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Players`(`UUID`, `Money`, `Rank`, `Permissions`) VALUES (?,?,?,?)");
                preparedStatement2.setString(1, player.getUniqueId().toString());
                preparedStatement2.setInt(2, 0);
                preparedStatement2.setString(3, "Membre");
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
        this.rank = null;
        this.money = null;
        this.permissions = null;
        return this;
    }

    // update this player in db
/*
    public void updatePlayer() {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `Players` SET `Money`=?,`Rank`=?,`Permissions`=? WHERE UUID=?");
            preparedStatement.setInt(1, this.getMoney());
            preparedStatement.setString(2, this.rank.getRank());
            preparedStatement.setString(3, this.permissions.getPermissions());
            preparedStatement.setString(4, this.name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
*/


}
