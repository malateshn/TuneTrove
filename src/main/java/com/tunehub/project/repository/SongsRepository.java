package com.tunehub.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.project.entity.Songs;

public interface SongsRepository extends JpaRepository<Songs, Integer>
{
	public Songs findByName(String name);
}
