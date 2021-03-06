package org.nationsatwar.nations.managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.bukkit.plugin.PluginBase;
import org.nationsatwar.nations.Nations;
import org.nationsatwar.nations.objects.NationsObject;
import org.nationsatwar.nations.objects.Rank;
import org.nationsatwar.nations.objects.Rank.RankType;

public class RankManager extends NationsManagement {
	private HashMap<Integer, Rank> rankMap = new HashMap<Integer, Rank>();

	public RankManager(PluginBase plugin) {
		super(plugin);
	}
	
	@Override
	public void loadAll() {
		if(!(plugin instanceof Nations)) {
			return;
		}
		Nations nations = (Nations) plugin;
		
		rankMap.clear();
		for (NationsObject obj : nations.database.gatherDataset(new Rank(0, null, null))) {
			Rank object = (Rank) obj;
			if (!rankMap.containsKey(object.getID()))
				rankMap.put(object.getID(), object);
		}
		
		boolean founder = false;
		boolean recruit = false;
		for(Rank rank : this.rankMap.values()) {
			if(rank.getType().equals(RankType.FOUNDER)) {
				founder = true;
			}
			if(rank.getType().equals(RankType.RECRUIT)) {
				recruit = true;
			}
		}
		if(!founder) {
			this.createRank("Founder", RankType.FOUNDER);
		}
		if(!recruit) {
			this.createRank("Recruit", RankType.RECRUIT);
		}
	}

	@Override
	public void saveAll() {
		if(!(plugin instanceof Nations)) {
			return;
		}
		Nations nations = (Nations) plugin;
		
		for (Rank object : rankMap.values()) {
			nations.database.save(object);
		}
	}

	@Override
	public void deleteAll() {
		if(!(plugin instanceof Nations)) {
			return;
		}
		Nations nations = (Nations) plugin;
		
		for (Rank object : rankMap.values()) {
			nations.database.delete(object);
		}
		rankMap.clear();
	}
	
	public Rank createRank(String name, RankType type) {
		int newKey = 0; 
		try {
			newKey = Collections.max(rankMap.keySet())+1;
		} catch(NoSuchElementException e) {
			newKey = 0;
		}
		Rank newRank = new Rank(newKey, name, type);
		if(this.addRank(newRank)) {
			return newRank;
		}
		return null;
	}

	private boolean addRank(Rank newRank) {
		if(!rankMap.containsKey(newRank.getID())) {
			this.rankMap.put(newRank.getID(), newRank);
			return true;
		}
		return false;
	}

	public Rank getFounderRank() {
		for(Rank rank : this.rankMap.values()) {
			if(rank.getName().equalsIgnoreCase("Founder")) {
				return rank;
			}
		}
		return null;
	}
	
	public Rank getRecruitRank() {
		for(Rank rank : this.rankMap.values()) {
			if(rank.getName().equalsIgnoreCase("Recruit")) {
				return rank;
			}
		}
		return null;
	}
	
	
/*
	public ArrayList<Rank> getRanks(User user) {
		ArrayList<Rank> newList = new ArrayList<Rank>();
		for(Rank rank : this.rankMap.values()) {
			if(rank.getInvitee() == user) {
				newList.add(rank);
			}
		}
		return newList;
	}
*/
}
