package com.scheduler.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name = "Solr_Parts")
public class PartEntity {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "productSeriesId")
    private Long productSeriesId;

    @Column(name = "divisionId")
    private Long divisionId;

    @Column(name = "part_thumbnail")
    private String partThumbnail;

    @Column(name = "part_fullImage")
    private String partFullImage;

    @Column(name = "part_shortDesc")
    private String partShortDesc;

    @Column(name = "part_longDesc")
    private String partLongDesc;

    @Column(name = "cadPath")
    private String cadPath;

    @Column(name = "lastModifiedDate")
    private Date lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "productSeriesId", referencedColumnName = "productId", insertable = false, updatable = false)
    private ProductSeriesEntity productSeries;

    @OneToMany(mappedBy = "part",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PartDefiningAttributeEntity> definingAttributes;

    @OneToMany(mappedBy = "part",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PartDescriptiveAttributeEntity> descriptiveAttributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getProductSeriesId() {
		return productSeriesId;
	}

	public void setProductSeriesId(Long productSeriesId) {
		this.productSeriesId = productSeriesId;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public String getPartThumbnail() {
		return partThumbnail;
	}

	public void setPartThumbnail(String partThumbnail) {
		this.partThumbnail = partThumbnail;
	}

	public String getPartFullImage() {
		return partFullImage;
	}

	public void setPartFullImage(String partFullImage) {
		this.partFullImage = partFullImage;
	}

	public String getPartShortDesc() {
		return partShortDesc;
	}

	public void setPartShortDesc(String partShortDesc) {
		this.partShortDesc = partShortDesc;
	}

	public String getPartLongDesc() {
		return partLongDesc;
	}

	public void setPartLongDesc(String partLongDesc) {
		this.partLongDesc = partLongDesc;
	}

	public String getCadPath() {
		return cadPath;
	}

	public void setCadPath(String cadPath) {
		this.cadPath = cadPath;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public ProductSeriesEntity getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(ProductSeriesEntity productSeries) {
		this.productSeries = productSeries;
	}

	public List<PartDefiningAttributeEntity> getDefiningAttributes() {
		return definingAttributes;
	}

	public void setDefiningAttributes(List<PartDefiningAttributeEntity> definingAttributes) {
		this.definingAttributes = definingAttributes;
	}

	public List<PartDescriptiveAttributeEntity> getDescriptiveAttributes() {
		return descriptiveAttributes;
	}

	public void setDescriptiveAttributes(List<PartDescriptiveAttributeEntity> descriptiveAttributes) {
		this.descriptiveAttributes = descriptiveAttributes;
	}

}