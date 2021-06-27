package org.easyj.samples.office.excel.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author wangliang181230
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
}
