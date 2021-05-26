/*
 * File: Team.java
 * Project: model
 * File Created: Sunday, 23rd May 2021 1:48:11 pm
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Thursday, 27th May 2021 12:30:42 am
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */
package ctrlx.webapps.iplmanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long totalWins;
    
    @Transient
    private List<Match> matches;
    
    public Team(){}

    public Team(String teamName, long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
    
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }

    
}
