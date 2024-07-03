CREATE TABLE "Network" (
	"HardwareId"	TEXT NOT NULL,
	"Description"	TEXT NOT NULL,
	"MACAddress"	TEXT NOT NULL,
	CONSTRAINT "fk_network_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);