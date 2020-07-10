package me.vesuvesu.navcoord
import org.bukkit.plugin.java.JavaPlugin

class NavCoord: JavaPlugin() {

    override fun onEnable() {
        this.server.pluginManager.registerEvents(CoordFormatter(), this)
    }

    override fun onDisable() {

    }
}