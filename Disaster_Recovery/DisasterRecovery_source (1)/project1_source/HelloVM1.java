package com.vmware.vim25.mo.samples;

import java.net.URL;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class HelloVM1 
{
	public static void main(String[] args) throws Exception
	{
		long start = System.currentTimeMillis();
		URL url = new URL("https://130.65.132.240/sdk");
		ServiceInstance si = new ServiceInstance(url, "administrator", "12!@qwQW", true);
		long end = System.currentTimeMillis();
		System.out.println("time taken:" + (end-start));
		Folder rootFolder = si.getRootFolder();
		String name = rootFolder.getName();
		System.out.println("root:" + name);
		ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
		if(mes==null || mes.length ==0)
		{
			return;
		}
		
		VirtualMachine vm = (VirtualMachine) mes[0]; 
		
		VirtualMachineConfigInfo vminfo = vm.getConfig();
		VirtualMachineCapability vmc = vm.getCapability();
		Network[] vmNw = vm.getNetworks();
		ManagedEntityStatus vmConfigStat = vm.getConfigStatus();
		ManagedEntityStatus vmHeartBeatStatus = vm.getGuestHeartbeatStatus();
		ResourceAllocationInfo vmCpu =vminfo.getCpuAllocation();
		
		
		vm.getResourcePool();
		System.out.println("Hello " + vm.getName());
		System.out.println("GuestOS: " + vminfo.getGuestFullName());
		System.out.println("NetworkInfo: " + vmNw);
		System.out.println("HeartBeatStatus: " + vmHeartBeatStatus);
		System.out.println("ConfigStatus: " + vmConfigStat);
		System.out.println("CPU: "+ vmCpu);
		
		System.out.println("Resource pool: " +vm.getResourcePool());
		System.out.println("Resource pool Owner: " +vm.getResourcePool().getOwner());
		System.out.println("Resource pool Parent: " +vm.getResourcePool().getParent());
		//System.out.println("Resource pool resource pools: " +vm.getResourcePool().getResourcePools().toString());
		System.out.println("Resource pool Values: " +vm.getResourcePool().getValues());
		System.out.println("Resource pool VM: " +vm.getResourcePool().getVMs().toString());
		//System.out.println("Resource pool Summary: " +vm.getResourcePool().getSummary().toString());
		System.out.println("");
		
		System.out.println("VM Datastores: " +vm.getDatastores().toString());
	    System.out.println("VM Config: " +vm.getConfig().toString());
		System.out.println("VM Guest: " +vm.getGuest().getIpAddress());
		System.out.println("VM Parent: " +vm.getParent());
		System.out.println("VM resouce pool: " +vm.getResourcePool());
		System.out.println("VM Runtime: " +vm.getRuntime().toString());
		System.out.println("VM Storage: " +vm.getStorage().toString());
		System.out.println("VM Values: " +vm.getValues());
		
		System.out.println("");
		
		
		System.out.println("GuestOS: " + vminfo.getGuestFullName());
		System.out.println("GuestID: " + vminfo.getGuestId());
		System.out.println("GuestName: " + vminfo.getName());
		System.out.println("GuestDataStore URL: " + vminfo.getDatastoreUrl());
		System.out.println("");
		System.out.println("Multiple snapshot supported: "
				+ vmc.isMultipleSnapshotsSupported());
		si.getServerConnection().logout();
	}

}