CREATE TABLE "Mainboard" (
	"HardwareId"	TEXT NOT NULL,
	"Name"	TEXT NOT NULL,
	"Manufacturer"	TEXT NOT NULL,
	"BIOS Version"	TEXT NOT NULL,
	CONSTRAINT "fk_mainboard_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);