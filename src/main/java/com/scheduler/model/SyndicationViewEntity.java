package com.scheduler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PIM_CONTENT_SYNDICATION_VIEW")
public class SyndicationViewEntity {

    @Id
    @Column(name = "PART_NUMBER")
    private String partNumber;

    @Column(name = "PART_DESC")
    private String partDescription;

    @Column(name = "BOX_QUANTITY")
    private Double boxQuantity;

    @Column(name = "BOX_UOM")
    private String boxUom;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "WEIGHT_UOM")
    private String weightUom;

    @Column(name = "STOCKING_UOM")
    private String stockingUom;

    @Column(name = "COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;

    @Column(name = "MIN_ORDER_QTY")
    private Double minOrderQuantity;

    @Column(name = "TARIFF_CD")
    private String tariffCode;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public Double getBoxQuantity() {
		return boxQuantity;
	}

	public void setBoxQuantity(Double boxQuantity) {
		this.boxQuantity = boxQuantity;
	}

	public String getBoxUom() {
		return boxUom;
	}

	public void setBoxUom(String boxUom) {
		this.boxUom = boxUom;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}

	public String getStockingUom() {
		return stockingUom;
	}

	public void setStockingUom(String stockingUom) {
		this.stockingUom = stockingUom;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public Double getMinOrderQuantity() {
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(Double minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

    // Getters and setters
}