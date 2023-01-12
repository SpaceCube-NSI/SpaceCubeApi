package fr.Mathis_Bruel.spacecube.spacecubeapi.api.games;

public class Stats {

  @Getter private Game game;
  @Getter @Setter private int kills;
  @Getter @Setter private int death;
  @Getter @Setter private float ratioKillsDeath;
  @Getter @Setter private int games;
  @Getter @Setter private int wins;
  @Getter @Setter private int loses;
  @Getter @Setter private float ratioWinsLoses;
  @Getter @Setter private HashMapo<Object, Object> divers;
  

  public Stats(){
    
  }

  public void addKills(int kills){
    this.kills += kills;
  }
  public void removeKills(int kills){
    this.kills -= kills;
  }

  public void addGames(int gamesPlayed){
    this.games += gamesPlayed;
  }
  public void removeGames(int gamesPlayed){
    this.games -= gamesPlayed;
  }

  public void addWins(int wins){
    this.wins += wins;
  }
  public void removeWins(int wins){
    this.wins -= wins;
  }


  
}
