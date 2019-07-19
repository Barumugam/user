package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the frequency_master database table.
 * 
 */
@Entity
@Table(name="frequency_master")
@NamedQuery(name="FrequencyMaster.findAll", query="SELECT f FROM FrequencyMaster f")
public class FrequencyMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_FREQUENCY_MASTER_ID")
	private String autogenFrequencyMasterId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="DURATION_DAYS_COUNT")
	private String durationDaysCount;

	@Column(name="FREQUENCY_NAME")
	private String frequencyName;

	@Column(name="REC_ADD_DT")
	private Timestamp recAddDt;

	@Column(name="REC_UPDATE_DT")
	private Timestamp recUpdateDt;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public FrequencyMaster() {
	}

	public String getAutogenFrequencyMasterId() {
		return this.autogenFrequencyMasterId;
	}

	public void setAutogenFrequencyMasterId(String autogenFrequencyMasterId) {
		this.autogenFrequencyMasterId = autogenFrequencyMasterId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDurationDaysCount() {
		return this.durationDaysCount;
	}

	public void setDurationDaysCount(String durationDaysCount) {
		this.durationDaysCount = durationDaysCount;
	}

	public String getFrequencyName() {
		return this.frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public Timestamp getRecAddDt() {
		return this.recAddDt;
	}

	public void setRecAddDt(Timestamp recAddDt) {
		this.recAddDt = recAddDt;
	}

	public Timestamp getRecUpdateDt() {
		return this.recUpdateDt;
	}

	public void setRecUpdateDt(Timestamp recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}