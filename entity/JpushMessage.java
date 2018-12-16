package com.fairymo.macrunnerpickupsystem.entity;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JpushMessage implements Serializable {
	@SerializedName("runner_pickup_code")
	private List<String> runnerPickupCodes;
	@Nullable
	@SerializedName("runner_state")
	private String runnerState;

	public List<String> getRunnerPickupCodes() {
		return runnerPickupCodes;
	}

	public void setRunnerPickupCodes(List<String> runnerPickupCodes) {
		this.runnerPickupCodes = runnerPickupCodes;
	}

	@Nullable
	public String getRunnerState() {
		return runnerState;
	}

	public void setRunnerState(@Nullable String runnerState) {
		this.runnerState = runnerState;
	}

	@Override
	public String toString() {
		return "JpushMessage{" +
			"runnerPickupCodes=" + runnerPickupCodes +
			", runnerState='" + runnerState + '\'' +
			'}';
	}
}
