package fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.Rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ranks {

    // create rank object: Name, Prefix, Suffix, Permission, ColorName, ColorChat, Default
    private String name;
    private String prefix;
    private String suffix;
    private String permission;
    private String colorName;
    private String colorChat;
    private boolean isDefault;

    public Ranks() {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permission = permission;
        this.colorName = colorName;
        this.colorChat = colorChat;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getPermission() {
        return permission;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorChat() {
        return colorChat;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColorChat(String colorChat) {
        this.colorChat = colorChat;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Ranks getRank(String name){
        return this;
    }

    public Ranks createRank(String name, String prefix, String suffix, String permissions, String colorName, String colorChat, boolean isDefault) {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ColorChat, ColorName, `Default` FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Rank already exist");
                return this.getRank(name);
            } else {
                System.out.println("Rank doesn't exist");
                // Player doesn't exist
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Ranks`(`Name`, `Prefix`, `Suffix`, `Permissions`, `ColorName`, `ColorChat`, `Default`) VALUES (?,?,?,?,?,?,?)");
                preparedStatement2.setString(1, name);
                preparedStatement2.setString(2, prefix);
                preparedStatement2.setString(3, suffix);
                preparedStatement2.setString(4, permissions);
                preparedStatement2.setString(5, colorName);
                preparedStatement2.setString(6, colorChat);
                preparedStatement2.setBoolean(7, isDefault);

                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permission = permission;
        this.colorName = colorName;
        this.colorChat = colorChat;
        this.isDefault = isDefault;

        return this;
    }

}


