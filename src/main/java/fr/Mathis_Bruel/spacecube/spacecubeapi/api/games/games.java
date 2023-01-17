package fr.Mathis_Bruel.spacecube.spacecubeapi.api.games;

public enum Games {
    BedWars,
    Practice,
    DeathRun,
    Thimble;

    private String Name;
    private String Prefix;
    private String ChatColor;
    private Stats Stats;

    public String getName(){
        return this.Name;
    }

    public String getPrefix(){
        return this.Prefix;
    }

    public String getChatColor(){
        return this.ChatColor;
    }

    public Stats getStats(){
        return this.Stats;
    }


}


