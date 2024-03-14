package com.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Solr_Parts_Descriptive_Att_Lang")
public class PartDescriptiveAttributeEntity {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "langId")
	private Long langId;
	
    @Column(name = "descAttName")
    private String descriptiveAttributeName;

    @Column(name = "descAttValue")
    private String descriptiveAttributeValue;

    @Column(name = "descAttMeasure")
    private String descriptiveAttributeMeasure;
    
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

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public String getDescriptiveAttributeName() {
		return descriptiveAttributeName;
	}

	public void setDescriptiveAttributeName(String descriptiveAttributeName) {
		this.descriptiveAttributeName = descriptiveAttributeName;
	}

	public String getDescriptiveAttributeValue() {
		return descriptiveAttributeValue;
	}

	public void setDescriptiveAttributeValue(String descriptiveAttributeValue) {
		this.descriptiveAttributeValue = descriptiveAttributeValue;
	}

	public String getDescriptiveAttributeMeasure() {
		return descriptiveAttributeMeasure;
	}

	public void setDescriptiveAttributeMeasure(String descriptiveAttributeMeasure) {
		this.descriptiveAttributeMeasure = descriptiveAttributeMeasure;
	}

}