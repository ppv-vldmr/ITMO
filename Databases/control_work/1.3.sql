select RunId, SessionId, Letter, SubmitTime
  from Sessions natural join Runs
  where ContestId = :ContestId
    and Accepted = 1