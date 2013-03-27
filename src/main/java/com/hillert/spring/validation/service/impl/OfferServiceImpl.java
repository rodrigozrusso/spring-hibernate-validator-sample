package com.hillert.spring.validation.service.impl;

import org.springframework.stereotype.Service;

import com.hillert.spring.validation.domain.Offer;
import com.hillert.spring.validation.service.OfferService;

/**
 * 
 * @author rodrigozrusso
 *
 */
@Service
public class OfferServiceImpl implements OfferService {

	@Override
	public Offer create(Offer input) {

		if ("returnnull".equalsIgnoreCase(input.getName())) {
			return null;
		}

		input.setId(1L);
		return input;
	}

}
