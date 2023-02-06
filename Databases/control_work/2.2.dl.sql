TeamsSucByContestAndLetter(TeamName) :-
    Runs(_, SessionId, :Letter, _, 1),
    Sessions(SessionId, TeamId, :ContestId, _),
    Teams(TeamId, TeamName).