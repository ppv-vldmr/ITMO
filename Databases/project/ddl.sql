drop table if exists LogEntity, OutstaffWorker, StaffWorker, WarehouseZone, Warehouse, OutstaffCompany, Position;

create table Position(
    PositionId bigint primary key,
    PositionName varchar(60) unique not null,
    PositionAccessLevel varchar(8) not null
        constraint checkAccessLevel check (PositionAccessLevel in ('internal','external','full')),
    Salary int not null
        constraint checkSalaryAmount check (Salary > 0)
);

create table OutstaffCompany(
    OutstaffCompanyId bigint primary key,
    OutstaffCompanyName varchar(60) unique not null,
    OutstaffCompanyAddress text not null,
    OutstaffCompanyPhone varchar(20) unique not null,
    OutstaffCompanyEmail varchar(60) unique not null,
    OutstaffCompanyCreatedAt timestamp not null,
    OutstaffCompanyContractStartDate date not null,
    OutstaffCompanyContractExpirationDate date default null
);

create table Warehouse(
    WarehouseId bigint primary key,
    WarehouseType varchar(3) not null
        constraint checkWHType check (WarehouseType in ('WH','SC','FFC','LS')),
    WarehouseName varchar(60) not null,
    WarehouseAddress text not null,
    WarehouseTimeZone int not null
        constraint checkTimeZoneValue check (WarehouseTimeZone between -12 and 12),
    WarehouseRegionCode int not null
        constraint checkRegionCode check (WarehouseRegionCode between 1 and 999)
);

create table WarehouseZone(
    WarehouseZoneId bigint, -- NOT NULL due to PK
    WarehouseId bigint not null references Warehouse (WarehouseId),
    WarehouseZoneType varchar(7) not null
        constraint checkZoneType check (WarehouseZoneType in ('working', 'rest')),
    WarehouseZoneName varchar(60) not null,
    WarehouseZoneAccessLevel varchar(8) not null
        constraint checkAccessLevel check (WarehouseZoneAccessLevel in ('internal', 'external', 'full')),
    primary key (WarehouseId, WarehouseZoneId)
);

create table StaffWorker(
    StaffWorkerId bigint primary key,
    StaffWorkerName varchar(100) not null,
    StaffWorkerGender varchar(6) not null
        constraint checkGender check (StaffWorkerGender in ('male', 'female')),
    StaffWorkerBirthDate date not null
        constraint checkStaffWorkerAge check (StaffWorkerBirthDate < (current_date - interval '18 year')),
    StaffWorkerPassport varchar(10) unique not null
        constraint checkStaffWorkerPassport check (StaffWorkerPassport ~ '[0-9]{10}'),
    StaffWorkerPositionId bigint not null references Position (PositionId),
    StaffWorkerPhoto text unique not null,
    StaffWorkerPhone varchar(20) unique not null,
    StaffWorkerCreatedAt timestamp not null,
    StaffWorkerFiredAt date default null,
    StaffWorkerWarehouseId bigint not null references Warehouse(WarehouseId),
    StaffWorkerDeactivationReason varchar(12) default null
        constraint checkStaffDReason check (StaffWorkerDeactivationReason in ('incident', 'own_request', 'by_agreement', 'reduction'))
);

create table OutstaffWorker(
    OutstaffWorkerId bigint primary key,
    OutstaffWorkerName varchar(100) not null,
    OutstaffWorkerGender varchar(6) not null
        constraint checkGender check (OutstaffWorkerGender in ('male', 'female')),
    OutstaffWorkerBirthDate date not null
        constraint checkOutstaffWorkerAge check (OutstaffWorkerBirthDate < (current_date - interval '18 year')),
    OutstaffWorkerPassport varchar(10) unique not null
        constraint checkOutstaffWorkerPassport check (OutstaffWorkerPassport ~ '[0-9]{10}'),
    OutstaffWorkerPositionId bigint not null references Position (PositionId),
    OutstaffWorkerPhoto text unique not null,
    OutstaffWorkerPhone varchar(20) unique not null,
    OutstaffWorkerCreatedAt timestamp not null,
    OutstaffWorkerFiredAt date default null,
    OutstaffWorkerWarehouseId bigint not null references Warehouse(WarehouseId),
    OutstaffWorkerDeactivationReason varchar(15) default null
        constraint checkOutstaffDReason check (OutstaffWorkerDeactivationReason in ('incident', 'fired_by_comp', 'end_of_contract')),
    OutstaffWorkerCompanyId bigint not null references OutstaffCompany(OutstaffCompanyId),
    OutstaffWorkerContractStarts date not null,
    OutstaffWorkerContractEnds date not null
);

create table LogEntity(
    LogEntityId bigint primary key,
    LogEntityStaffWorkerId bigint references StaffWorker(StaffWorkerId),
    LogEntityOutstaffWorkerId bigint references OutstaffWorker(OutstaffWorkerId),
    LogEntityCreatedAt timestamp not null,
    LogEntityWarehouseId bigint not null references Warehouse(WarehouseId),
    LogEntityWarehouzeZoneId bigint not null,
    LogEntityType varchar(5) not null
        constraint checkLEType check (LogEntityType in ('exit', 'enter')),
    constraint checkWorkerIdInLog check
        ((LogEntityOutstaffWorkerId is not null and LogEntityStaffWorkerId is null)
             or
         (LogEntityOutstaffWorkerId is null and LogEntityStaffWorkerId is not null)),
    foreign key (LogEntityWarehouseId, LogEntityWarehouzeZoneId) references WarehouseZone (WarehouseId, WarehouseZoneId)
);

-- index on requests to pull information about some zone type in warehouses
create index on WarehouseZone using hash (WarehouseZoneId);

-- indexes on requests to pull information about worker's actions during some period
create index on LogEntity using hash (LogEntityStaffWorkerId);
create index on LogEntity using hash (LogEntityOutstaffWorkerId);