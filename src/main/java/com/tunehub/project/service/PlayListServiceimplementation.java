package com.tunehub.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.project.entity.PlayList;
import com.tunehub.project.repository.PlayListRepository;

@Service
public class PlayListServiceimplementation implements PlayListService
{
	@Autowired 
	PlayListRepository prepo;

	@Override
	public void addPlaylist(PlayList playlist) {
		prepo.save(playlist);
	}

	@Override
	public List<PlayList> fetchPlaylist() {
		return prepo.findAll();
		
	}
}
