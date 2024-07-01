CREATE TABLE "Storage" (
	"HardwareId"	TEXT NOT NULL,
	"Name"	TEXT NOT NULL,
	"Serial"	TEXT NOT NULL,
	"Size"	TEXT NOT NULL,
	"SMART"	TEXT NOT NULL,
	CONSTRAINT "fk_storage_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);