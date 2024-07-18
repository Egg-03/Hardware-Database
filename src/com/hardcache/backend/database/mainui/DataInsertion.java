package com.hardcache.backend.database.mainui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ferrumx.system.currentuser.User;
import com.ferrumx.system.hardware.HardwareID;
import com.ferrumx.system.hardware.Win32_BIOS;
import com.ferrumx.system.hardware.Win32_Baseboard;
import com.ferrumx.system.hardware.Win32_DiskDrive;
import com.ferrumx.system.hardware.Win32_NetworkAdapter;
import com.ferrumx.system.hardware.Win32_PhysicalMemory;
import com.ferrumx.system.hardware.Win32_Processor;
import com.ferrumx.system.hardware.Win32_VideoController;
import com.ferrumx.system.networking.Win32_NetworkAdapterConfiguration;
import com.ferrumx.system.networking.Win32_NetworkAdapterSetting;
import com.ferrumx.system.operating_system.Win32_OperatingSystem;
import com.hardcache.backend.database.connection.DatabaseConnectivity;
import com.hardcache.frontend.miniuis.ExceptionUI;
import com.hardcache.frontend.miniuis.InformationUI;

public class DataInsertion {
	
	private static Connection connect;
	private static final String HARDWAREID = getHWID();
	
	private DataInsertion() {
		throw new IllegalStateException("Utility Class");
	}
	
	private static String getHWID() {
		try {
			return HardwareID.getHardwareID();
		} catch (ExecutionException | InterruptedException e) {
			new ExceptionUI("Database Dump Error", "HWID could not be retrieved\n"+e.getMessage()).setVisible(true);
			Thread.currentThread().interrupt();
			return "";
		}
	}
	
