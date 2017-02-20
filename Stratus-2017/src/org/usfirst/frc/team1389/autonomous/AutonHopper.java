package org.usfirst.frc.team1389.autonomous;

import org.usfirst.frc.team1389.robot.RobotSoftware;

import com.team1389.auto.AutoModeBase;
import com.team1389.auto.AutoModeEndedException;
import com.team1389.command_framework.CommandScheduler;
import com.team1389.command_framework.CommandUtil;
import com.team1389.hardware.inputs.software.PositionEncoderIn;
import com.team1389.trajectory.PathFollowingSystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class AutonHopper extends AutoModeBase {
	RobotSoftware robot;
	CommandScheduler scheduler;
	PathFollowingSystem cont;
	public AutonHopper(RobotSoftware robot) {
		this.robot = robot;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem.put(robot.voltageDrive);
	}

	@Override
	public String getName() {
		return "AutonHopper";
	}

	@Override
	protected void routine() throws AutoModeEndedException {
		PathFollowingSystem.Constants constants = new PathFollowingSystem.Constants(200, 20, 240, .17, .004, 0, 0.65,
				.6);
		cont = new PathFollowingSystem(robot.voltageDrive.getAsTank(),
				robot.frontLeft.getPositionInput().<PositionEncoderIn>setTicksPerRotation(1440).getInches(),
				robot.frontRight.getPositionInput().getInches().<PositionEncoderIn>setTicksPerRotation(1440)
						.getInches(),
				robot.gyro.getAngleInput(), constants);

		Waypoint[] hopper1 = new Waypoint[] { new Waypoint(0, 0, 0), new Waypoint(-76, -65, Pathfinder.d2r(90)) };
		Waypoint[] backUp = new Waypoint[] { new Waypoint(16, -15, Pathfinder.d2r(90)), new Waypoint(10, 10, 0) };

		scheduler.schedule(CommandUtil.combineSequential(cont.new PathFollowCommand(hopper1, false, -180),
				CommandUtil.createCommand(() -> cont.leftPos.offset(-cont.leftPos.get())),CommandUtil.createCommand(()-> cont.rightPos.offset(-cont.rightPos.get())),cont.new PathFollowCommand(backUp, false, -180)));

	}

}
