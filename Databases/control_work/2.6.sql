select ProblemName
from Problems
where exists
    (
        select *
        from Runs
           , Sessions
        where Accepted = 1
          and Runs.SessionId = Sessions.SessionId
          and Problems.Letter = Runs.Letter
          and Problems.ContestId = Sessions.ContestId
    );