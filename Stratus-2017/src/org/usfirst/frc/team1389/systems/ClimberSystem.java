package org.usfirst.frc.team1389.systems;


import org.usfirst.frc.team1389.robot.RobotHardware;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.PercentIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.PercentOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.hardware.value_types.Value;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.util.state.State;
import com.team1389.watch.Watchable;

public class ClimberSystem extends Subsystem {

	private RangeIn<Value> climberCurrent;
	protected PercentOut climberVoltageOut;
	private PercentIn climberAxis;
	private RangeIn<Percent> rightTrigger;

	public ClimberSystem(RangeIn<Value> climberCurrent, PercentIn climberAxis, PercentOut climberVoltageOut, RangeIn<Percent> rightTrigger) {
		this.climberCurrent = climberCurrent;
		this.climberAxis = climberAxis;
		this.climberVoltageOut = climberVoltageOut;
		this.rightTrigger = rightTrigger;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem.put(rightTrigger.getWatchable("climberButton"), climberCurrent.getWatchable("climberCurrent"),
				climberVoltageOut.getWatchable("climberVoltage"));
	}

	@Override
	public String getName() {
		return "climber";
	}

	@Override
	public void init() {
	
	}
	


	@Override
	public void update() {
		climberVoltageOut.set(rightTrigger.get());
		/*if(climberButton.get()){
			climberVoltageOut.set(1);
		}
		else{
			climberVoltageOut.set(0);
		}
	*/
}
}
