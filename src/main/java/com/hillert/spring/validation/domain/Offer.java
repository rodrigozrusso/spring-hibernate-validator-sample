package com.hillert.spring.validation.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

/**
 * Annotation rules based on {@link http://docs.jboss.org/hibernate/validator/4.2/api/index.html?org/hibernate/validator/constraints/NotBlank.html} and {@link http
 * ://docs.oracle.com/javaee/6/api/javax/validation/constraints/package-summary.html}
 * 
 * @author rodrigozrusso
 * 
 */
@XmlRootElement(name = "offer")
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String name;

	@SafeHtml
	private String description;

	@XmlElement(defaultValue = "true")
	private Boolean active = true;

	private BigDecimal price;

	private BigDecimal priceDiscount;

	@NotNull
	@Min(1)
	private Long quantity;

	private String urlImage;

	@URL
	private String url;

	private Long skuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(BigDecimal priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

}
