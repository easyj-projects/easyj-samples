package icu.easyj.spring.boot.sample.logging.elk.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


	@GetMapping("/insert")
	public String insert() {
		String id = UUID.randomUUID().toString();

		try {
			MDC.put("id", id);
			LOGGER.info("insert");
		} finally {
			MDC.remove("id");
		}

		return id;
	}

	@GetMapping("/delete")
	public void delete(String id) {
		try {
			MDC.put("id", id);
			LOGGER.info("remove");

			int i = 1 / 0; // 模拟异常
		} catch (Throwable t) {
			LOGGER.error("error", t);
		} finally {
			MDC.remove("id");
		}
	}

}
