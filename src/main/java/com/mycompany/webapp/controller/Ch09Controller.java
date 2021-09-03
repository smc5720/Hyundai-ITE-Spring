package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");

		return "ch09/content";
	}

	@PostMapping("/fileupload")
	public String fileUpload(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");

		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);

		// 파일 파트 내용 읽기
		logger.info("file original name: " + attach.getOriginalFilename());
		logger.info("file content type: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());

		// 파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyundai_it&e/upload_files/" + savedname);
		attach.transferTo(file);

		return "redirect:/ch09/content";
	}

	@PostMapping(value = "/fileuploadAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAjax(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");

		// 문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);

		// 파일 파트 내용 읽기
		logger.info("file original name: " + attach.getOriginalFilename());
		logger.info("file content type: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());

		// 파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyundai_it&e/upload_files/" + savedname);
		attach.transferTo(file);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();

		return json;
	}

	/*
	@GetMapping(value = "/filedownload", produces = "image/jpeg")
	@ResponseBody
	public byte[] filedownload(String savedname) throws Exception {
		String filePath = "C:/hyundai_it&e/upload_files/" + savedname;
		InputStream is = new FileInputStream(filePath);
		byte[] data = IOUtils.toByteArray(is);
		return data;
	}*/

	@GetMapping("/filedownload")
	public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent)
			throws Exception {
		// fileNo를 이용해서 DB에서 가져온 값
		String contentType = "image/jpeg";
		String origianFilename = "눈내리는마을.jpg";
		String savedName = "1630651026193-눈내리는마을.jpg";

		// 응답 body의 데이터 형식
		response.setContentType(contentType);

		// 브라우저별로 한글 파일명을 변환
		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE11 이하 버전일 경우
			origianFilename = URLEncoder.encode(origianFilename, "UTF-8");
		} else {
			// 크롬 브라우저에서 한글 파일명을 변환
			origianFilename = new String(origianFilename.getBytes("UTF-8"), "ISO-8859-1");
		}

		// 파일을 첨부로 다운로드하도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + origianFilename + "\"");

		// 파일로부터 데이터를 읽는 입력 스트림 생성
		String filePath = "C:/hyundai_it&e/upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);

		// 응답 body에 출력하는 출력 스트림 얻기
		OutputStream os = response.getOutputStream();

		// 입력 스트림 → 출력 스트림
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
	}
}