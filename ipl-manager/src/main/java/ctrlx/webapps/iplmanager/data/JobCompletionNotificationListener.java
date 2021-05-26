/*
 * File: JobCompletionNotificationListener.java
 * Project: data
 * File Created: Wednesday, 19th May 2021 2:36:26 am
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Wednesday, 26th May 2021 8:23:13 pm
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */

package ctrlx.webapps.iplmanager.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ctrlx.webapps.iplmanager.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, Team> teamsData = new HashMap<String, Team>();

      entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
          .getResultList().stream().map(t -> new Team((String) t[0], (long) t[1]))
          .forEach(team -> teamsData.put(team.getTeamName(), team));

      entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
          .getResultList().stream().forEach(t -> {
            Team team = teamsData.get((String) t[0]);
            team.setTotalMatches(team.getTotalMatches() + (long) t[1]);
          });

      entityManager.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
          .getResultList().stream().forEach(t -> {
            Team team = teamsData.get((String) t[0]);
            team.setTotalWins((long) t[1]);
          });

      teamsData.values().forEach(team -> entityManager.persist(team));
    }
  }
}