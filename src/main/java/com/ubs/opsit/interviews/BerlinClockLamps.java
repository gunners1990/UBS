package com.ubs.opsit.interviews;

public enum BerlinClockLamps {
	YELLOW("Y"), RED("R"), OFF("O");
	
	private String displayValue;
	
	private BerlinClockLamps(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
