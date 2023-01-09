package fr.Mathis_Bruel.spacecube.spacecubeapi;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.Rank;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.SpaceCubePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Main.getApi().ranks.forEach((s, ranks) -> {
            sender.sendMessage(s + " : " + ranks.getName() + " " + ranks.isDefault());
        });

        SpaceCubePlayer scp = SpaceCubePlayer.getSCP((Player) sender);
        System.out.println(scp.getRank().getName());
        Ranks rank = Ranks.getRank("Admin");
        scp.setRank(rank);
        scp.updatePlayer();
        System.out.println(scp.getRank().getName());


        return true;
    }
}
