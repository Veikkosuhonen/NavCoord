package me.vesuvesu.navcoord

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class CoordFormatter: Listener  {
    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val regex = Regex("([Mm]ycoord(s?))")
        if (event.message.contains(regex)) {
            val location = event.player.location
            val formattedCoord = ChatColor.LIGHT_PURPLE.toString() + "${location.x.toInt()} ${location.z.toInt()}" + ChatColor.RESET
            val newMessage = event.message.replace(regex, formattedCoord)
            event.message = newMessage

            event.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, ComponentBuilder("hello there, "+event.player.name).color(ChatColor.DARK_RED).create()[0])

        }
    }
}