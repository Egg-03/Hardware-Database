CREATE TABLE "Memory" (
	"HardwareId"	TEXT NOT NULL,
	"SlotsUsed"	INTEGER NOT NULL,
	"TotalMemory"	TEXT NOT NULL,
	CONSTRAINT "fk_memory_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);