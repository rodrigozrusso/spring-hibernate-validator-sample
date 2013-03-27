/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hillert.spring.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hillert.spring.validation.domain.Offer;
import com.hillert.spring.validation.service.OfferService;

/**
 * 
 * @author rodrigozrusso
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest extends BaseIntegrationTest {

	@Autowired
	private OfferService offerService;

	@Test
	public void testCreate() throws Exception {
		Offer input = new Offer();
		input.setName("Reef Men's Fanning Sandal");
		input.setDescription("This is the original Reef Fanning that you know and love.");
		input.setPrice(new BigDecimal(29.98));
		input.setQuantity(20L);
		input.setUrlImage("http://ecx.images-amazon.com/images/I/31pQ-3OS-PL.jpg");
		input.setUrl("http://www.amazon.com/Reef-FANNING-Mens-Fanning-Sandal/dp/B001PTHDRS");
		input.setSkuId(1234L);

		Offer expected = new Offer();
		expected.setId(1L);

		Offer actual = offerService.create(input);
		assertEquals(expected.getId(), actual.getId());
	}

	@Test
	public void testCreateWithNullReturn() throws Exception {

		try {

			Offer input = new Offer();
			input.setName("returnnull");
			input.setQuantity(1L);
			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("Null returns are not permitted", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

	@Test
	public void testCreateOfferNameEmpty() throws Exception {

		try {

			Offer input = new Offer();
			input.setName("");
			input.setQuantity(1L);
			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("may not be empty", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

	@Test
	public void testCreateOfferNameNull() throws Exception {

		try {

			Offer input = new Offer();
			input.setName(null);
			input.setQuantity(1L);
			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("may not be empty", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

	@Test
	public void testCreateOfferQuantityZero() throws Exception {

		try {

			Offer input = new Offer();
			input.setName("Reef Men's Fanning Sandal");
			input.setQuantity(0L);
			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("must be greater than or equal to 1", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

	@Test
	public void testCreateOfferURLWrong() throws Exception {

		try {

			Offer input = new Offer();
			input.setName("Reef Men's Fanning Sandal");
			input.setQuantity(1L);

			input.setUrl("xpto");
			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("must be a valid URL", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

	@Test
	public void testCreateOfferSafeHTML() throws Exception {

		try {

			Offer input = new Offer();
			input.setName("Reef Men's Fanning Sandal");
			input.setQuantity(1L);

			input.setDescription("<script>alert(\"a\");</script>");

			offerService.create(input);

		} catch (MethodConstraintViolationException e) {
			assertEquals("may have unsafe html content", e.getConstraintViolations().iterator().next().getMessage());
			return;
		}

		fail("Was expecting a ConstraintViolationException.");
	}

}
