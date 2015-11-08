package com.beyole.bean;

import android.app.Application;

public class GlobalParameterApplication extends Application {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
