package org.utj.hrh.dto;

import org.utj.hrh.model.County;

public class SubCountyDTO {
	private Integer districtId;
	private String districtName;
	private County county;
	
	public SubCountyDTO(Integer districtId, String districtName, County county) {
		this.districtId = districtId;
		this.districtName = districtName;
		this.county = county;
	}
	
	public Integer getDistrictId() {
		return districtId;
	}
	
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public County getCounty() {
		return county;
	}
	
	public void setCounty(County county) {
		this.county = county;
	}
}