	//Populate the HardwareId Table
	private static boolean insertHardwareId(String username, String location) {
		String query = "INSERT INTO HardwareId (UniqueId, Username, Location) VALUES ('"+HARDWAREID+"', '"+username+"', '"+location+"');";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			if(e.getErrorCode()==19) { //Error code for Primary Key Constraint Failing
				new InformationUI("HWID Already Exists").setVisible(true);
				return false;
			}
			else {
				new ExceptionUI("HWID Data Dump Error", e.getMessage()).setVisible(true);
				return false;
			}
		}
	}
	
	//Populate the OS Table
	private static void insertOperatingSystem() {
		String query = "INSERT INTO OperatingSystem (HardwareId, Name, Architecture, DeviceName, CurrentUser) VALUES (?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> operatingSystemList = Win32_OperatingSystem.getOSList();
			for(String operatingSystem: operatingSystemList) {
				Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(operatingSystem);
				ps.setString(1, HARDWAREID);
				ps.setString(2, operatingSystem);
				ps.setString(3, osProperties.get("OSArchitecture"));
				ps.setString(4, osProperties.get("CSName"));
				ps.setString(5, User.getUsername());
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "OS info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "OS info could not be retrieved\n"+e.getMessage()).setVisible(true);
		}
	}
	
	//Populate the CPU Table
	private static void insertCpu() {
		String query = "INSERT INTO CPU (HardwareId, DeviceId, CpuName, CpuCores, CpuThreads, CpuSocket) VALUES (?,?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> cpuList = Win32_Processor.getProcessorList();
			for(String cpu: cpuList) {
				Map<String, String> cpuProperties = Win32_Processor.getCurrentProcessor(cpu);
				ps.setString(1, HARDWAREID);
				ps.setString(2, cpu);
				ps.setString(3, cpuProperties.get("Name"));
				ps.setInt(4, Integer.valueOf(cpuProperties.get("NumberOfCores")));
				ps.setInt(5, Integer.valueOf(cpuProperties.get("ThreadCount")));
				ps.setString(6, cpuProperties.get("SocketDesignation"));
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "CPU info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "CPU info could not be retrieved\n"+e.getMessage()).setVisible(true);
		} catch (NumberFormatException e) {
			new ExceptionUI("Database CPU Core/Thread Parse Error", e.getMessage()).setVisible(true);
		}
	}
	
	//Populate the memory
	private static void insertMemory() {
		String query = "INSERT INTO Memory (HardwareId, SlotsUsed, TotalMemory) VALUES (?,?,?);";
		Long size = 0L;
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> memoryList = Win32_PhysicalMemory.getTag();
			for(String memory: memoryList) {
				Map<String, String> memoryProperties = Win32_PhysicalMemory.getMemory(memory);
				size+= Long.parseLong(memoryProperties.get("Capacity"));
			}
			size = size/(1024*1024);
			
			ps.setString(1, HARDWAREID);
			ps.setInt(2, memoryList.size());
			ps.setString(3, String.valueOf(size)+" MB");
			ps.executeUpdate();
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "Memory info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "Memory info could not be retrieved\n"+e.getMessage()).setVisible(true);
		} catch (NumberFormatException e) {
			new ExceptionUI("Database Memory Size Parse Error", e.getMessage()).setVisible(true);
		}
	}
	
	private static void insertGpu() {
		String query = "INSERT INTO GPU (HardwareId, DeviceId, GpuName, VRAM, DriverVersion) VALUES (?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> gpuList = Win32_VideoController.getGPUID();
			for(String gpu: gpuList) {
				Map<String, String> gpuProperties = Win32_VideoController.getGPU(gpu);
				ps.setString(1, HARDWAREID);
				ps.setString(2, gpu);
				ps.setString(3, gpuProperties.get("Name"));
				Long adapterRam = Long.parseLong(gpuProperties.get("AdapterRAM"))/(1024*1024);
				ps.setString(4, String.valueOf(adapterRam)+" MB");
				ps.setString(5, gpuProperties.get("DriverVersion"));
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "GPU info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "GPU info could not be retrieved\n"+e.getMessage()).setVisible(true);
		} catch (NumberFormatException e) {
			new ExceptionUI("Database CPU Core/Thread Parse Error", e.getMessage()).setVisible(true);
		}
	}
	
	private static void insertMainboard() {
		String query = "INSERT INTO Mainboard (HardwareId, Name, Manufacturer, BIOSVersion) VALUES (?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			Map<String, String> mainboard = Win32_Baseboard.getMotherboard();
			Map<String, String> bios = Win32_BIOS.getPrimaryBIOS();
			
			ps.setString(1, HARDWAREID);
			ps.setString(2, mainboard.get("Product"));
			ps.setString(3, mainboard.get("Manufacturer"));
			ps.setString(4, bios.get("Caption"));
			ps.executeUpdate();
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "Mainboard info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "Mainboard info could not be retrieved\n"+e.getMessage()).setVisible(true);
		}
	}
	
	private static void insertNetwork() {
		String query = "INSERT INTO Network (HardwareId, DeviceId, Description, MACAddress, IPAddress, IPSubnet, DefaultIPGateway, DHCPServer) VALUES (?,?,?,?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> adapterList = Win32_NetworkAdapter.getDeviceIDList();
			for(String networkAdapter: adapterList) {
				Map<String, String> adapterProperties = Win32_NetworkAdapter.getNetworkAdapters(networkAdapter);
				String networkIndex = Win32_NetworkAdapterSetting.getIndex(networkAdapter);
				Map<String, String> adapterAddress = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(networkIndex);
				ps.setString(1, HARDWAREID);
				ps.setString(2, networkAdapter);
				ps.setString(3, adapterProperties.get("Description"));
				ps.setString(4, adapterProperties.get("MACAddress"));
				ps.setString(5, adapterAddress.get("IPAddress"));
				ps.setString(6, adapterAddress.get("IPSubnet"));
				ps.setString(7, adapterAddress.get("DefaultIPGateway"));
				ps.setString(8, adapterAddress.get("DHCPServer"));
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "Network info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "Network info could not be retrieved\n"+e.getMessage()).setVisible(true);
		}
	}
	
	private static void insertStorage() {
		String query = "INSERT INTO Storage (HardwareId, DeviceId, Name, Serial, Size, SMART) VALUES (?,?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> storageDiskList = Win32_DiskDrive.getDriveID();
			for(String disk: storageDiskList) {
				Map<String, String> diskProperties = Win32_DiskDrive.getDrive(disk);
				
				ps.setString(1, HARDWAREID);
				ps.setString(2, disk);
				ps.setString(3, diskProperties.get("Caption"));
				ps.setString(4, diskProperties.get("SerialNumber"));
				
				Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
				ps.setString(5, String.valueOf(diskSize)+" GB");
				
				ps.setString(6, diskProperties.get("Status"));
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "Storage info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "Storage info could not be retrieved\n"+e.getMessage()).setVisible(true);
		} catch (NumberFormatException e) {
			new ExceptionUI("Database Disk Size Parse Error", e.getMessage()).setVisible(true);
		}
	}
	
	public static final void insert(String username, String location) {
		connect = DatabaseConnectivity.initialize();
		if(insertHardwareId(username, location)) {
			try(ExecutorService dumperThreads = Executors.newFixedThreadPool(7)){
				Runnable mainboard = ()->insertMainboard();
				Runnable cpu = ()->insertCpu();
				Runnable memory = ()->insertMemory();
				Runnable gpu = ()->insertGpu();
				Runnable storage = ()->insertStorage();
				Runnable network = ()->insertNetwork();
				Runnable os = ()->insertOperatingSystem();
				
				dumperThreads.submit(mainboard);
				dumperThreads.submit(cpu);
				dumperThreads.submit(memory);
				dumperThreads.submit(gpu);
				dumperThreads.submit(storage);
				dumperThreads.submit(network);
				dumperThreads.submit(os);
			}
		}
		DatabaseConnectivity.close(connect);
	}
}
