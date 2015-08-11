package verlinden.jason.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import verlinden.jason.entity.LogEntry;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {

	public List<LogEntry> findByUserName(String userName);
	
    public List<LogEntry> findByTestDataFile(String testDataFile);
}
