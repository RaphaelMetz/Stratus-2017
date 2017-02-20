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

public class HopperAndLowGoals extends AutoModeBase {
	/*
	 * TODO
	 * Make sure the path's work, then add the ball intake
	 */
	RobotSoftware robot;
	PathFollowingSystem cont;

	public HopperAndLowGoals(RobotSoftware robot) {
		this.robot = robot;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem.put(cont);
	}

	@Override
	public String getName() {
		return "Hopper and low goals";
	}

	@Override
	protected void routine() throws AutoModeEndedException {
		PathFollowingSystem.Constants constants = new PathFollowingSystem.Constants(200, 20, 240, .17, .004, 0, 0.65,
				.6);
		PathFollowingSystem cont = new PathFollowingSystem(robot.voltageDrive.getAsTank(),
				robot.frontLeft.getPositionInput().<PositionEncoderIn>setTicksPerRotation(1440).getInches(),
				robot.frontRight.getPositionInput().getInches().<PositionEncoderIn>setTicksPerRotation(1440)
						.getInches(),
				robot.gyro.getAngleInput(), constants);

		Waypoint[] hopper = new Waypoint[] { new Waypoint(0, 0, 0), new Waypoint(-76, -65, Pathfinder.d2r(90)) };
		Waypoint[] goal = new Waypoint[] { new Waypoint(-80, -30, Pathfinder.d2r(80)),
				new Waypoint(-20, -15, Pathfinder.d2r(-20)) };
		Waypoint[] baseline = new Waypoint[] { new Waypoint(-20, -15, Pathfinder.d2r(-20)),
				new Waypoint(-150, 30, Pathfinder.d2r(180)) };

		runCommand(CommandUtil.combineSequential(cont.new PathFollowCommand(hopper, false, -180),
				CommandUtil.createCommand(
						() -> robot.frontLeft.getPositionInput().offset(robot.frontLeft.getPositionInput().get())),
				CommandUtil.createCommand(
						() -> robot.frontRight.getPositionInput().offset(robot.frontRight.getPositionInput().get())),
				cont.new PathFollowCommand(goal, false, -180), cont.new PathFollowCommand(baseline, false, -180)));

	}

}
