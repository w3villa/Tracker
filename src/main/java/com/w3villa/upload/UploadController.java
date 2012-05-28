package com.w3villa.upload;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.w3villa.constants.TrackerConstant;
import com.w3villa.voBean.UploadItem;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(UploadController.class);

	
	
	@RequestMapping(value = "/FileUpload", method = RequestMethod.POST)
	public @ResponseBody
	UploadFileResponse create(UploadItem uploadItem, BindingResult result,
			HttpServletRequest request, Model model, HttpSession session) {
		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		String fileOrignalName = "";
		if (result.hasErrors()) {
			model.addAttribute("uploadStatus", "fail");
			uploadFileResponse.setStatus(TrackerConstant.UPLOAD_STATUS_FAIL);
			uploadFileResponse.setMessage("Error in file upload.");
			return uploadFileResponse;
		} else {
			try {
				MultipartFile file = uploadItem.getFileData();
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();
					if (file.getSize() > 15728640) {
						System.out.println("File Size:::" + file.getSize()
								+ " bytes.");
						uploadFileResponse
								.setStatus(TrackerConstant.UPLOAD_STATUS_FAIL);
						uploadFileResponse
								.setMessage("File size exceeds limit. Max size allowed less than 15 MB.");
						return uploadFileResponse;
					}
					System.out.println("size::" + file.getSize());
					/*
					 * fileName =
					 * "C:\\Users\\Ishank\\Desktop\\priyank development\\" +
					 * file.getOriginalFilename();
					 */
					if (uploadItem.getUploadPath() == null
							|| ("").equals(uploadItem.getUploadPath())) {
						throw new Exception(
								"Please provide upload Path its empty.");
					}
					fileName = request.getRealPath("") + uploadItem.getUploadPath()
							+ file.getOriginalFilename();
					outputStream = new FileOutputStream(fileName);
					fileOrignalName = file.getOriginalFilename();
					System.out.println("fileName:" + fileOrignalName);
					int readBytes = 0;
					byte[] buffer = new byte[15728640];
					int totalByteRead = 0;
					UploadInfoBean infoBean = new UploadInfoBean();
					while ((readBytes = inputStream.read(buffer, 0, 5120)) != -1) {
						if(buffer.length%1024 == 0){
							totalByteRead = totalByteRead + readBytes;
							infoBean.setBytesRead(totalByteRead);
							infoBean.setTotalSize(file.getSize());
							session.setAttribute("uploadInfoBean", infoBean);
						}
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				session.setAttribute("uploadFile", file.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
				uploadFileResponse.setStatus(TrackerConstant.UPLOAD_STATUS_FAIL);
				uploadFileResponse
						.setMessage("Exception thrown in file upload.["
								+ e.getMessage() + "]");
				return uploadFileResponse;
			}
			uploadFileResponse.setFileName(fileOrignalName);
			uploadFileResponse.setStatus(TrackerConstant.UPLOAD_STATUS_PASS);
			uploadFileResponse.setMessage(fileOrignalName
					+ " uploaded Successfully.");
			return uploadFileResponse;
		}
	}
	
	
		
	@RequestMapping(value = "/getStatus", method = RequestMethod.POST)
	public @ResponseBody UploadInfoBean getStatus(HttpSession session) {
		logger.info("get status...");
		System.out.println("get status...");
		UploadInfoBean uploadInfoBean = (UploadInfoBean)session.getAttribute("uploadInfoBean");
		if(uploadInfoBean != null){
			System.out.println("**************************************************");
			
			if(uploadInfoBean.getBytesRead() != uploadInfoBean.getTotalSize()){
				// per looks like 0% - 100%, remove % before submission
				uploadInfoBean.setTotalSize(uploadInfoBean.getTotalSize() / 1024);
				uploadInfoBean.setBytesRead(uploadInfoBean.getBytesRead() / 1024);
				String per = NumberFormat.getPercentInstance().format(
						(double) uploadInfoBean.getBytesRead() / (double) uploadInfoBean.getTotalSize());
				uploadInfoBean.setPercentage(Integer.parseInt(per.substring(0,
						per.length() - 1)));
			}
			else{
				uploadInfoBean.setPercentage(100);
				session.removeAttribute("uploadInfoBean");
			}
			System.out.println("percentage : "+uploadInfoBean.getPercentage());
			System.out.println("**************************************************");
		}
		else{
			uploadInfoBean = new UploadInfoBean();
			uploadInfoBean.setPercentage(500);
		}
		return uploadInfoBean;
	}

}
