/*
 * File: IplTeamService.js
 * Project: frontend
 * File Created: Friday, 28th May 2021 1:38:24 am
 * Author: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Last Modified: Friday, 28th May 2021 2:12:36 am
 * Modified By: Vithushan Sylvester (vsylvester@mitrai.com)
 * -----
 * Copyright 2021 vithushan sylvester
 */

const baseUrl = "http://localhost:8080";

exports.getTeamByTeamName = async (teamName, matchcount) => {
    return await (await fetch(`${baseUrl}/api/team/${teamName}?matchCount=${matchcount}`)).json();
}



