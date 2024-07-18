CREATE TABLE "GPU" (
	"HardwareId"	TEXT NOT NULL,
	"DeviceId"	TEXT NOT NULL,
	"GpuName"	TEXT NOT NULL,
	"VRAM"	TEXT NOT NULL,
	"DriverVersion"	TEXT NOT NULL,
	CONSTRAINT "fk_gpu_hwid" FOREIGN KEY("HardwareId") REFERENCES "HardwareId"("UniqueId")
)