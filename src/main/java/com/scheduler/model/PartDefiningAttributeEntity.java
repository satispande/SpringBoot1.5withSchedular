package com.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Solr_Parts_Defining_Att")
public class PartDefiningAttributeEntity {

	@Id
	@Column(name = "id")
	private Long id;

    @Column(name = "defAttName")
    private String definingAttributeName;

    @Column(name = "defAttValue")
    private String definingAttributeValue;
    
    @ManyToOne
    @JoinColumn(name = "partId", referencedColumnName = "id", insertable = false, updatable = false)
    private PartEntity part;

	public PartEntity getPart() {
		return part;
	}

	public void setPart(PartEntity part) {
		this.part = part;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDefiningAttributeName() {
		return definingAttributeName;
	}

	public void setDefiningAttributeName(String definingAttributeName) {
		this.definingAttributeName = definingAttributeName;
	}

	public String getDefiningAttributeValue() {
		return definingAttributeValue;
	}

	public void setDefiningAttributeValue(String definingAttributeValue) {
		this.definingAttributeValue = definingAttributeValue;
	}

}