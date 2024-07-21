CREATE TABLE "Network" (
	"HardwareId"	TEXT NOT NULL,
	"DeviceId"	TEXT NOT NULL,
	"Description"	TEXT NOT NULL,
	"MACAddress"	TEXT NOT NULL,
	"IPAddress"	TEXT,
	"IPSubnet"	TEXT,
	"DefaultIPGateway"	TEXT,
	"DNSServerSearchOrder"	TEXT,
	CONSTRAINT "fk_network_hwid" FOREIGN KEY("HardwareId") REFERENCES "HardwareId"("UniqueId")
)