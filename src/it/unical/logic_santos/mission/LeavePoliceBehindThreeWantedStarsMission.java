/**
 * 
 */
package it.unical.logic_santos.mission;

import it.unical.logic_santos.logging.ILogger;
import it.unical.logic_santos.logging.ILoggingEvent;
import it.unical.logic_santos.logging.WantedStarsLoggingEvent;

/**
 * @author Agostino
 *
 */
public class LeavePoliceBehindThreeWantedStarsMission implements IMission {

	private boolean missionComplete=false;
	private boolean threeWantedStars=false;

	public LeavePoliceBehindThreeWantedStarsMission() {}
	
	@Override
	public void onLoggingEvent(ILoggingEvent loggingEvent, ILogger logger) {
		if ( loggingEvent instanceof WantedStarsLoggingEvent ) {
			
			WantedStarsLoggingEvent wantedStarLoggingEvent = (WantedStarsLoggingEvent) loggingEvent;
			if ( (wantedStarLoggingEvent.getEventType()==WantedStarsLoggingEvent.WANTED_STARS_INCREASE_EVENT) &&
					(wantedStarLoggingEvent.getWantedStarsCount()>=3) )
				threeWantedStars=true;
			else if ( (wantedStarLoggingEvent.getEventType()==WantedStarsLoggingEvent.WANTED_STARS_REDUCTION_EVENT) &&
					(wantedStarLoggingEvent.getWantedStarsCount()==0) &&
					threeWantedStars )
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
		return ( new LeavePoliceBehindThreeWantedStarsMission() );
	}

	@Override
	public String getCompanyName() {
		return "it.unical.logic_santos";
	}

	@Override
	public String getDescription() {
		return "The goal of the Mission is to leave the Police system behind after you reach al least 3 wanted stars.\n" +
			   "To invoke the Police system you can use some strategies like:\n" +
			   "	- shoot some bullet\n" +
			   "	- steal some vehicle\n" +
			   "When you reach this goal the Mission is going to be completed.";
	}

	@Override
	public float getGain() {
		return 1000.0f;
	}

	@Override
	public String getName() {
		return "Leave Police Behind Beginner";
	}

	@Override
	public boolean isComplete() {
		return missionComplete;
	}
}
