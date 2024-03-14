package com.scheduler.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "PIM_PRICING_VIEW")
public class PricingViewEntity {

    @Id
    @Column(name = "PART_NUMBER")
    private String partNumber;

    @Column(name = "PART_DESCRIPTION")
    private String partDescription;

    @Column(name = "BOX_QUANTITY")
    private Double boxQuantity;

    @Column(name = "BOX_UOM")
    private String boxUom;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "WEIGHT_UOM")
    private String weightUom;

    @Column(name = "TARIFF_CODE")
    private String tariffCode;

    @Column(name = "COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "MIN_ORDER_QTY")
    private Double minOrderQuantity;

    @Column(name = "STOCKING_UOM")
    private String stockingUom;

    // Getters and setters
}