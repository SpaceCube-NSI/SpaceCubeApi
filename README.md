# SpaceCubeApi
Documentation complète de la librerie SpaceCube

---

## Présentation

La librerie de SpaceCube est une librerie qui permet de gérer les données de SpaceCube. 
Grâce à cette librerie, vous pouvez gérer les statistiques de chaque joueur en fonction du mode de jeu.
La librerie permet la connection à la base de données de SpaceCube et la gestion de celle-ci.

---

## Games

### Introduction

La class Games est une énumération qui permet de définir les différents modes de jeu de SpaceCube.

```java
public enum Games {
    BedWars("BedWars", "[BedWars]", "&3"),
    Practice("Practice", "[Practice]", "&b"),
    DeathRun("DeathRun", "[DeathRun]", "&c"),
    Thimbles("Thimbles", "[Thimbles]", "&6"),;

}
```

Il y a 4 modes de jeu : BedWars, Practice, DeathRun et Thimbles.
Chaque mode de jeu a pour attribut :
- Un nom
- Un préfixe
- Une couleur

### Utilisation

Plusieurs fonctionnalités sont disponibles pour la class Games.

#### getName()

Cette fonction permet de récupérer le nom du mode de jeu.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getName());
}
```

#### getPrefix()

Cette fonction permet de récupérer le préfixe du mode de jeu. Cela permet de récupérer le préfixe du mode de jeu pour l'afficher dans un chat en fonction du mode de jeu.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getPrefix());
}
```

#### getChatColor()

Cette fonction permet de récupérer la couleur du mode de jeu. Cela permet de récupérer la couleur du mode de jeu pour permettre de personnaliser le chat en fonction du mode de jeu.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getChatColor());
}
```

#### getStats(String uuid)

Cette fonction permet de retourner les statistique d'un joueur en particulier et en fonction du mode de jeu en question.
Elle demande en paramètre l'uuid du joueur en String.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getStats("uuid"));
}
```

#### getStats(Player player)

Cette fonction permet de retourner les statistique d'un joueur en particulier et en fonction du mode de jeu en question.
Elle demande en paramètre le joueur en question.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getStats(player));
}
```

#### getStats(UUID uuid)

Cette fonction permet de retourner les statistique d'un joueur en particulier et en fonction du mode de jeu en question.
Elle demande en paramètre l'uuid du joueur en UUID.

```java
public void test() {
    Games game = Games.BedWars;
    System.out.println(game.getStats(uuid));
}
```

---

## Statistiques

### Introduction

La class Stats est une class qui permet de gérer les statistiques d'un joueur en fonction du mode de jeu.

```java

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
}
```

Chaque statistique a pour attribut :
- Le mode de jeu
- L'uuid du joueur
- Le nombre de kills
- Le nombre de morts
- Le ratio kills/morts
- Le nombre de parties jouées
- Le nombre de parties gagnées
- Le nombre de parties perdues
- Le ratio parties gagnées/parties perdues
- Un HashMap qui permet de stocker des données diverses

### Utilisation

Plusieurs fonctionnalités sont disponibles pour la class Stats.

> La class possède un getter, setter, adder et un remover pour chaque attribut.

#### updateRatioKillsDeath()

Cette fonction permet de mettre à jour le ratio kills/morts.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.updateRatioKillsDeath();
}
```

#### updateRatioWinsLoses()

Cette fonction permet de mettre à jour le ratio parties gagnées/parties perdues.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.updateRatioWinsLoses();
}
```

#### updateRatios()

Cette fonction permet de mettre à jour les ratios kills/morts et parties gagnées/parties perdues.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.updateRatios();
}
```

#### reset()

Cette fonction permet de réinitialiser les statistiques du joueur.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.reset();
}
```

#### updateStats()

Cette fonction permet de mettre à jour dans la base de données les statistiques du joueur.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.updateStats();
}
```

> Si le joueur n'a pas de statistiques, elles seront créées.

#### init()

Cette fonction est **très** importante, elle permet d'initialiser les statistiques d'un joueur dans une nouvelle variable ! Elle attribue à la variable `Stats` les informations qui se trouve dans la base de données.

```java
public void test() {
    Stats stats = new Stats(Games.BedWars, uuid);
    stats.init();
}
```

> Si le joueur n'a pas de statistiques, elles seront créées.

### Exemple

Voici un exemple coqueret de l'utilisation des statistiques.

```java
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

        Stats stats = Games.BedWars.getStats((Player) sender);
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
        sender.sendMessage("Loses: " + stats.getLoses()); // 10

        return true;
    }
}
```

---


