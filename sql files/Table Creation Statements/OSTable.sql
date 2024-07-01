CREATE TABLE "OperatingSystem" (
	"HardwareId"	INTEGER NOT NULL,
	"Name"	INTEGER NOT NULL,
	"Architecture"	INTEGER NOT NULL,
	"DeviceName"	INTEGER NOT NULL,
	"CurrentUser"	INTEGER NOT NULL,
	CONSTRAINT "fk_os_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);