package verlinden.jason.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import verlinden.jason.model.FormInput;
import verlinden.jason.model.TestDataFileOption;

@Controller
@RequestMapping("/")
public class DefaultController {	
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView initForm(Model model) {
		// Create the options for a test data drop down
		List<TestDataFileOption> testDataOptions = new ArrayList<TestDataFileOption>();
		testDataOptions.add(new TestDataFileOption("TestData1.txt", "TestData1.txt"));
		testDataOptions.add(new TestDataFileOption("TestData2.txt", "TestData2.txt"));
		
		model.addAttribute("formInput", new FormInput());
		model.addAttribute("testDataOptions", testDataOptions);
		
		ModelAndView mv = new ModelAndView("graph/formInput");
		
		return mv;
	}

}
