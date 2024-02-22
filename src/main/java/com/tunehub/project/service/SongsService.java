package com.tunehub.project.service;

import java.util.List;

import com.tunehub.project.entity.Songs;

public interface SongsService 
{
	public String addSongs(Songs song);
	
	public boolean songExists(String name);
	
	public List<Songs> fetchAllSongs();

	public void updateSong(Songs song);

	
}
