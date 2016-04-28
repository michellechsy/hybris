package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppytrail.data.MatchSummaryData;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import org.springframework.beans.factory.annotation.Required;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by michelle on 16-4-27.
 */
public class DefaultStadiumFacade implements StadiumFacade {
    private StadiumService stadiumService;

    @Override
    public List<StadiumData> getStadiums() {
        final List<StadiumModel> stadiumModels = stadiumService.getStadiums();
        final List<StadiumData> stadiumDataList = new ArrayList<StadiumData>();
        for (final StadiumModel sm : stadiumModels) {
            final StadiumData sfd = new StadiumData();
            sfd.setName(sm.getCode());
            sfd.setCapacity(sm.getCapacity().toString());
            stadiumDataList.add(sfd);
        }
        return stadiumDataList;
    }

    @Override
    public StadiumData getStadium(String name) {
        StadiumModel stadium = null;
        if (name != null) {
            stadium = stadiumService.getStadiumForCode(name);
            if (stadium == null) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("Stadium with name " + name + " not found.");
        }

        final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();
        if (stadium.getMatches() != null) {
            final Iterator<MatchModel> matchesIterator = stadium.getMatches().iterator();
            while (matchesIterator.hasNext()) {
                final MatchModel match = matchesIterator.next();
                final MatchSummaryData summary = new MatchSummaryData();
                final String homeTeam = match.getHomeTeam().getName();
                final String guestTeam = match.getGuestTeam().getName();
                final Date matchDate = match.getDate();
                final String guestGoal = match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString();
                final String homeGoal = match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString();
                summary.setHomeTeam(homeTeam);
                summary.setHomeGoals(homeGoal);
                summary.setDate(matchDate);
                summary.setGuestTeam(guestTeam);
                summary.setGuestGoals(guestGoal);
                final String formattedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(matchDate);
                final String matchSummaryFormatted = homeTeam + ":( " + homeGoal + ") " + guestTeam + " ( " + guestGoal + ") " + formattedDate;
                summary.setMatchSummaryFormatted(matchSummaryFormatted);
                matchSummary.add(summary);
            }
        }

        final StadiumData stadiumData = new StadiumData();
        stadiumData.setName(stadium.getCode());
        stadiumData.setCapacity(stadium.getCapacity().toString());
        stadiumData.setMatches(matchSummary);
        return stadiumData;
    }

    @Required
    public void setStadiumService(final StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
}
