/**
 * 
 */
package it.unical.logic_santos.mission;

import it.unical.logic_santos.logging.ILogger;
import it.unical.logic_santos.logging.ILoggingEvent;
import it.unical.logic_santos.logging.VehicleLoggingEvent;

/**
 * @author Agostino
 *
 */
public class LeaveVehicleDriverBehindMission implements IMission {

	private boolean missionComplete=false;

	public LeaveVehicleDriverBehindMission() {}
	
	@Override
	public void onLoggingEvent(ILoggingEvent loggingEvent, ILogger logger) {
		if ( loggingEvent instanceof VehicleLoggingEvent ) {
			
			VehicleLoggingEvent vehicleLoggingEvent = (VehicleLoggingEvent) loggingEvent;
			if ( vehicleLoggingEvent.getEventType()==VehicleLoggingEvent.LEAVE_VEHICLE_DRIVER_BEHIND_EVENT )
				missionComplete=true;
		}
		
	}

	@Override
	public boolean check() {
		return missionComplete;
	}

	@Override
	public boolean check(float tpf) {
		return missionComplete;
	}

	@Override
	public IMission cloneMission() {
		return ( new LeaveVehicleDriverBehindMission() );
	}

	@Override
	public String getCompanyName() {
		return "it.unical.logic_santos";
	}

	@Override
	public String getDescription() {
		return "The goal of the Mission is to stole a vehicle and leave its driver behind.\n" +
			   "When you reach this goal the Mission is going to be completed.";
	}

	@Override
	public float getGain() {
		return 1000.0f;
	}

	@Override
	public String getName() {
		return "Stole Vehicle";
	}

	@Override
	public boolean isComplete() {
		return missionComplete;
	}
}
