/**
 * It's a class that allows you to create a rank object and update it in the database
 */
package fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.Main;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.Rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It's a class that represents a rank
 */
public class Ranks {

    // create rank object: Name, Prefix, Suffix, Permission, ColorName, ColorChat, Default
    private String name;
    private String prefix;
    private String suffix;
    private String permissions;
    private String colorName;
    private String colorChat;
    private boolean isDefault;

    public Ranks() {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permissions = permissions;
        this.colorName = colorName;
        this.colorChat = colorChat;
        this.isDefault = isDefault;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the suffix of the file.
     *
     * @return The suffix of the file.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * This function returns the suffix of the file.
     *
     * @return The suffix of the file.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * > This function returns the permissions of the user
     *
     * @return The permissions of the user.
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * This function returns the colorName variable.
     *
     * @return The colorName variable is being returned.
     */
    public String getColorName() {
        return colorName;
    }

    /**
     * It returns the colorChat variable
     *
     * @return The colorChat variable is being returned.
     */
    public String getColorChat() {
        return colorChat;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function sets the prefix variable to the value of the prefix parameter.
     *
     * @param prefix The prefix to use for the generated class names.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * This function sets the suffix of the file.
     *
     * @param suffix The suffix to be appended to the file name.
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * > This function sets the permissions of the user
     *
     * @param permissions The permissions that the user has.
     */
    public void setPermission(String permissions) {
        this.permissions = permissions;
    }

    /**
     * This function sets the colorName variable to the value of the colorName parameter.
     *
     * @param colorName The name of the color.
     */
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**
     * This function sets the color of the chat
     *
     * @param colorChat The color of the chat message.
     */
    public void setColorChat(String colorChat) {
        this.colorChat = colorChat;
    }

    /**
     * This function sets the default value of the variable isDefault to the value of the parameter isDefault.
     *
     * @param isDefault If true, the default value will be used.
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }


    /**
     * It creates a rank in the database
     *
     * @param name The name of the rank
     * @param prefix The prefix of the rank
     * @param suffix The suffix of the rank
     * @param permissions A string of permissions separated by commas.
     * @param colorName The color of the name in chat.
     * @param colorChat The color of the chat
     * @param isDefault If the rank is the default rank.
     * @return The rank that was created.
     */
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
        this.permissions = permissions;
        this.colorName = colorName;
        this.colorChat = colorChat;
        this.isDefault = isDefault;

        return this;
    }

    // update rank
    /**
     * It updates the rank in the database
     */
    public void updateRank() {
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ColorChat, ColorName, `Default` FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `Ranks` SET `Prefix`=?,`Suffix`=?,`Permissions`=?,`ColorName`=?,`ColorChat`=?,`Default`=? WHERE Name=?");
                preparedStatement2.setString(1, this.prefix);
                preparedStatement2.setString(2, this.suffix);
                preparedStatement2.setString(3, this.permissions);
                preparedStatement2.setString(4, this.colorName);
                preparedStatement2.setString(5, this.colorChat);
                preparedStatement2.setBoolean(6, this.isDefault);
                preparedStatement2.setString(7, this.name);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            } else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO `Ranks`(`Name`, `Prefix`, `Suffix`, `Permissions`, `ColorName`, `ColorChat`, `Default`) VALUES (?,?,?,?,?,?,?)");
                preparedStatement2.setString(1, this.name);
                preparedStatement2.setString(2, this.prefix);
                preparedStatement2.setString(3, this.suffix);
                preparedStatement2.setString(4, this.permissions);
                preparedStatement2.setString(5, this.colorName);
                preparedStatement2.setString(6, this.colorChat);
                preparedStatement2.setBoolean(7, this.isDefault);

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
        this.permissions = permissions;
        this.colorName = colorName;
        this.colorChat = colorChat;
        this.isDefault = isDefault;
    }

    /**
     * It gets the rank from the database and returns it
     *
     * @param name The name of the rank
     * @return The rank object
     */
    public Ranks getRank(String name){
        final Connection connection2 = Main.getDbManageur().getConnection();
        try {
            final java.sql.Connection connection = connection2.getConnection();
            String query = "SELECT Name, Permissions, Prefix, Suffix, ColorChat, ColorName, `Default` FROM Ranks WHERE Name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("Name");
                this.prefix = resultSet.getString("Prefix");
                this.suffix = resultSet.getString("Suffix");
                this.permissions = resultSet.getString("Permissions");
                this.colorName = resultSet.getString("ColorName");
                this.colorChat = resultSet.getString("ColorChat");
                this.isDefault = resultSet.getBoolean("Default");
            } else {
                System.out.println("Rank doesn't exist");
                return null;
            }
            resultSet.close();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this;
    }

}


