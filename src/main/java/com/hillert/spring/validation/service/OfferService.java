package com.hillert.spring.validation.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.hillert.spring.validation.domain.Offer;

/**
 * 
 * @author rodrigozrusso
 *
 */
@Validated
public interface OfferService {

	@NotNull(message = "Null returns are not permitted")
	public Offer create(@Valid Offer input);
}
