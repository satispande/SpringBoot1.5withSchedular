package com.scheduler.service;

import java.util.List;

import com.scheduler.model.PartDefiningAttributeEntity;
import com.scheduler.model.PartDescriptiveAttributeEntity;
import com.scheduler.model.PartEntity;
import com.scheduler.model.ProductSeriesEntity;
import com.scheduler.model.SyndicationViewEntity;

public class EmailBuilder {
	
	 private static final String TABLE_STYLE = "border-collapse: collapse; border: 1px solid black;";
	    private static final String CELL_STYLE = "border: 1px solid black;";
	
	    public static String buildPartEmailBody(List<PartEntity> parts) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<b>Part Detail</b>");
	        sb.append("<table style=\"").append(TABLE_STYLE).append("\">");
	        sb.append("<tr><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Product Name</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Code</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Product Series ID</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Division ID</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Thumbnail</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Full Image</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Short Description</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Long Description</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">CAD Path</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Last Modified Date</th></tr>");
	        for (PartEntity part : parts) {
	            sb.append("<tr>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getProductSeries().getSeriesName()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getCode()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getProductSeriesId()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getDivisionId()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getPartThumbnail()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getPartFullImage()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getPartShortDesc()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getPartLongDesc()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getCadPath()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(part.getLastModifiedDate()).append("</td>");
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        sb.append("<br/>");
	        return sb.toString();
	    }
	
	    public static String buildProductSeriesEmailBody(List<ProductSeriesEntity> productSeriesList) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<b>Product Series Detail</b>");
	        sb.append("<table style=\"").append(TABLE_STYLE).append("\">");
	        sb.append("<tr><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Product Name</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Series Name</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Thumbnail</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Full Image</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Short Description</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Long Description</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">URL Keywords</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Last Modified Date</th></tr>");
	        for (ProductSeriesEntity series : productSeriesList) {
	            sb.append("<tr>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getProductId()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesName()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesThumbnail()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesFullImage()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesShortDesc()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesLongDesc()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getSeriesUrlKeywords()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(series.getLastModifiedDate()).append("</td>");
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        sb.append("<br/>");
	        return sb.toString();
	    }
	
	    public static String buildPartDefiningAttributeEmailBody(List<PartDefiningAttributeEntity> definingAttributes) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<b>Part Defining Attributes Detail</b>");
	        sb.append("<table style=\"").append(TABLE_STYLE).append("\">");
	        sb.append("<tr><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Defining Attribute Name</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Defining Attribute Value</th></tr>");
	        for (PartDefiningAttributeEntity attribute : definingAttributes) {
	            sb.append("<tr>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(attribute.getDefiningAttributeName()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(attribute.getDefiningAttributeValue()).append("</td>");
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        sb.append("<br/>");
	        return sb.toString();
	    }

	    public static String buildPartDescriptiveAttributeEmailBody(List<PartDescriptiveAttributeEntity> descriptiveAttributes) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<b>Part Descriptive Attributes Detail</b>");
	        sb.append("<table style=\"").append(TABLE_STYLE).append("\">");
	        sb.append("<tr><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Descriptive Attribute Name</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Descriptive Attribute Value</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Descriptive Attribute Measure</th></tr>");
	        for (PartDescriptiveAttributeEntity attribute : descriptiveAttributes) {
	            sb.append("<tr>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(attribute.getDescriptiveAttributeName()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(attribute.getDescriptiveAttributeValue()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(attribute.getDescriptiveAttributeMeasure()).append("</td>");
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        sb.append("<br/>");
	        return sb.toString();
	    }
	    
	    public static String buildSyndicationViewEmailBody(List<SyndicationViewEntity> syndicationViews) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<b>Syndication View Detail</b>");
	        sb.append("<table style=\"").append(TABLE_STYLE).append("\">");
	        sb.append("<tr><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Number</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Part Description</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Box Quantity</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Box UOM</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Weight</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Weight UOM</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Stocking UOM</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Country of Origin</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Min Order Quantity</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Tariff Code</th><th class=\"table-header\" style=\"").append(CELL_STYLE).append("\">Last Updated Date</th></tr>");
	        for (SyndicationViewEntity syndicationView : syndicationViews) {
	            sb.append("<tr>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getPartNumber()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getPartDescription()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getBoxQuantity()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getBoxUom()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getWeight()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getWeightUom()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getStockingUom()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getCountryOfOrigin()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getMinOrderQuantity()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getTariffCode()).append("</td>");
	            sb.append("<td style=\"").append(CELL_STYLE).append("\">").append(syndicationView.getLastUpdatedDate()).append("</td>");
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        sb.append("<br/>");
	        return sb.toString();
	    }


}
