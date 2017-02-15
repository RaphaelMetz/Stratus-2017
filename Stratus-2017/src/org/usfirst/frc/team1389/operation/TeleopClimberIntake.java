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

	public TeleopClimberIntake(RangeIn<Value> climberCurrent, PercentIn climberAxis, PercentOut climberVoltageOut,
			RangeIn<Percent> rightTrigger) {
		super(climberCurrent, climberAxis, climberVoltageOut, rightTrigger);
		// TODO Auto-generated constructor stub
	}

	

}
