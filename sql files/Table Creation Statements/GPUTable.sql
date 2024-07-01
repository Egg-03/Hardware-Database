CREATE TABLE "GPU" (
	"HardwareId"	TEXT NOT NULL,
	"GpuName"	TEXT NOT NULL,
	"VRAM"	TEXT NOT NULL,
	"Driver Version"  TEXT NOT NULL,
	CONSTRAINT "fk_gpu_hwid" FOREIGN KEY("HardwareId") REFERENCES HardwareId(UniqueId)
);