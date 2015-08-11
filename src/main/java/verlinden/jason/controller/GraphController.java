package verlinden.jason.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import verlinden.jason.airport.DenverAirport;
import verlinden.jason.entity.LogEntry;
import verlinden.jason.model.FormInput;
import verlinden.jason.model.PathsResponseJson;
import verlinden.jason.repository.LogEntryRepository;

@Controller
@RequestMapping("/graph")
public class GraphController {
	private static int sectionCount = 0;

	private final String HEADER_SECTION_CHAR = "#";
	private final int CONVEYER_COLUMN_COUNT = 3;
	private final int DEPARTURE_COLUMN_COUNT = 4;
	private final int LUGGAGE_COLUMN_COUNT = 3;
	
	@Autowired
	private LogEntryRepository repository;

	@RequestMapping("test")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView("/graph/test");
		mv.addObject("message", "This came from the graph controller's test method!");
		
		return mv;
	}
	
	@RequestMapping("anotherTest")
	public ModelAndView anotherTest() {
		ModelAndView mv = new ModelAndView("/graph/test");
		mv.addObject("message", "This came from the graph controller's anotherTest method!");
		
		return mv;
	}
	
	@RequestMapping(value = "findPaths", method = RequestMethod.POST)
	public ModelAndView findShortestPaths(@Valid @ModelAttribute("formInput") FormInput formInput, BindingResult bindingResult, SessionStatus status) {
		if (bindingResult.hasErrors()) {
            return new ModelAndView("/");
        }
		
		// Save log entry to mongo
		repository.save(new LogEntry(formInput.getUserName(), formInput.getFileId()));
		
		ModelAndView result = new ModelAndView("/graph/results");
		result.addObject("routes", getShortestPaths(formInput.getFileId()));
		result.addObject("userName", formInput.getUserName());
		
		return result;
	}
	
	@RequestMapping(value = "findPaths", method = RequestMethod.GET)
	public @ResponseBody PathsResponseJson getPathsInJSON(@RequestParam("userName") String userName, @RequestParam("fileId") String fileId) {
		// Save log entry to mongo
		repository.save(new LogEntry(userName, fileId));
		
		PathsResponseJson resp = new PathsResponseJson(userName, getShortestPaths(fileId));
		
		return resp;
	}
	
	private List<String> getShortestPaths(String testFile) {
		DenverAirport airport = new DenverAirport();
		sectionCount = 0;
		
		// Get the data
		try {
			readFileInput(airport, testFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print the routes
		List<String> routes = airport.getAllLuggageRoutes();

		return routes;
	}
	
	/**
	 * Reads the data from the contents of a file specified by filePath and used
	 * to populate the aiport object.
	 * 
	 * @param airport
	 *            The object to hold the data read from the file.
	 * @param filePath
	 *            The path of the file to read from.
	 * @throws Exception 
	 */
	private void readFileInput(DenverAirport airport, String filePath) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
		InputStream is = classLoader.getResourceAsStream(filePath);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				processLineOfData(airport, line);
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Take in a line of data and process it.
	 * 
	 * @param airport
	 *            The object to store the data after it's processed.
	 * @param lineOfData
	 *            The line of data to process.
	 * @throws Exception
	 *             If the data format is incorrect.
	 */
	private void processLineOfData(DenverAirport airport, String lineOfData) throws Exception {

		if (lineOfData.startsWith(HEADER_SECTION_CHAR)) {
			// Skip the section header lines
			sectionCount++;
			return;
		}

		if (sectionCount == 1) {
			// Read in conveyer system nodes and build graph
			// This section should have 3 columns and be in the form:
			// <Node 1> <Node 2> <travel_time>
			String[] data = lineOfData.split(" ");
			if (data.length != CONVEYER_COLUMN_COUNT) {
				throw new Exception(
						"Conveyer system data is incorrect. Should be in the format <Node 1> <Node 2> <travel_time>.");
			}

			airport.addConveyerSystemConnection(data[0], data[1],
					Integer.parseInt(data[2]));
		} else if (sectionCount == 2) {
			// Read in departure nodes and build list
			// This section should have 4 columns and be in the form:
			// <flight_id> <flight_gate> <destination> <flight_time>
			String[] data = lineOfData.split(" ");
			if (data.length != DEPARTURE_COLUMN_COUNT) {
				throw new Exception(
						"Departures data is incorrect. Should be in the format <flight_id> <flight_gate> <destination> <flight_time>.");
			}

			airport.addDeparture(data[0], data[1], data[2], data[3]);
		} else if (sectionCount == 3) {
			// Read in bags nodes and build list
			// This section should have 3 columns and be in the form:
			// <bag_number> <entry_point> <flight_id>
			String[] data = lineOfData.split(" ");
			if (data.length != LUGGAGE_COLUMN_COUNT) {
				throw new Exception(
						"Baggage data is incorrect. Should be in the format <bag_number> <entry_point> <flight_id>.");
			}

			airport.addLuggage(data[0], data[1], data[2]);
		} else {
			// Shouldn't get here but if we do, there is an extra section
			throw new Exception(
					"There are too many sections indicated by the beginning a line with '#', check your input data and try again.");
		}
	}
}
