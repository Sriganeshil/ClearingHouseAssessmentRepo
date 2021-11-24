package com.clearinghouse.fileprocessing.vo;

public class BankInfo {
	@Override
	public String toString() {
		return "BankInfo [bankName=" + bankName + ", type=" + type + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}
	String bankName;
	String type;
	String city;
	String state;
	String zipCode;
	String displayData;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	private String rowDataStr ;

	public String getRowDataStr() {
		
		return rowDataStr;
	}

	public void setRowDataStr(String rowDataStr) {
		this.rowDataStr = rowDataStr;
	}
	
	public String getRowDataStrFormattedForSearch() {
//		String lowerCase = rowDataStr.toLowerCase();
		String updateValue = rowDataStr.replace(" ","");
		
		return updateValue.toLowerCase() ;
	}
	
	public String getDisplayData() {
		return  displayData;
	}
	

}
