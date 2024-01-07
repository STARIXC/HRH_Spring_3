package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StatutoryDetailsDTO {
	private String kraPin;
	private String nssfNo;
	private String nhifNo;
	private String certGoodConductNo;
	private String helbClearanceNo;
	private Integer helbBeneficiary;
}
