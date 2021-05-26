/*
 * File: TeamController.java
 * Project: controller
 * File Created: Wednesday, 26th May 2021 9:31:18 pm
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Thursday, 27th May 2021 1:43:57 am
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */
package ctrlx.webapps.iplmanager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ctrlx.webapps.iplmanager.model.Team;
import ctrlx.webapps.iplmanager.repository.MatchRepository;
import ctrlx.webapps.iplmanager.repository.TeamRepository;

@RestController
public class TeamController {
    
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/api/team/{teamName}")
    public Team getTeam(@PathVariable String teamName,@RequestParam int matchCount){
        Team team = this.teamRepository.getByTeamName(teamName);
        team.setMatches(matchRepository.getLatestMatchesByTeam(teamName, matchCount));
        
        return team;
    } 
}
