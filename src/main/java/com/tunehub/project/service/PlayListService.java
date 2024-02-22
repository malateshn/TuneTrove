package com.tunehub.project.service;

import java.util.List;

import com.tunehub.project.entity.PlayList;

public interface PlayListService {

	public void addPlaylist(PlayList playlist);

	public List<PlayList> fetchPlaylist();

}
