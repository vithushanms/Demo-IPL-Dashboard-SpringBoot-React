/*
 * File: TeamRepository.java
 * Project: repository
 * File Created: Wednesday, 26th May 2021 10:01:46 pm
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Thursday, 27th May 2021 12:36:08 am
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */
package ctrlx.webapps.iplmanager.repository;

import org.springframework.data.repository.CrudRepository;

import ctrlx.webapps.iplmanager.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long> {
    Team getByTeamName(String teamName);
}
