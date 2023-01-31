package fr.Mathis_Bruel.spacecube.spacecubeapi;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Games;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        sender.sendMessage("§a§l[SpaceCubeAPI] §aTest command executed !");


        
       /*Stats stats = Games.BedWars.getStats((Player) sender);
        stats.init();
        sender.sendMessage("Kills: " + stats.getKills()); // 0
        sender.sendMessage("Deaths: " + stats.getDeath()); // 0
        sender.sendMessage("Wins: " + stats.getWins()); // 0
        sender.sendMessage("Loses: " + stats.getLoses()); // 0
        stats.setKills(10);
        stats.setDeath(10);
        stats.setWins(10);
        stats.setLoses(10);
        stats.updateStats();
        sender.sendMessage("Kills: " + stats.getKills()); // 10
        sender.sendMessage("Deaths: " + stats.getDeath()); // 10
        sender.sendMessage("Wins: " + stats.getWins()); // 10
        sender.sendMessage("Loses: " + stats.getLoses()); // 10*/

        return true;
    }
}
