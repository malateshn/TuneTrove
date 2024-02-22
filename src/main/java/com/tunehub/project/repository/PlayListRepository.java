package com.tunehub.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.project.entity.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer>
{

}
