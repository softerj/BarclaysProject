package verlinden.jason.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LogEntry {
	
	@Id // For mongo
	private String id;
	private String userName;
	private String testDataFile;
	private String runDate;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getTestDataFile() {
		return testDataFile;
	}
	
	public void setTestDataFile(String testDataFile) {
		this.testDataFile = testDataFile;
	}
	
	public String getRunDate() {
		return runDate;
	}
	
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public LogEntry() {}
	
	public LogEntry(String userName, String testDataFile) {
		this.userName = userName;
		this.testDataFile = testDataFile;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		this.runDate = dateFormat.format(date);
	}
}
