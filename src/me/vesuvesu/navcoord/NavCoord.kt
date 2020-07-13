package me.vesuvesu.navcoord
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

class NavCoord: JavaPlugin() {
    val playerPreferences = HashMap<UUID, DisplayStyle>()
    val playerDestinations = HashMap<UUID, Location>()
    override fun onEnable() {
        val coordWatcher = CoordFormatter(playerPreferences, playerDestinations);
        this.server.pluginManager.registerEvents(coordWatcher, this)
        this.getCommand("navcoord")?.setExecutor(CommandHandler())
    }

    override fun onDisable() {

    }

    enum class DisplayStyle {
        FULL_ABSOLUTE,
        SIMPLE_ABSOLUTE,
        FULL_RELATIVE,
        SIMPLE_RELATIVE
    }
}