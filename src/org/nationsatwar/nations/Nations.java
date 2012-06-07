package org.nationsatwar.nations;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.nationsatwar.listeners.NationsUserListener;
import org.nationsatwar.nations.commands.CommandManager;
import org.nationsatwar.nations.managers.NationManager;
import org.nationsatwar.nations.managers.UserManager;

public class Nations extends JavaPlugin {
	public NationManager 	nationManager 	= new NationManager(this);
	public UserManager		userManager		= new UserManager(this);
	
	public CommandManager	command			= new CommandManager(this);
	
	public String getVersion() {
		return this.getDescription().getVersion();
	}
	
	public void onEnable() {
		new NationsUserListener(this);
		this.getConfig().options().copyDefaults(true);
		
		this.getLogger().info(this.getVersion()+ " Loaded");
		
	}
	
	public void onDisable() {
		this.saveConfig();
		this.getLogger().info(this.getVersion()+ " Unloaded");
	}
	
	public void reload(CommandSender sender) {

	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return command.execute(sender, cmd, commandLabel, args);
	}
	
	public void messageAll(String message) {
		this.getServer().broadcastMessage(ChatColor.DARK_RED + "["+this.getName()+"]: " + message);
	}
	
	public void notifyAll(String message) {
		this.getServer().broadcastMessage(ChatColor.DARK_RED + "["+this.getName()+"]: " + ChatColor.DARK_AQUA + message);
	}
}