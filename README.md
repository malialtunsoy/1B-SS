# Group Name: 1B-SS
Project Selection: Slay the Spire

Group Members
-
* Cem Cebeci          21703377
* Can Cebeci          21703376
* Gizem Karal         2173094
* Gökberk Boz         21602558
* Sena Sultan Karataş 21604078
* Mehmet Ali Altunsoy 21702531

Project Description
-
Slay the spire is a roguelike deck building game where the goal is to follow a map through three levels while fighting various monsters and collecting or upgrading the deck.

The game includes permanent death, that is, it is played in “runs” where each run begins without any upgrades and is independent from the success or failure of the previous runs. Each run ends when the player dies (their HP reaches 0) and then a new run can be started.

A character (and thus the state of a run) is defined by the following:
* A deck, which is a collection of cards
* An amount of gold
* A set of relics, which grant passive bonuses
* Amounts of maximum HP and current HP 
* A set of potions, which are single-use bonuses
* The map and the current position

Each run begins with the selection of a character class and the random generation of a map. The player, then, begins the game at their maximum HP with a deck and a starting relic determined by their class and no potions.

The player has to repeatedly choose a vertex on the map and complete the action required by it until either the final boss is defeated or the player has lost all of their HP.

The types of vertices on the map are:
* Combat: The player engages in combat where they have to use their deck and inventory to defeat a number of foes. They either lose the game in combat or they are rewarded (with new cards, gold, relics or potions) upon victory.
* Elite combat: Tougher foes that grant better relics.
* Boss: The toughest combats, one at the end on each act.
* Merchant: The player spends their gold to upgrade their inventory.
* Treasure room: The player clicks on a treasure chest and earns a relic.
* Rest: The player may choose to heal some of their missing hp or upgrade a card.
* Mystery vertex: These vertices are revealed upon choosing them. The player may be faced with combat, a merchant, a treasure room or a series of choices that affect the character state.

Combat consists of turns. In each turn, the player draws a number of cards and has an amount of energy. Each card has an energy cost and a set of effects. The player can play any number of cards within their energy limit and then choose to end their turn. The player is aware of the intentions of their foes during their turn. When the turn ends, the remaining cards in the player’s hand are discarded and the foes realise their declared intentions. Both the player and the foes may be affected by a variety of status effects during combat.


When the game ends, the player may be rewarded with permanent unlockables that modify the set of items the player may come upon in future runs.


Possible Extensions:
-
* Ability to play the game cooperatively.
* Giving the cards as a gift and exchanging them with friends.
* As a new game mechanic, the player can call the aid of a companion/pet which helps through the fights or runs.
* Graphical features can be added to the character and themes to the background as rewards for certain quests or achievement milestones.
* Like Achievements, there can be run-specific quests which rewards the player with some buffs or fight-skip/flee options.
* Cheat Mode.


Deliverables
Iteration 1 Project Analysis Report : https://docs.google.com/document/d/1aetvog87XzTkCs14IeTxf2skNKtrWLwBpwNMR0wfbOg/edit?usp=sharing
to be added

Iteration 1 Design Report: https://docs.google.com/document/d/1kwibgx4kkgXqmPCu-a8-ILdoIrlPrbM-X_v1eSp77J0/edit?usp=sharing
Iteration 2 Project Analysis Report : https://docs.google.com/document/d/1sT4ElzfKJwLIgR54Z0B_NXIvwX9fkvzR1KiuaXu0_nU/edit#
Iteration 2 Design Report:https://docs.google.com/document/d/1jSxWI_eGJieWRNiva-_XaLx5XEFqRA3vK6t2S0OzzG8/edit#heading=h.y71qxf2foe2b
