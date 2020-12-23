package com.janitha.designpatterns.builder;

public class House {
	
	private final String houseName;
	private final int windows;
	private final int doors;
	private final int rooms;
	private final int floors;
	private final boolean garage;
	private final boolean swimmingPool;
	private final String statueName;
	private final boolean garden;
	private final String heatingSystemName;
	private final boolean plumbing;
	private final boolean electricity;
	
	public House(Builder builder) {
		
		this.houseName = builder.houseName;
		this.windows = builder.windows;
		this.doors = builder.doors;
		this.rooms = builder.rooms;
		this.floors = builder.floors;
		this.garage = builder.garage;
		this.swimmingPool = builder.swimmingPool;
		this.statueName = builder.statueName;
		this.garden = builder.garden;
		this.heatingSystemName = builder.heatingSystemName;
		this.plumbing = builder.plumbing;
		this.electricity = builder.electricity;
		
	}
	
	static class Builder {
		
		private String houseName;
		private int windows;
		private int doors;
		private int rooms;
		private int floors;
		private boolean garage;
		private boolean swimmingPool;
		private String statueName;
		private boolean garden;
		private String heatingSystemName;
		private boolean plumbing;
		private boolean electricity;
		
		
		public House build() {
			return new House(this);
		}
		
		//If a property is compulsory to create the object, it should be declared within the constructor.
		public Builder(String houseName, int windows, int doors, int rooms, int floors) {
			
			this.houseName = houseName;
			this.windows = windows;
			this.doors = doors;
			this.rooms = rooms;
			this.floors = floors;
		}
		
		public Builder garage(Boolean garage) {
			this.garage = garage;
			return this;
		}
		
		public Builder swimmingPool(Boolean swimmingPool) {
			this.swimmingPool = swimmingPool;
			return this;
		}
		
		public Builder statueName(String statueName) {
			this.statueName = statueName;
			return this;
		}
		
		public Builder garden(Boolean garden) {
			this.garden = garden;
			return this;
		}
		
		public Builder heatingSystemName(String heatingSystemName) {
			this.heatingSystemName = heatingSystemName;
			return this;
		}
		
		public Builder plumbing(Boolean plumbing) {
			this.plumbing = plumbing;
			return this;
		}
		
		public Builder electricity(Boolean electricity) {
			this.electricity = electricity;
			return this;
		}
		
		
	}

	@Override
	public String toString() {
		return "House [houseName=" + houseName + ", windows=" + windows + ", doors=" + doors + ", rooms=" + rooms
				+ ", floors=" + floors + ", garage=" + garage + ", swimmingPool=" + swimmingPool + ", statueName="
				+ statueName + ", garden=" + garden + ", heatingSystemName=" + heatingSystemName + ", plumbing="
				+ plumbing + ", electricity=" + electricity + "]";
	}
	
	

}
