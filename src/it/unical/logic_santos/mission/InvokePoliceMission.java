/**
 * 
 */
package it.unical.logic_santos.mission;

import it.unical.logic_santos.logging.ILogger;
import it.unical.logic_santos.logging.ILoggingEvent;
import it.unical.logic_santos.logging.WantedStarsLoggingEvent;
import it.unical.logic_santos.mission.IMission;

/**
 * @author Agostino
 *
 */
public class InvokePoliceMission implements IMission {
	
	private boolean missionComplete=false;

	public InvokePoliceMission() {}
	
	@Override
	public void onLoggingEvent(ILoggingEvent loggingEvent, ILogger logger) {
		if ( loggingEvent instanceof WantedStarsLoggingEvent ) {
			
			WantedStarsLoggingEvent wantedStarLoggingEvent = (WantedStarsLoggingEvent) loggingEvent;
			if ( wantedStarLoggingEvent.getEventType()==WantedStarsLoggingEvent.WANTED_STARS_INCREASE_EVENT )
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
		return ( new InvokePoliceMission() );
	}

	@Override
	public String getCompanyName() {
		return "it.unical.logic_santos";
	}

	@Override
	public String getDescription() {
		return "The goal of the Mission is to invoke the Police system.\n" +
			   "To invoke the Police system you can use some strategies like:\n" +
			   "	- shoot some bullet\n" +
			   "	- steal some vehicle\n" +
			   "When you reach this goal the Mission is going to be completed.";
	}

	@Override
	public float getGain() {
		return 300.0f;
	}

	@Override
	public String getName() {
		return "Invoke Police";
	}

	@Override
	public boolean isComplete() {
		return missionComplete;
	}

}
