package verlinden.jason.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import verlinden.jason.entity.LogEntry;
import verlinden.jason.repository.LogEntryRepository;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	private LogEntryRepository repository;

	@RequestMapping("logByUser")
	public @ResponseBody List<LogEntry> getLogEntriesByUsername(@RequestParam("userName") String userName) {
		List<LogEntry> logEntries = repository.findByUserName(userName);
		
		return logEntries;
	}
	
	@RequestMapping("logByFile")
	public @ResponseBody List<LogEntry> getLogEntriesByTestDataFile(@RequestParam("fileName") String fileName) {
		List<LogEntry> logEntries = repository.findByTestDataFile(fileName);
		
		return logEntries;
	}
	
	@RequestMapping("allLogs")
	public @ResponseBody List<LogEntry> getAllLogEntries() {
		List<LogEntry> logEntries = repository.findAll();
		
		return logEntries;
	}
}
