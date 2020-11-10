package domain;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import org.junit.Assert;
import org.junit.Test;


public class CrewMemberTest {

    @Test
    public void setRankTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setRank(Rank.CAPTAIN);
        Assert.assertEquals(crewMember.getRank(), Rank.CAPTAIN);
    }

    @Test
    public void setRoleTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setRole(Role.COMMANDER);
        Assert.assertEquals(crewMember.getRole(), Role.COMMANDER);
    }

    @Test
    public void setReadyForNextMissionsTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setReadyForNextMissions(true);
        Assert.assertTrue(crewMember.getReadyForNextMissions());
    }

    @Test
    public void equalsTest() throws InvalidStateException {
        CrewMember crewMember1 = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        CrewMember crewMember2 = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        boolean expected = crewMember1.equals(crewMember2);
        Assert.assertTrue(expected);
    }

    @Test
    public void toStringTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setId(1L);
        String expected = "CrewMember{Name=Test, role=COMMANDER, rank=CAPTAIN, isReadyForNextMissions=true, id=1'}";
        Assert.assertEquals(expected, crewMember.toString());
    }

}
