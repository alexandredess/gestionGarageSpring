package com.formation.parking.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RecordEntity {

	@JsonProperty(value="fields")
	private FieldEntity fields;

	public FieldEntity getFields() {
		return fields;
	}

	public void setFields(FieldEntity fields) {
		this.fields = fields;
	}
	
	
}
