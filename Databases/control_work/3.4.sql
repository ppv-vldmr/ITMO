update
    Runs
set Accepted = 1
where RunId in
      (
          select RunId
          from Runs
                   natural join
               (
                   select SessionId, max(SubmitTime) as SubmitTime
                   from Runs
                   group by SessionId
               ) LastFail
      )