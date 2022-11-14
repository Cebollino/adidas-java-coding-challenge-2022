package com.adidas.backend.publicservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adidas.backend.publicservice.controller.PublicSaleServiceRestController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PublicSaleServiceRestController.class)
class PublicSaleServiceRestControllerTests {

	@Autowired
	private PublicSaleServiceRestController publicSaleServiceRestController;

//	@Test
//	void testDummyController() {
//		String expectedResponse = "Hello, this is a dummy response from public service";
//		ResponseEntity<String> response = dummyRestController.getDummyEndpointResponse();
//		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//		Assertions.assertEquals(expectedResponse, response.getBody());
//	}

}
