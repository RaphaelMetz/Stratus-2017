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

public class GearAndLowGoals extends AutoModeBase {

	RobotSoftware robot;
	CommandScheduler scheduler = new CommandScheduler();
	PathFollowingSystem cont;

	public GearAndLowGoals(RobotSoftware robot) {
		this.robot = robot;
		PathFollowingSystem.Constants constants = new PathFollowingSystem.Constants(200, 20, 12, .001, .004, 0, 0.65,
				.6);
		cont = new PathFollowingSystem(robot.voltageDrive.getAsTank(),
				robot.frontLeft.getPositionInput().<PositionEncoderIn>setTicksPerRotation(1440).getInches(),
				robot.frontRight.getPositionInput().<PositionEncoderIn>setTicksPerRotation(1440).getInches(),
				robot.gyro.getAngleInput(), constants);

	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem.put(robot.gyro, robot.voltageDrive, robot.armAngle.getWatchable("Arm angle"));
	}

	@Override
	public String getName() {
		return "Gear & Low Goal";
	}

	@Override
	protected void routine() throws AutoModeEndedException {
		Waypoint[] points = new Waypoint[] { new Waypoint(0, 30, 0), new Waypoint(-101, 56, Pathfinder.d2r(-60)) };
		Waypoint[] points2 = new Waypoint[] { new Waypoint(0, 0, Pathfinder.d2r(-60)),
				new Waypoint(18, -90, Pathfinder.d2r(-90)) };
		scheduler.schedule(CommandUtil.combineSequential(cont.new PathFollowCommand(points, false, 180),
				CommandUtil.createCommand(
						() -> robot.frontLeft.getPositionInput().offset(robot.frontLeft.getPositionInput().get())),
				cont.new PathFollowCommand(points2, false, 180)));
	}

}
