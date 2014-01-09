package com.caved_in.blockregeneration;

import com.caved_in.blockregeneration.listeners.BukkitListeners;
import com.caved_in.commons.threading.RunnableManager;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 9:49 AM
 */
public class BlockRegeneration extends JavaPlugin {

	public static RunnableManager runnableManager;

	public void onEnable() {
		runnableManager = new RunnableManager(this);
		new BukkitListeners(this);
		//Todo Register listeners, threads, and other intializers
	}

	public void onDisable() {
		HandlerList.unregisterAll(this);
		getServer().getScheduler().cancelTasks(this);
	}

}
