delete from Runs
where
    Runs.SessionId in (
        select
            SessionId
        from
            Sessions
        where
            ContestId = :ContestId
    );