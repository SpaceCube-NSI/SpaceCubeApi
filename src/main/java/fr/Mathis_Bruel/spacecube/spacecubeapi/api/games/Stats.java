package fr.Mathis_Bruel.spacecube.spacecubeapi.api.games;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Stats {

    private Games game;
    private int kills;
    private int death;
    private float ratioKillsDeath;
    private int games;
    private int wins;
    private int loses;
    private float ratioWinsLoses;
    private HashMap<Object, Object> divers;


    public Stats() {

    }

    public int getKills() {
        return this.kills;
    }

    public int getDeath() {
        return this.death;
    }

    public float getRatioKillsDeath() {
        return this.ratioKillsDeath;
    }

    public int getGames() {
        return this.games;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLoses() {
        return this.loses;
    }

    public float getRatioWinsLoses() {
        return this.ratioWinsLoses;
    }

    public HashMap<Object, Object> getDivers() {
        return this.divers;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public void setRatioKillsDeath(float ratioKillsDeath) {
        this.ratioKillsDeath = ratioKillsDeath;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void setRatioWinsLoses(float ratioWinsLoses) {
        this.ratioWinsLoses = ratioWinsLoses;
    }

    public void setDivers(HashMap<Object, Object> divers) {
        this.divers = divers;
    }

    public void setGame(Games game) {
        this.game = game;
    }

    public Games getGame() {
        return this.game;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public void removeKills(int kills) {
        this.kills -= kills;
    }

    public void addGames(int gamesPlayed) {
        this.games += gamesPlayed;
    }

    public void removeGames(int gamesPlayed) {
        this.games -= gamesPlayed;
    }

    public void addWins(int wins) {
        this.wins += wins;
    }

    public void removeWins(int wins) {
        this.wins -= wins;
    }

    public void addLoses(int loses) {
        this.loses += loses;
    }

    public void removeLoses(int loses) {
        this.loses -= loses;
    }

    public void addDeath(int death) {
        this.death += death;
    }

    public void removeDeath(int death) {
        this.death -= death;
    }

    public void addDivers(Object key, Object value) {
        this.divers.put(key, value);
    }

    public void removeDivers(Object key) {
        this.divers.remove(key);
    }

    public void updateRatioKillsDeath() {
        this.ratioKillsDeath = (float) this.kills / (float) this.death;
    }

    public void updateRatioWinsLoses() {
        this.ratioWinsLoses = (float) this.wins / (float) this.loses;
    }

    public void updateRatio() {
        this.ratioKillsDeath = (float) this.kills / (float) this.death;
        this.ratioWinsLoses = (float) this.wins / (float) this.loses;
    }


    public void reset() {
        this.kills = 0;
        this.death = 0;
        this.ratioKillsDeath = 0;
        this.games = 0;
        this.wins = 0;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }


    public void updateStats(){
        // update stats in db

        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Kills, Death FROM Stats WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getUniqueId().toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

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
        this.money = 0;
        this.permissions = null;
        Main.getApi().scp.put(player.getUniqueId(), this);
        return this;
    }


}
