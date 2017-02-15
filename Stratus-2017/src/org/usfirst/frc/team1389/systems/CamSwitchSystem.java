package org.usfirst.frc.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CamSwitchSystem extends Subsystem{
	DigitalIn switchButton;
	UsbCamera cam0, cam1;
	MjpegServer switcher;
	public CamSwitchSystem(DigitalIn switchButton){
		this.switchButton = switchButton;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
	cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
	cam1 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
	switcher.setSource(cam0);
	}

	@Override
	public void update() {
		
		switcher.setSource((switcher.getSource()== cam1)? cam0:cam1);
		
	}

}
