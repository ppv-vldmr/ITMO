-- добавить новую компанию-партнера
--уровень изоляции: read uncommited, так как мы сразу добавлем в таблицу запись о новой компании без лишних действий
create or replace function addOutstaffCompany(
    companyIdArg bigint,
    companyName varchar(60),
    address text,
    phone varchar(20),
    email varchar(60),
    contractStartDate date,
    contractExpirationDate date
) returns boolean
as
$$
begin
    insert into OutstaffCompany(
             OutstaffCompanyId,
             OutstaffCompanyName,
             OutstaffCompanyAddress,
             OutstaffCompanyPhone,
             OutstaffCompanyEmail,
             OutstaffCompanyCreatedAt,
             OutstaffCompanyContractStartDate,
             OutstaffCompanyContractExpirationDate
    ) values (
             companyIdArg,
             companyName,
             address,
             phone,
             email,
             now(),
             contractStartDate,
             contractExpirationDate
    );
    return true;
end;
$$ language plpgsql;

--уволить штатного или внештатного сотрудника
--уровень изоляции: read uncommited, так как мы сразу проставляем нужные значения в одной таблице
create or replace function fireWorker(isStaff boolean, workerId bigint, reason varchar(15))
returns boolean
as
$$
begin
    if (isStaff)
    then
        update StaffWorker
        set StaffWorkerDeactivationReason = reason,
            StaffWorkerFiredAt = now()
        where StaffWorkerId = workerId;
    else
        update OutstaffWorker
        set OutstaffWorkerDeactivationReason = reason,
            OutstaffWorkerFiredAt = now()
        where OutstaffWorkerId = workerId;
    end if;
    return true;
end;
$$ language plpgsql;

--создать запись под нового штатного или внештатного сотрудника
--уровень изоляции: read uncommited, так как мы сразу добавлем в таблицу запись о человеке без лишних действий
create or replace function createWorker(
    isStaff boolean,
    workerId bigint,
    name varchar(100),
    gender varchar(6),
    birthdate date,
    passport varchar(10),
    positionId bigint,
    photoLink text,
    phone varchar(20),
    warehouseId bigint,
    outstaffCompanyId bigint,
    outstaffContractStart date,
    outstaffContractEnd date
) returns boolean
as
$$
begin
    if (isStaff)
    then
        insert into StaffWorker(
            StaffWorkerId,
            StaffWorkerName,
            StaffWorkerGender,
            StaffWorkerBirthDate,
            StaffWorkerPassport,
            StaffWorkerPositionId,
            StaffWorkerPhoto,
            StaffWorkerPhone,
            StaffWorkerCreatedAt,
            StaffWorkerFiredAt,
            StaffWorkerWarehouseId,
            StaffWorkerDeactivationReason
        ) values (
            workerId,
            name,
            gender,
            birthdate,
            passport,
            positionId,
            photoLink,
            phone,
            now(),
            null,
            warehouseId,
            null
        );
    else
        insert into OutstaffWorker(
            OutstaffWorkerId,
            OutstaffWorkerName,
            OutstaffWorkerGender,
            OutstaffWorkerBirthDate,
            OutstaffWorkerPassport,
            OutstaffWorkerPositionId,
            OutstaffWorkerPhoto,
            OutstaffWorkerPhone,
            OutstaffWorkerCreatedAt,
            OutstaffWorkerFiredAt,
            OutstaffWorkerWarehouseId,
            OutstaffWorkerDeactivationReason,
            OutstaffWorkerCompanyId,
            OutstaffWorkerContractStarts,
            OutstaffWorkerContractEnds
        ) values (
            workerId,
            name,
            gender,
            birthdate,
            passport,
            positionId,
            photoLink,
            phone,
            now(),
            null,
            warehouseId,
            null,
            outstaffCompanyId,
            outstaffContractStart,
            outstaffContractEnd
        );
    end if;
    return true;
end;
$$ language plpgsql;