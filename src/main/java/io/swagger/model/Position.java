package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@Validated
public class Position {

	@JsonProperty("vehicle")
	private String vehicle = null;

	@JsonProperty("latitude")
	private BigDecimal latitude = null;

	@JsonProperty("longitude")
	private BigDecimal longitude = null;

	public Position vehicle(String vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	/**
	 * Get vehicle
	 * 
	 * @return vehicle
	 **/
	@ApiModelProperty(value = "")
	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Position latitude(BigDecimal latitude) {
		this.latitude = latitude;
		return this;
	}

	/**
	 * Get latitude
	 * 
	 * @return latitude
	 **/
	@ApiModelProperty(value = "")
	@Valid
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Position longitude(BigDecimal longitude) {
		this.longitude = longitude;
		return this;
	}

	/**
	 * Get longitude
	 * 
	 * @return longitude
	 **/
	@ApiModelProperty(value = "")
	@Valid
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

}
