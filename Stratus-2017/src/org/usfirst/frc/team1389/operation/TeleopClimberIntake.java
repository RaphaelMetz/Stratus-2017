package org.usfirst.frc.team1389.operation;

import org.usfirst.frc.team1389.systems.ClimberSystem;

import com.team1389.hardware.inputs.software.PercentIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.PercentOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.hardware.value_types.Value;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class TeleopClimberIntake extends ClimberSystem {

	public TeleopClimberIntake(RangeIn<Value> climberCurrent, PercentOut climberVoltageOut,
			RangeIn<Percent> rightTrigger) {
		super(climberCurrent, climberVoltageOut, rightTrigger);
		this.climberVoltageOut = climberVoltageOut;
		this.climberCurrent = climberCurrent;
		this.rightTrigger = rightTrigger;
		
		
	}

	

}
