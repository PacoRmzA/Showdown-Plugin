# Speedrun-showdown

This Minecraft plugin is my version of the Showdown plugin that appears in Twitch Streamer 
[Nerdi's](https://www.twitch.tv/nerdi) tournament, also based on the [Manhunt plugin by Eugene
Chau](https://github.com/echau01/Minecraft-Manhunt). The plugin works on Minecraft 
versions 1.16.1 to 1.16.4 and was built using the Spigot 1.16.4 API.

## Commands
- ```/addhunter <username> <team>```: Add a player to a team, giving them a compass.
- ```/removehunter <username>```: Remove a player from the team they are in.
- ```/getplayers <team>```: Get the usernames of all players within a team.

## Features

When assigned to a team, they receive a compass, that allows them to track the players in their own
team by right-clicking and the players in the other team by left-clicking. The compass is updated
whenever a target moves and the person with the compass has it in their main or off-hand. The compass
can track players in all dimensions, as long as the player tracked is in the same dimension.

## Installation Instructions

To add the plugin to your server, download the JAR file in the targets folder and place it in your
server's plugins folder.
