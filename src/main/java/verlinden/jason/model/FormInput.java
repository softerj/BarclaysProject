package verlinden.jason.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class FormInput {

	@NotEmpty
	private String fileId;
	
	@NotEmpty
	@Size(min=2, max=30)
	private String userName;
	
	public String getFileId() {
		return fileId;
	}
	
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public FormInput() {}
	
	public FormInput(String fileId, String userName) {
		this.fileId = fileId;
		this.userName = userName;
	}
}
