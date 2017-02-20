package org.usfirst.frc.team1389.watchers;

import org.usfirst.frc.team1389.autonomous.Selector;
import org.usfirst.frc.team1389.autonomous.Option;

import com.team1389.auto.AutoModeBase;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardInput {
	private static DashboardInput instance = new DashboardInput();

	public static DashboardInput getInstance() {
		return instance;
	}

	public DashboardInput() {
		init();
	}

	private SendableChooser<Option> autonSelector;

	public void init() {
		autonSelector = new SendableChooser<Option>();
		for (Option autonOption : Option.values()) {
			autonSelector.addObject(autonOption.name, autonOption);
		}

		SmartDashboard.putData(SELECTED_AUTO_MODE, autonSelector);
	}

	private static final String SELECTED_AUTO_MODE = "selected_auto_mode";
	private static final Option DEFAULT_MODE = Option.DRIVE_STRAIGHT;

	public AutoModeBase getSelectedAutonMode() {
		String autoModeString = SmartDashboard.getString(SELECTED_AUTO_MODE, DEFAULT_MODE.name);
		Option selectedOption = DEFAULT_MODE;
		for (Option autonOption : Option.values()) {
			if (autonOption.name.equals(autoModeString)) {
				selectedOption = autonOption;
				break;
			}
		}
		selectedOption = (Option) autonSelector.getSelected();
		return Selector.createAutoMode(selectedOption);
	}

}
