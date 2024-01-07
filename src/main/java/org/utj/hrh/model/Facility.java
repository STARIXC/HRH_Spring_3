package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subpartnera") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility {
	@Id
	@Column(name = "sub_partner_id")
	private Integer subPartnerId;
	
	@Column(name = "sub_partner_nom")
	private String subPartnerName;
	
	@OneToOne
	@JoinColumn(name = "districtid")
	private SubCounty subCounty;
	
	@Column(name = "centre_sante_id")
	private Integer centreSanteId;
	
	@Column(name = "SP_ID")
	private Integer spId;
	
	@Column(name = "HTC")
	private Integer htc;
	
	@Column(name = "FP")
	private Integer fp;
	
	@Column(name = "PMTCT")
	private Integer pmtct;
	
	@Column(name = "EID")
	private Integer eid;
	
	@Column(name = "ART")
	private Integer art;
	
	@Column(name = "VMMC")
	private Integer vmmc;
	
	@Column(name = "Nutrition")
	private Integer nutrition;
	
	@Column(name = "GSN")
	private Integer gsn;
	
	@Column(name = "Lab")
	private Integer lab;
	
	@Column(name = "FP_Integration")
	private Integer fpIntegration;
	
	@Column(name = "Care_DSD")
	private Integer careDsd;
	
	@Column(name = "ART_DSD")
	private Integer artDsd;
	
	@Column(name = "Maternity")
	private Integer maternity;
	
	@Column(name = "ART_Support")
	private String artSupport;
	
	@Column(name = "PMTCT_Support")
	private String pmtctSupport;
	
	@Column(name = "HTC_Support1")
	private String htcSupport1;
	
	@Column(name = "KMMP")
	private Integer kmmp;
	
	@Column(name = "Gender")
	private Integer gender;
	
	@Column(name = "PEP")
	private Integer pep;
	
	@Column(name = "prep")
	private Integer prep;
	
	@Column(name = "Blood_Safety")
	private Integer bloodSafety;
	
	@Column(name = "TB")
	private Integer tb;
	
	@Column(name = "Burdencat", columnDefinition = "TEXT")
	private String burdenCategory;
	
	@Column(name = "Owner", columnDefinition = "TEXT")
	private String owner;
	
	@Column(name = "Type", columnDefinition = "TEXT")
	private String type;
	
	@Column(name = "tibu_name", columnDefinition = "TEXT")
	private String tibuName;
	
	@Column(name = "ART_highvolume")
	private Integer artHighVolume;
	
	@Column(name = "HTC_highvolume")
	private Integer htcHighVolume;
	
	@Column(name = "PMTCT_highvolume")
	private Integer pmtctHighVolume;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "constituency")
	private String constituency;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "ART_Outreach", columnDefinition = "varchar(45) default '0'")
	private String artOutreach;
	
	@Column(name = "IPD")
	private Integer ipd;
	
	@Column(name = "all_highvolume")
	private Integer allHighVolume;
	
	@Column(name = "PNS")
	private Integer pns;
	
	@Column(name = "Link_desks")
	private Integer linkDesks;
	
	@Column(name = "Male_clinics")
	private Integer maleClinics;
	
	@Column(name = "Adolescent_clinics")
	private Integer adolescentClinics;
	
	@Column(name = "Viremia_clinics")
	private Integer viremiaClinics;
	
	@Column(name = "EMR_Sites")
	private Integer emrSites;
	
	@Column(name = "Supportlevel_HTS")
	private String supportLevelHTS;
	
	@Column(name = "Supportlevel_ART")
	private String supportLevelART;
	
	@Column(name = "surge_site")
	private Integer surgeSite;
	
	@Column(name = "active")
	private Integer active;
	
	@Column(name = "updatetimestamp")
	private LocalDateTime updateTimestamp;
	
	@Column(name = "sto")
	private String sto;
	
	@Column(name = "datimward")
	private String datimWard;
	
	@Column(name = "datimid")
	private String datimId;
	
	@Column(name = "datimname")
	private String datimName;
	
	@Column(name = "vl_report_source", columnDefinition = "varchar(45) default 'vl_website'")
	private String vlReportSource;
	
	@Column(name = "khisid")
	private String khisId;
	
	@Column(name = "khisname")
	private String khisName;
	
	@Column(name = "ART_Categorization")
	private String artCategorization;
	
	@Column(name = "old_surge_site")
	private String oldSurgeSite;
	
	@Column(name = "FPT", columnDefinition = "int default 0")
	private Integer fpt;
	
	@Column(name = "datim_userid")
	private String datimUserId;
	
	@Column(name = "JMW", columnDefinition = "varchar(45) default '0'")
	private String jmw;
	
	@Column(name = "stockv", columnDefinition = "varchar(45) default '0'")
	private String stockV;
	
	@Column(name = "retentiona")
	private String retentionA;
	
	@Column(name = "mdt")
	private String mdt;
	
	@Column(name = "otz")
	private String otz;
	
	@Column(name = "ehts", columnDefinition = "varchar(45) default '0'")
	private String ehts;
	
	@Column(name = "il", columnDefinition = "varchar(45) default '0'")
	private String il;
	
	@Column(name = "ushauri", columnDefinition = "varchar(45) default '0'")
	private String ushauri;
	
	@Column(name = "cons_prep", columnDefinition = "varchar(45) default '0'")
	private String consPrep;
	
	@Column(name = "labmanifest", columnDefinition = "varchar(45) default '0'")
	private String labManifest;
	
	@Column(name = "emr_type")
	private String emrType;
	
	@Column(name = "rmcah", columnDefinition = "varchar(45) default '0'")
	private String rmcah;

//	@OneToMany(mappedBy = "facility",fetch = FetchType.LAZY)
//	@Transient
//	private List<EmployeeFacility> employeeFacilities;


}
