package com.cqut.test;

public class boss {
   private office office;
   private String carId;
   private String carColor;
	public office getOffice() {
		return office;
	}
	public void setOffice(office office) {
		this.office = office;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String toString()
	{
		return "this boss has "+"the car is "+carColor+" with "+
	    carId+" and in office :"+getOffice();
	}

}
