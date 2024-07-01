CREATE TABLE "Memory" (
	"HardwareId"	TEXT NOT NULL,
	"Slots Used"	INTEGER NOT NULL,
	"Total Memory"	TEXT NOT NULL,
	CONSTRAINT "fk_memory_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);