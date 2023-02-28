package icu.easyj.spring.boot.sample.logging.elk.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

@WebFilter
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String traceId = ((HttpServletRequest) request).getHeader("traceId");
			if (!StringUtils.hasText(traceId)) {
				traceId = UUID.randomUUID().toString();
			}

			try {
				MDC.put("traceId", traceId);

				// 设置链路跟踪ID到响应头中
				if (response instanceof HttpServletResponse) {
					((HttpServletResponse) response).setHeader("traceId", traceId);
				}

				chain.doFilter(request, response);
			} finally {
				MDC.remove("traceId");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
