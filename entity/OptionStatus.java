package com.fairymo.macrunnerpickupsystem.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OptionStatus implements Serializable {
	@SerializedName("pickup_codes")
	private List<String> pickupCodes;

	public List<String> getPickupCodes() {
		return pickupCodes;
	}

	public void setPickupCodes(List<String> pickupCodes) {
		this.pickupCodes = pickupCodes;
	}

	@Override
	public String toString() {
		return "OptionStatus{" +
			"pickupCodes=" + pickupCodes +
			'}';
	}
}
