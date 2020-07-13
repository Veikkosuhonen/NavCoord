package me.vesuvesu.navcoord

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerMoveEvent
import java.util.UUID
import kotlin.collections.HashMap

class CoordFormatter(playerPreferences: HashMap<UUID, NavCoord.DisplayStyle>, playerDestinations: HashMap<UUID, Location>): Listener {
    val preferences = playerPreferences
    val destinations = playerDestinations

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val location = player.location
        when (preferences[player.uniqueId]) {
            NavCoord.DisplayStyle.FULL_ABSOLUTE -> player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent("" +
                            "x:${location.blockX} " +
                            "y:${location.blockY} " +
                            "z:${location.blockZ}"))

            NavCoord.DisplayStyle.SIMPLE_ABSOLUTE -> player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent("" +
                            "${location.blockX} " +
                            "${location.blockZ}"))

            NavCoord.DisplayStyle.FULL_RELATIVE -> player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent("" +
                            "x:${location.blockX - (destinations[player.uniqueId]?.blockX ?: 0)} " +
                            "y:${location.blockY - (destinations[player.uniqueId]?.blockY ?: 0)} " +
                            "z:${location.blockX - (destinations[player.uniqueId]?.blockZ ?: 0)}"))

            NavCoord.DisplayStyle.SIMPLE_RELATIVE -> player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent("" +
                            "${location.blockX - (destinations[player.uniqueId]?.blockX ?: 0)} " +
                            "${location.blockX - (destinations[player.uniqueId]?.blockZ ?: 0)}"))
        }

    }

    @EventHandler
    fun onPlayerLogin(event: PlayerLoginEvent) {
        preferences[event.player.uniqueId] = NavCoord.DisplayStyle.SIMPLE_RELATIVE
    }
}