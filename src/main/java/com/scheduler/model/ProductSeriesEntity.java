package com.scheduler.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Solr_ProductSeries")
public class ProductSeriesEntity {

    @Id
    @Column(name = "productId")
    private Long productId;

    @Column(name = "series_name")
    private String seriesName;

    @Column(name = "series_thumbnail")
    private String seriesThumbnail;

    @Column(name = "series_fullImage")
    private String seriesFullImage;

    @Column(name = "series_shortDesc")
    private String seriesShortDesc;

    @Column(name = "series_longDesc")
    private String seriesLongDesc;

    @Column(name = "series_urlKeywords")
    private String seriesUrlKeywords;

    @Column(name = "lastModifiedDate")
    private Date lastModifiedDate;
    
    @OneToMany(mappedBy = "productSeries")
    private List<PartEntity> parts;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesThumbnail() {
		return seriesThumbnail;
	}

	public void setSeriesThumbnail(String seriesThumbnail) {
		this.seriesThumbnail = seriesThumbnail;
	}

	public String getSeriesFullImage() {
		return seriesFullImage;
	}

	public void setSeriesFullImage(String seriesFullImage) {
		this.seriesFullImage = seriesFullImage;
	}

	public String getSeriesShortDesc() {
		return seriesShortDesc;
	}

	public void setSeriesShortDesc(String seriesShortDesc) {
		this.seriesShortDesc = seriesShortDesc;
	}

	public String getSeriesLongDesc() {
		return seriesLongDesc;
	}

	public void setSeriesLongDesc(String seriesLongDesc) {
		this.seriesLongDesc = seriesLongDesc;
	}

	public String getSeriesUrlKeywords() {
		return seriesUrlKeywords;
	}

	public void setSeriesUrlKeywords(String seriesUrlKeywords) {
		this.seriesUrlKeywords = seriesUrlKeywords;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<PartEntity> getParts() {
		return parts;
	}

	public void setParts(List<PartEntity> parts) {
		this.parts = parts;
	}

    // Getters and setters
}