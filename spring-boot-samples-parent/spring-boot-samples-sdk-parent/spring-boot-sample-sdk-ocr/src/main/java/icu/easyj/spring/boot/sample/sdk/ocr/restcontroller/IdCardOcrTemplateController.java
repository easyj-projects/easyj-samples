package icu.easyj.spring.boot.sample.sdk.ocr.restcontroller;

import java.io.IOException;
import javax.annotation.Resource;

import cn.hutool.core.codec.Base64;
import icu.easyj.sdk.ocr.CardSide;
import icu.easyj.sdk.ocr.IOcrTemplate;
import icu.easyj.sdk.ocr.idcardocr.IdCardOcrAdvanced;
import icu.easyj.sdk.ocr.idcardocr.IdCardOcrAdvancedGroup;
import icu.easyj.sdk.ocr.idcardocr.IdCardOcrResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 身份证识别 的Controller
 *
 * @author wangliang181230
 */
@RestController
public class IdCardOcrTemplateController {

	@Resource
	private IOcrTemplate ocrTemplate;

	/**
	 * 单面身份证识别
	 *
	 * @param image    单面身份证图片文件
	 * @param cardSide 身份证正反面枚举，可选值：FRONT/BACK
	 * @return response 身份证识别结果
	 * @throws IOException IO异常
	 */
	@PostMapping("/test/ocr/idcardocr/one-card-side")
	public IdCardOcrResponse oneCardSideIdCardOcr(@RequestPart("image") MultipartFile image,
												  @RequestParam(required = false) CardSide cardSide,
												  @RequestParam(required = false) IdCardOcrAdvancedGroup advancedGroup) throws IOException {
		String imageBase64 = Base64.encode(image.getInputStream());
		IdCardOcrAdvanced[] advancedArr = advancedGroup != null ? advancedGroup.getAdvancedArr() : IdCardOcrAdvanced.EMPTY;
		return ocrTemplate.idCardOcr(imageBase64, cardSide, advancedArr);
	}

	/**
	 * 同时上传正反两面的图片进行身份证识别
	 *
	 * @param image1 一张身份证图片文件
	 * @param image2 与image1相反的另一面的身份证图片文件
	 * @return response 身份证识别结果
	 * @throws IOException IO异常
	 */
	@PostMapping("/test/ocr/idcardocr/double-card-side")
	public IdCardOcrResponse doubleIdCardOcr(@RequestPart("image1") MultipartFile image1,
											 @RequestPart("image2") MultipartFile image2,
											 @RequestParam(required = false) IdCardOcrAdvancedGroup advancedGroup) throws IOException {
		String image1Base64 = Base64.encode(image1.getInputStream());
		String image2Base64 = Base64.encode(image2.getInputStream());
		IdCardOcrAdvanced[] advancedArr = advancedGroup != null ? advancedGroup.getAdvancedArr() : IdCardOcrAdvanced.EMPTY;
		return ocrTemplate.idCardOcr(image1Base64, image2Base64, advancedArr);
	}
}
