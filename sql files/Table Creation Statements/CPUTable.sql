CREATE TABLE "CPU" (
	"HardwareId"	TEXT NOT NULL,
	"DeviceId"	TEXT NOT NULL,
	"CpuName"	TEXT NOT NULL,
	"Manufacturer"	TEXT NOT NULL,
	"CpuCores"	INTEGER NOT NULL,
	"CpuThreads"	INTEGER NOT NULL,
	"CpuSocket"	TEXT NOT NULL,
	CONSTRAINT "fk_cpu_hwid" FOREIGN KEY("HardwareId") REFERENCES "HardwareId"("UniqueId")
)