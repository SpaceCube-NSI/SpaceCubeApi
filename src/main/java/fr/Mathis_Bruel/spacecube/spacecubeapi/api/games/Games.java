package fr.Mathis_Bruel.spacecube.spacecubeapi.api.games;

import org.bukkit.entity.Player;

import java.util.UUID;

public enum Games {
    BedWars("BedWars", "[BedWars]", "&3"),
    Practice("Practice", "[Practice]", "&b"),
    DeathRun("DeathRun", "[DeathRun]", "&c"),
    Thimbles("Thimbles", "[Thimbles]", "&6"),;

    private String Name;
    private String Prefix;
    private String ChatColor;

    Games(String name, String s, String s1) {
        this.Name = name;
        this.Prefix = s;
        this.ChatColor = s1;
    }

    public String getName(){
        return this.Name;
    }
    public void setName(String name){
        this.Name = name;
    }

    public String getPrefix(){
        return this.Prefix;
    }
    public void setPrefix(String prefix){
        this.Prefix = prefix;
    }

    public String getChatColor(){
        return this.ChatColor;
    }
    public void setChatColor(String chatColor){
        this.ChatColor = chatColor;
    }

    public Stats getStats(String uuid){
        Stats stats = new Stats(this, UUID.fromString(uuid));
        stats.init();
        return stats;
    }
    public Stats getStats(Player player){
        Stats stats = new Stats(this, player.getUniqueId());
        stats.init();
        return stats;
    }
    public Stats getStats(UUID uuid){
        Stats stats = new Stats(this, uuid);
        stats.init();
        return stats;
    }



}


