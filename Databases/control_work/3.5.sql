update
    Runs
set Accepted = 1
where RunId in
      (
          select RunId
          from Runs
                   natural join
               (
                   select SessionId, Letter, max(SubmitTime) as SubmitTime
                   from Runs
                   group by SessionId, Letter
               ) LastFail
      )