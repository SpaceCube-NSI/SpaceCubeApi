package fr.Mathis_Bruel.spacecube.spacecubeapi.api.players;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rank {

    public Ranks getRankOfScp(SpaceCubePlayer scp) {
        // get rank string in database
        Ranks rank = null;
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Money, Rank FROM Players WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, scp.getUuid().toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rank =  new Ranks().getRank(resultSet.getString("Rank"));
            } else {
                // Player doesn't exist
                System.out.println("Player doesn't exist");
                rank = null;
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return rank;


    }

}
