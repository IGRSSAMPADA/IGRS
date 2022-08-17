package com.wipro.igrs.useracctmgmt.dto;

import java.util.ArrayList;

public class UserAccntUnlockDTO {

	
	public ArrayList userDtlList;
	public String userId;
	public String lockedUserStatus;
	public String getLockedUserStatus() {
		return lockedUserStatus;
	}
	public void setLockedUserStatus(String lockedUserStatus) {
		this.lockedUserStatus = lockedUserStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLockedUserId() {
		return lockedUserId;
	}
	public void setLockedUserId(String lockedUserId) {
		this.lockedUserId = lockedUserId;
	}

	public String lockedUserId;
	public ArrayList getUserDtlList() {
		return userDtlList;
	}
	public void setUserDtlList(ArrayList userDtlList) {
		this.userDtlList = userDtlList;
	}
	
	public String userLockedFlag;
	public String getUserLockedFlag() {
		return userLockedFlag;
	}
	public void setUserLockedFlag(String userLockedFlag) {
		this.userLockedFlag = userLockedFlag;
	}
}
