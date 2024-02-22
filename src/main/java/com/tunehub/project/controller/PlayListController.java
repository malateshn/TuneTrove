package com.tunehub.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.project.entity.PlayList;
import com.tunehub.project.entity.Songs;
import com.tunehub.project.service.PlayListService;
import com.tunehub.project.service.SongsService;

@Controller
public class PlayListController {
	
	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		
		//fetching all the songs
		List<Songs> songslist=sserv.fetchAllSongs();
		
		//adding the songs to model
		model.addAttribute("songslist", songslist);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute PlayList playlist) {
		//Creating the playlist and adding the playlist  in the database
		pserv.addPlaylist(playlist);
		
		//updating the songs
		
		//getting the songs in the playlist	
		List<Songs> songsList=playlist.getSong();		//getting the list of the songs
		
		//updating the songs by one by one
		for(Songs song:songsList) 						//getting the songs one by one
		{
			//for particular song we want to get the particular list of playlist
								//inbuilt method in List in java
			song.getPlaylist().add(playlist);  //for each song adding the playlist
			sserv.updateSong(song);				//saving it in the databse
		}
		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<PlayList> plist=pserv.fetchPlaylist();
		model.addAttribute("plist", plist);
		return "viewplaylists";
	}
	
	
	
}
