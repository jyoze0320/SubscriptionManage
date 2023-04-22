package com.app.subscribing;

public class SubscribingDTO {
	private int id;
	private String userId;
	private int paySysId;
	private int numOfShare;

	public SubscribingDTO(int id, String userId, int paySysId, int numOfShare) {
		super();
		this.id = id;
		this.userId = userId;
		this.paySysId = paySysId;
		this.numOfShare = numOfShare;
	}

	public SubscribingDTO(String userId, int paySysId, int numOfShere) {
		super();
		this.userId = userId;
		this.paySysId = paySysId;
		this.numOfShare = numOfShere;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPaySysId() {
		return paySysId;
	}

	public void setPaySysId(int paySysId) {
		this.paySysId = paySysId;
	}

	public int getNumOfShare() {
		return numOfShare;
	}

	public void setNumOfShare(int numOfShare) {
		this.numOfShare = numOfShare;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "SubscribingDTO [id=" + id + ", userId=" + userId + ", paySysId=" + paySysId + ", numOfShare="
				+ numOfShare + "]";
	}

}
