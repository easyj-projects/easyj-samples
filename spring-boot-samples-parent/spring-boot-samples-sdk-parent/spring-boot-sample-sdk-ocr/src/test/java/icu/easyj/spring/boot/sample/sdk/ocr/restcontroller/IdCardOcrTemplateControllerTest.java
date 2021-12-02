package icu.easyj.spring.boot.sample.sdk.ocr.restcontroller;

import java.nio.charset.StandardCharsets;

import icu.easyj.core.util.DateUtils;
import icu.easyj.sdk.ocr.CardSide;
import icu.easyj.sdk.ocr.idcardocr.IdCardOcrAdvancedGroup;
import icu.easyj.sdk.ocr.idcardocr.IdCardOcrResponse;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 {@link IdCardOcrTemplateController} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
@Disabled("腾讯云测试账号免费额度有限，请手动执行该测试用例")
public class IdCardOcrTemplateControllerTest extends BaseSpringBootMockMvcTest {

	/**
	 * 测试：正面身份证识别
	 *
	 * @throws Exception 异常
	 * @see IdCardOcrTemplateController#oneCardSideIdCardOcr(MultipartFile, CardSide, IdCardOcrAdvancedGroup)
	 */
	@Test
	public void testFrontCardSideIdCardOcr() throws Exception {
		String path = "/test/ocr/idcardocr/one-card-side";

		super.mockPost(path)
				.file("image", "IDCardOCR1_FRONT.jpg")
				.queryParam("cardSide", CardSide.FRONT.name())
				.queryParam("advancedGroup", IdCardOcrAdvancedGroup.ALL_DETECT.name())
				.send()
				.status().isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.characterEncoding().is(StandardCharsets.UTF_8)
				.content(IdCardOcrResponse.class).is(resp -> {
					Assertions.assertEquals(CardSide.FRONT, resp.getCardSide());
					Assertions.assertEquals("刘洋", resp.getName());
					Assertions.assertEquals("女", resp.getSex());
					Assertions.assertEquals("汉", resp.getNation());
					Assertions.assertEquals("1995-05-13", DateUtils.toString(resp.getBirthday()));
					Assertions.assertEquals("广东省深圳市南山区腾讯大厦", resp.getAddress());
					Assertions.assertEquals("440305199505132561", resp.getIdNum());
					Assertions.assertNull(resp.getAuthority());
					Assertions.assertNull(resp.getValidDateStart());
					Assertions.assertNull(resp.getValidDateEnd());
					Assertions.assertNull(resp.getIdCardBase64());
					Assertions.assertNull(resp.getBackIdCardBase64());
					//Assertions.assertEquals(2, resp.getWarns().size()); // 告警信息不稳定，不校验它
				});
	}

	/**
	 * 测试：反面身份证识别
	 *
	 * @throws Exception 异常
	 * @see IdCardOcrTemplateController#oneCardSideIdCardOcr(MultipartFile, CardSide, IdCardOcrAdvancedGroup)
	 */
	@Test
	public void testBackCardSideIdCardOcr() throws Exception {
		String path = "/test/ocr/idcardocr/one-card-side";

		super.mockPost(path)
				.file("image", "IDCardOCR1_BACK.jpg")
				.queryParam("cardSide", CardSide.BACK.name())
				.queryParam("advancedGroup", IdCardOcrAdvancedGroup.ALL_DETECT.name())
				.send()
				.status().isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.characterEncoding().is(StandardCharsets.UTF_8)
				.content(IdCardOcrResponse.class).is(resp -> {
					Assertions.assertEquals(CardSide.BACK, resp.getCardSide());
					Assertions.assertNull(resp.getName());
					Assertions.assertNull(resp.getSex());
					Assertions.assertNull(resp.getNation());
					Assertions.assertNull(resp.getBirthday());
					Assertions.assertNull(resp.getAddress());
					Assertions.assertNull(resp.getIdNum());
					Assertions.assertEquals("深圳市公安局南山分局", resp.getAuthority());
					Assertions.assertEquals("2017-08-12", DateUtils.toString(resp.getValidDateStart()));
					Assertions.assertEquals("2037-08-12", DateUtils.toString(resp.getValidDateEnd()));
					Assertions.assertNull(resp.getIdCardBase64());
					Assertions.assertNull(resp.getBackIdCardBase64());
					//Assertions.assertEquals(2, resp.getWarns().size()); // 告警信息不稳定，不校验它
				});
	}

	/**
	 * 测试3：同时上传正反两面的图片进行身份证识别
	 *
	 * @see IdCardOcrTemplateController#doubleIdCardOcr(MultipartFile, MultipartFile, IdCardOcrAdvancedGroup)
	 */
	@Test
	public void testDoubleIdCardOcr() throws Exception {
		String path = "/test/ocr/idcardocr/double-card-side";

		super.mockPost(path)
				.file("image1", "IDCardOCR1_FRONT.jpg")
				.file("image2", "IDCardOCR1_BACK.jpg")
				.queryParam("advancedGroup", IdCardOcrAdvancedGroup.ALL_DETECT.name())
				.send()
				.status().isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.characterEncoding().is(StandardCharsets.UTF_8)
				.content(IdCardOcrResponse.class).is(resp -> {
					Assertions.assertEquals(CardSide.BOTH, resp.getCardSide());
					Assertions.assertEquals("刘洋", resp.getName());
					Assertions.assertEquals("女", resp.getSex());
					Assertions.assertEquals("汉", resp.getNation());
					Assertions.assertEquals("1995-05-13", DateUtils.toString(resp.getBirthday()));
					Assertions.assertEquals("广东省深圳市南山区腾讯大厦", resp.getAddress());
					Assertions.assertEquals("440305199505132561", resp.getIdNum());
					Assertions.assertEquals("深圳市公安局南山分局", resp.getAuthority());
					Assertions.assertEquals("2017-08-12", DateUtils.toString(resp.getValidDateStart()));
					Assertions.assertEquals("2037-08-12", DateUtils.toString(resp.getValidDateEnd()));
					Assertions.assertNull(resp.getIdCardBase64());
					Assertions.assertNull(resp.getBackIdCardBase64());
					//Assertions.assertEquals(2, resp.getWarns().size()); // 告警信息不稳定，不校验它
				});
	}
}
