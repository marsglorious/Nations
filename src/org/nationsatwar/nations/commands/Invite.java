package org.nationsatwar.nations.commands;

import org.bukkit.command.CommandSender;
import org.nationsatwar.nations.Nations;

public class Invite extends NationsCommand {

	protected Invite(CommandSender commandSender, String commandLabel, String[] command) {
		super(commandSender, commandLabel, command);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// --invite || -invite help
		if(command.length == 0 || command[0].equalsIgnoreCase("help")) {
			this.helpText(commandSender, "e.g. '/naw invite player'", "Invites player to join your nation. Nation permission may apply.");
			return;
		}
		
		if(!(plugin instanceof Nations)) {
			return;
		}
		Nations nations = (Nations) plugin;
		
		// -invite [String: userName]
		if(!nations.userManager.exists(command[0])) {
			this.errorText(commandSender, "That user does not exist or is not registered!", null);
			return;
		}
		
		this.successText(commandSender, "This is as far as things go right now, should work soon!", null);
		return;
		
	}

}