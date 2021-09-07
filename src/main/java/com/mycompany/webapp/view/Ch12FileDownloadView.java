package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class Ch12FileDownloadView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(Ch12FileListView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("실행");

		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");
		
		// file명의 확장자를 보고 자동으로 타입을 return 해준다.
		String contentType = request.getServletContext().getMimeType(fileName);
		String origianFilename = fileName;
		String savedName = fileName;

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
