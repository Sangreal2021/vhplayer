package com.mega.vhplayer.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mega.vhplayer.beans.vo.AttachFileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
@RequestMapping("/admin/upload/*")
public class UploadController {
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		log.info("---------------------- upload form");
	}
	
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		log.info("---------------------- upload ajax");
	}
	
	// 1. uploadForm.html에서 받아서 처리할 서비스 로직 구현.
	@PostMapping("uploadFormAction")
	public String uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "E:/MegaStudy/Study/Project/upload/tmp";
		
		for(MultipartFile f : uploadFile) {
			// 받아온 f를 가지고 할 일
			log.info("file received ---------------------> ");
			// 파일 이름 및 크기 알아보기
			log.info("file name : " + f.getOriginalFilename());
			log.info("file size : " + f.getSize());
			
			// File Creation(Empty)
			File saveFile = new File(uploadFolder, f.getOriginalFilename());
			
			// 컨텐츠 복사
			try {
				// 파일 업로드 transforTo() 사용
				f.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage()); // 간단히 출력
			}
		}
		return "/upload/result"; // 임시 리턴 페이지
	}
	
	// 6. Ajax로 파일 업로드
	@PostMapping(value = "uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<AttachFileVO> uploadAjaxPost(MultipartFile[] uploadFile, Model model){
		log.info("upload ajax post ----------------------------");
		
		List<AttachFileVO> fileList = new ArrayList<>();
		String uploadFolder = "D:/MegaStudy/Study/workspaceEE/spring/vhplayer/src/main/resources/static/assets/songs";
//		String uploadFolderPath = getFolder();
		
		// 1번 문제 해결을 위해 /yyyy/mm/dd 경로를 생성
//		File uploadPath = new File(uploadFolder, uploadFolder);
		File uploadPath = new File(uploadFolder);
		// 디렉토리 실제 생성
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		log.info("upload path : " + uploadPath);
		
		for(MultipartFile f : uploadFile) {
			// 받아온 f를 가지고 할 일
			log.info("file received ---------------------");
			// 파일 이름 및 크기 알아보기
			log.info("file name : " + f.getOriginalFilename());
			log.info("file name : " + f.getSize());
			
			AttachFileVO attachFileVO = new AttachFileVO();
			
			// IE에서는 파일 이름을 포함한 전체 경로가 나오기 때문에 잘라줌
			// upload\\aaaa\\eeee\\filename.txt 같은 방식으로 출력
			String uploadFileName = f.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("file name(modified) : " + uploadFileName);
			// setFileName은 @Data 롬복
			attachFileVO.setFileName(uploadFileName);
			
			// UUID 적용
//			UUID uuid = UUID.randomUUID();
			// 파일 이름으로 조회 시에는 '_'를 기준으로 뒤의 문자열로 조회 가능
//			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("file name(modified + uuid) : " + uploadFileName);
			
			// File Creation(Empty)
			File saveFile = new File(uploadPath, uploadFileName);
			
			// 컨텐츠 복사
			try {
				// 파일 업로드 transferTo() 사용
				f.transferTo(saveFile);
				
				InputStream in = new FileInputStream(saveFile);
				// uuid는 String 아님.
//				attachFileVO.setUuid(uuid.toString());
//				attachFileVO.setUploadPath(uploadFolderPath);
				attachFileVO.setUploadPath(uploadFolder);
				
				if(checkImageType(saveFile)) { // 이미지이면?
					// 이미지면 true
					attachFileVO.setImage(true);
					// 앞에 s_ 붙임
					FileOutputStream tn = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					// inputstream을 통해 파일을 생성하고(원래 있던 파일을 가져와야 하므로)
					// 가로, 세로 길이를 지정해줌.
					// 새로운 작은 파일은 s_ 가 붙은 형태로 해당 경로에 생성됨.(32, 32)도 가능
					Thumbnailator.createThumbnail(in, tn, 100, 100);
					tn.close();
				}else { // 이미지가 아니면?
					attachFileVO.setImage(false);
				}
				in.close();
				// attachFileVO를 fileList에 저장함
				fileList.add(attachFileVO);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return fileList;
	}
	
	// 2. 업로드 디렉토리에 년/월/일 디렉토리 생성
//	private String getFolder() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date(); // java.util
//		String str = sdf.format(date);
//		
//		// 보통 폴더 생성은 2023/5/21 로
//		return str.replace("-", "/");
//	}
	
	// 3. 이미지 타입 체크
	private boolean checkImageType(File file) {
		try {
			// Files 클래스가 제공하는 probeContentType() 메소드로
			// file에 있는 컨텐트를 검사
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType); // text/plain, image/jpeg, ...
			// 걸려서 image면 반환
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 4. 음성파일 타입 체크
	private boolean checkSoundType(File file) {
		try {
			// Files 클래스가 제공하는 probeContentType() 메소드로
			// file에 있는 컨텐트를 검사
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType); // text/plain, image/jpeg, ...
			// 걸려서 audio/mpeg면 반환
			return contentType.startsWith("audio");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//5. 파일이름 주면 그 파일을 찾아 데이터를 주겠다
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("getFile - fileName : " + fileName + " ----------------");
		
		// 경로에서 파일 불러옴
		File file = new File("D:/MegaStudy/Study/workspaceEE/spring/vhplayer/src/main/resources/static/assets/songs" + fileName);
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders(); // org.springframework.http.HttpHeaders;
		
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			// file을 카피해서 header에 넣음
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}




























