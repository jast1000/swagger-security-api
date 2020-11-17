package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@Validated
public class VehiclesPositions {

	@JsonProperty("positions")
	@Valid
	private List<Position> positions = null;

	public VehiclesPositions positions(List<Position> positions) {
		this.positions = positions;
		return this;
	}

	public VehiclesPositions addPositionsItem(Position positionsItem) {
		if (this.positions == null) {
			this.positions = new ArrayList<Position>();
		}
		this.positions.add(positionsItem);
		return this;
	}

	/**
	 * Get positions
	 * 
	 * @return positions
	 **/
	@ApiModelProperty(value = "")
	@Valid
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

}
