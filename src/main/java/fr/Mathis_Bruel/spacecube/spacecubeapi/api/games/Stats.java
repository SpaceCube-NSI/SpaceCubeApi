package fr.Mathis_Bruel.spacecube.spacecubeapi.api.games;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.utils.JSON;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class Stats {

    private Games game;
    private UUID uuid;
    private int kills;
    private int death;
    private float ratioKillsDeath;
    private int games;
    private int wins;
    private int loses;
    private float ratioWinsLoses;
    private HashMap<Object, Object> divers;


    public Stats(Games game, UUID uuid) {
        this.game = game;
        this.uuid = uuid;
        this.kills = 0;
        this.death = 0;
        this.ratioKillsDeath = 0;
        this.games = 0;
        this.wins = 0;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = 0;
        this.ratioKillsDeath = 0;
        this.games = 0;
        this.wins = 0;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = 0;
        this.games = 0;
        this.wins = 0;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = 0;
        this.games = games;
        this.wins = 0;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games, int wins){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = 0;
        this.games = games;
        this.wins = wins;
        this.loses = 0;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games, int wins, int loses){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = 0;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games, int wins, int loses, float ratioKillsDeath){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = ratioKillsDeath;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.ratioWinsLoses = 0;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games, int wins, int loses, float ratioKillsDeath, float ratioWinsLoses){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = ratioKillsDeath;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.ratioWinsLoses = ratioWinsLoses;
        this.divers = new HashMap<>();
    }

    public Stats(Games game, UUID uuid, int kills, int death, int games, int wins, int loses, float ratioKillsDeath, float ratioWinsLoses, HashMap<Object, Object> divers){
        this.game = game;
        this.uuid = uuid;
        this.kills = kills;
        this.death = death;
        this.ratioKillsDeath = ratioKillsDeath;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.ratioWinsLoses = ratioWinsLoses;
        this.divers = divers;
    }


    public UUID getUuid() {
        return uuid;
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

    public void updateRatios() {
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
        this.updateStats();
    }


    public void updateStats(){
        // update stats in db

        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Kills, Death, Win, Lost, Divers, GamesPlayed FROM `Stats-"+this.game.getName()+"` WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.uuid.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Stats-"+this.game.getName()+"` SET Kills=?, Death=?, Win=?, Lost=?, Divers=?, GamesPlayed=? WHERE UUID=?");
                preparedStatement2.setInt(1, this.kills);
                preparedStatement2.setInt(2, this.death);
                preparedStatement2.setInt(3, this.wins);
                preparedStatement2.setInt(4, this.loses);
                preparedStatement2.setString(5, JSON.build(this.divers));
                preparedStatement2.setString(7, this.uuid.toString());
                preparedStatement2.setInt(6, this.games);
                preparedStatement2.executeUpdate();


            } else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Stats-"+this.game.getName()+"` (UUID, Kills, Death, Win, Lost, Divers, GamesPlayed) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, this.uuid.toString());
                preparedStatement2.setInt(2, this.kills);
                preparedStatement2.setInt(3, this.death);
                preparedStatement2.setInt(4, this.wins);
                preparedStatement2.setInt(5, this.loses);
                preparedStatement2.setString(6, JSON.build(this.divers));
                preparedStatement2.setInt(7, this.games);
                preparedStatement2.executeUpdate();
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void init(){
        // load stats from db

        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT UUID, Kills, Death, Win, Lost, Divers, GamesPlayed FROM `Stats-"+this.game.getName()+"` WHERE UUID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.uuid.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.kills = resultSet.getInt("Kills");
                this.death = resultSet.getInt("Death");
                this.wins = resultSet.getInt("Win");
                this.loses = resultSet.getInt("Lost");
                this.divers = (HashMap<Object, Object>) JSON.parseJSON(resultSet.getString("Divers")).getJsonMap();
                this.games = resultSet.getInt("GamesPlayed");
            }else{
                this.kills = 0;
                this.death = 0;
                this.wins = 0;
                this.loses = 0;
                this.divers = new HashMap<>();
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Stats-"+this.game.getName()+"` (UUID, Kills, Death, Win, Lost, Divers,GamesPlayed) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, this.uuid.toString());
                preparedStatement2.setInt(2, this.kills);
                preparedStatement2.setInt(3, this.death);
                preparedStatement2.setInt(4, this.wins);
                preparedStatement2.setInt(5, this.loses);
                preparedStatement2.setString(6, this.divers.toString());
                preparedStatement2.setInt(7, this.games);
                preparedStatement2.executeUpdate();

            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}
