CREATE TABLE "OperatingSystem" (
	"HardwareId"	TEXT NOT NULL,
	"Name"	TEXT NOT NULL,
	"Architecture"	TEXT NOT NULL,
	"DeviceName"	TEXT NOT NULL,
	"CurrentUser"	TEXT NOT NULL,
	CONSTRAINT "fk_os_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);