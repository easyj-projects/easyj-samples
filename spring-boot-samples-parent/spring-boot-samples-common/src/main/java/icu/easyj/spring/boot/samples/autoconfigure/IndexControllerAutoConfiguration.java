package icu.easyj.spring.boot.samples.autoconfigure;

import icu.easyj.spring.boot.samples.controller.IndexController;
import org.springframework.context.annotation.Import;

/**
 * @author wangliang181230
 */
@Import({IndexController.class/*, Swagger2Config.class*/})
public class IndexControllerAutoConfiguration {
}
