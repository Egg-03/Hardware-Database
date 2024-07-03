CREATE TABLE "Mainboard" (
	"HardwareId"	TEXT NOT NULL,
	"Name"	TEXT NOT NULL,
	"Manufacturer"	TEXT NOT NULL,
	"BIOSVersion"	TEXT NOT NULL,
	CONSTRAINT "fk_mainboard_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);