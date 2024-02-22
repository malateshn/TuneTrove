package com.tunehub.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.project.entity.Songs;
import com.tunehub.project.service.SongsService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongsController {
	
	//Connecting Controller class to service class
	@Autowired
	SongsService songserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song) 
	{
		if(songserv.songExists(song.getName())==false) {
		songserv.addSongs(song);
		return "addsongssuccess";
		}
		else {
			return "addsongsfail";
		}
	}
	
	@GetMapping("/map-displaysongs")
	public String viewSongs(Model model) {
		List<Songs> songslist=songserv.fetchAllSongs();
		//Storing the displaying values into the model and displaying them in view using model
					       //AttributeName  //Attribute Values
						   //how we access  //What we are storing
		model.addAttribute("songslist",     songslist);
		return "displaysongs";
	}
	
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model) {
		boolean primeStatus=true;
		
		
		if(primeStatus==true) 
		{
			List<Songs> songslist=songserv.fetchAllSongs();
			model.addAttribute("songslist",songslist);
			return "viewsongs";
		}
		else 
		{
			return "makepayment";
		}
	}
	
}
