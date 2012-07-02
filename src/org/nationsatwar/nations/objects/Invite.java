package org.nationsatwar.nations.objects;

public class Invite extends NationsObject {
	private InviteType type;
	private Integer invitee;
	private Integer inviter;
	
	public Invite(int newId, InviteType newType, int newInviter, int newInvitee) {
		super(newId);
		this.type = newType;
		
		if(newType == InviteType.PLAYERNATION) {
			this.invitee = newInvitee;
			this.inviter = newInviter;
		}
	}
	
	public enum InviteType {
		PLAYERNATION,NATIONALLIANCE,TOWNNATION,
	}

	public int getInvitee() {
		return invitee;
	}
	
	public InviteType getType() {
		return this.type;
	}

	public String getNiceType() {
		if(type == InviteType.PLAYERNATION) {
			return "Nation";
		}
		return null;
	}

	public int getInviter() {
		return inviter;
	}

}
