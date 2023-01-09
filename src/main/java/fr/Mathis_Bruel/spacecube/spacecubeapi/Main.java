package fr.Mathis_Bruel.spacecube.spacecubeapi;

import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.Connection;
import fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.DbManageur;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.SpaceCubePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private static DbManageur dbmanageur;
    public fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase.DbCredentials DbCredentials;
    public Connection connection;
    private static Main instance;
    public HashMap<String, Ranks> ranks = new HashMap<>();
    public HashMap<UUID, SpaceCubePlayer> scp = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        dbmanageur = new DbManageur();
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("\n§2   _____                                  _____           _                                 _ \n" +
                "§2  / ____|                                / ____|         | |                /\\             (_)\n" +
                "§2 | (___    _ __     __ _    ___    ___  | |       _   _  | |__     ___     /  \\     _ __    _ \n" +
                "§2  \\___ \\  | '_ \\   / _` |  / __|  / _ \\ | |      | | | | | '_ \\   / _ \\   / /\\ \\   | '_ \\  | |\n" +
                "§2  ____) | | |_) | | (_| | | (__  |  __/ | |____  | |_| | | |_) | |  __/  / ____ \\  | |_) | | |\n" +
                "§2 |_____/  | .__/   \\__,_|  \\___|  \\___|  \\_____|  \\__,_| |_.__/   \\___| /_/    \\_\\ | .__/  |_|\n" +
                "§2          | |                                                                      | |        \n" +
                "§2          |_|                                                                      |_|        ");
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         L'API viens de ce §2start§7 !               §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         Developpeur: §2Mathis_Bruel               §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         Pour: §2SpaceCube                         §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");

        getCommand("test").setExecutor(new TestCommand());
        Ranks.init();
        SpaceCubePlayer.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("\n§4   _____                                  _____           _                                 _ \n" +
                "§4  / ____|                                / ____|         | |                /\\             (_)\n" +
                "§4 | (___    _ __     __ _    ___    ___  | |       _   _  | |__     ___     /  \\     _ __    _ \n" +
                "§4  \\___ \\  | '_ \\   / _` |  / __|  / _ \\ | |      | | | | | '_ \\   / _ \\   / /\\ \\   | '_ \\  | |\n" +
                "§4  ____) | | |_) | | (_| | | (__  |  __/ | |____  | |_| | | |_) | |  __/  / ____ \\  | |_) | | |\n" +
                "§4 |_____/  | .__/   \\__,_|  \\___|  \\___|  \\_____|  \\__,_| |_.__/   \\___| /_/    \\_\\ | .__/  |_|\n" +
                "§4          | |                                                                      | |        \n" +
                "§4          |_|                                                                      |_|        ");
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         L'Api viens de ce §4stop§7 !                §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         Developpeur: §2Mathis_Bruel               §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-         Pour: §2SpaceCube                         §7-");
        Bukkit.getServer().getConsoleSender().sendMessage("§4---------------------------------------------------");
    }

    public static Main getApi() {
        return instance;
    }
    public static DbManageur getDbManageur() {
        return dbmanageur;
    }
}
