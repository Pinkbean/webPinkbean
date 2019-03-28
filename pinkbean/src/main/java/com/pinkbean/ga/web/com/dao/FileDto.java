package com.pinkbean.ga.web.com.dao;

import java.util.List;

public class FileDto {

	String atchId;       /* 파일 아이디 */            
	String pFileName;    /* 물리 파일명 */            
	String lfileName;    /* 논리 파일명 */            
	String filePath;     /* 파일 경로 */             
	Long   fileSize;     /* 파일 크기 */             
	String fileExt;      /* 확장자 */               
	String crtDttm;      /* 작성일자 */              
	String crtUser;      /* 작성자 */               
	String updtDttm;     /* 수정일자 */              
	String updtUser;	 /* 수정자 */
	String saveFlag;
	
	List<FileDto> fileDtoList;
	
	public String getAtchId() {
		return atchId;
	}
	public void setAtchId(String atchId) {
		this.atchId = atchId;
	}
	public String getpFileName() {
		return pFileName;
	}
	public void setpFileName(String pFileName) {
		this.pFileName = pFileName;
	}
	public String getLfileName() {
		return lfileName;
	}
	public void setLfileName(String lfileName) {
		this.lfileName = lfileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getCrtDttm() {
		return crtDttm;
	}
	public void setCrtDttm(String crtDttm) {
		this.crtDttm = crtDttm;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
	public String getUpdtUser() {
		return updtUser;
	}
	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}
	public List<FileDto> getFileDtoList() {
		return fileDtoList;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public void setFileDtoList(List<FileDto> fileDtoList) {
		this.fileDtoList = fileDtoList;
	}
	@Override
	public String toString() {
		return "FileDto [atchId=" + atchId + ", pFileName=" + pFileName + ", lfileName=" + lfileName + ", filePath="
				+ filePath + ", fileSize=" + fileSize + ", fileExt=" + fileExt + ", crtDttm=" + crtDttm + ", crtUser="
				+ crtUser + ", updtDttm=" + updtDttm + ", updtUser=" + updtUser + ", saveFlag=" + saveFlag
				+ ", fileDtoList=" + fileDtoList + "]";
	}
}