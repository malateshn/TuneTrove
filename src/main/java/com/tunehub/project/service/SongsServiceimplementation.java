package com.tunehub.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.project.entity.Songs;
import com.tunehub.project.repository.SongsRepository;
@Service
public class SongsServiceimplementation implements SongsService
{
	@Autowired
	SongsRepository srepo;
	
	@Override
	public String addSongs(Songs song) {
		srepo.save(song);
		return "Song is added";
	}

	@Override
	public boolean songExists(String name) {
		if(srepo.findByName(name)==null) 
		{
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> songslist= srepo.findAll();
		return songslist;
	}

	@Override
	public void updateSong(Songs song) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
