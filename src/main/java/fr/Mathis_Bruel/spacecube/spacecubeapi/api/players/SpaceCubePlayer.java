package fr.Mathis_Bruel.spacecube.spacecubeapi.api.players;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaceCubePlayer {

    private String name;
    private Rank rank;
    private Money money;
    private Permissions permissions;

    public SpaceCubePlayer() {
        this.name = name;
        this.rank = rank;
        this.money = money;
        this.permissions = permissions;
    }

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
        }
        this.name = player.getName();
        this.rank = null;
        this.money = null;
        this.permissions = null;
        return this;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public Money getMoney() {
        return money;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }


}
