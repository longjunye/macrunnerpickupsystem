package com.fairymo.macrunnerpickupsystem.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shop implements Serializable {
	@SerializedName("shop_id")
	private int shopId;
	@SerializedName("shop_no")
	private String shopNo;
	@SerializedName("shop_name")
	private String shopName;
	@SerializedName("shop_name_en")
	private String shopNameEn;
	@SerializedName("shop_address")
	private String shopAddress;
	@SerializedName("shop_address_en")
	private String shopAddressEn;
	@SerializedName("shop_telephone")
	private String shopTelephone;
	@SerializedName("shop_compose_info")
	private String shopComposeInfo;
	@SerializedName("shop_state")
	private int shopState;
	@SerializedName("shop_activation")
	private String shopActivation;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNameEn() {
		return shopNameEn;
	}

	public void setShopNameEn(String shopNameEn) {
		this.shopNameEn = shopNameEn;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopAddressEn() {
		return shopAddressEn;
	}

	public void setShopAddressEn(String shopAddressEn) {
		this.shopAddressEn = shopAddressEn;
	}

	public String getShopTelephone() {
		return shopTelephone;
	}

	public void setShopTelephone(String shopTelephone) {
		this.shopTelephone = shopTelephone;
	}

	public String getShopComposeInfo() {
		return shopComposeInfo;
	}

	public void setShopComposeInfo(String shopComposeInfo) {
		this.shopComposeInfo = shopComposeInfo;
	}

	public int getShopState() {
		return shopState;
	}

	public void setShopState(int shopState) {
		this.shopState = shopState;
	}

	public String getShopActivation() {
		return shopActivation;
	}

	public void setShopActivation(String shopActivation) {
		this.shopActivation = shopActivation;
	}

	@Override
	public String toString() {
		return "Shop{" +
			"shopId=" + shopId +
			", shopNo='" + shopNo + '\'' +
			", shopName='" + shopName + '\'' +
			", shopNameEn='" + shopNameEn + '\'' +
			", shopAddress='" + shopAddress + '\'' +
			", shopAddressEn='" + shopAddressEn + '\'' +
			", shopTelephone='" + shopTelephone + '\'' +
			", shopComposeInfo='" + shopComposeInfo + '\'' +
			", shopState=" + shopState +
			", shopActivation='" + shopActivation + '\'' +
			'}';
	}
}
