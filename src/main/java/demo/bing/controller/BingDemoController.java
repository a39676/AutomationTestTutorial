package demo.bing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.bing.flow.SearchInHompage;
import demo.bing.pojo.dto.SearchKeyWordDTO;

@Controller
@RequestMapping(value = "/bingDemo")
public class BingDemoController {
	
	@Autowired
	private SearchInHompage searchInHompageFlow;
	
	@GetMapping(value = "/searchByGet")
	@ResponseBody
	public String searchByGet(@RequestParam("keyword") String keyword) {
		searchInHompageFlow.searchInHomepage(keyword);
		return "Done";
	}
	

	@PostMapping(value = "/searchByPost")
	@ResponseBody
	public String searchByPost(@RequestBody SearchKeyWordDTO dto) {
		searchInHompageFlow.searchInHomepage(dto.getKeyword());
		return "Done";
	}
	
}
