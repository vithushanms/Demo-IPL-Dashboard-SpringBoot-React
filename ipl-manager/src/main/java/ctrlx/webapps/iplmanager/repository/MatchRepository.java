/*
 * File: MatchRepository.java
 * Project: repository
 * File Created: Thursday, 27th May 2021 12:31:27 am
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Thursday, 27th May 2021 1:42:06 am
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */
package ctrlx.webapps.iplmanager.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ctrlx.webapps.iplmanager.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    default List<Match> getLatestMatchesByTeam(String teamName, int count){
        Pageable pageable = PageRequest.of(0, count);
        return this.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }
}
