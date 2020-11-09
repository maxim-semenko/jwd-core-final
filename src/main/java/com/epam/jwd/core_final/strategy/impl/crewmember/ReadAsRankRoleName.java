package com.epam.jwd.core_final.strategy.impl.crewmember;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.ReadFromFileCrewMemberStrategy;

import java.util.Collection;
import java.util.Scanner;

public class ReadAsRankRoleName implements ReadFromFileCrewMemberStrategy {
    @Override
    public void read(Scanner in, Collection<CrewMember> crewMembers) {
        while (in.hasNextLine()) {
            String[] tempArray = in.nextLine().split(",");

            Rank tempRank = Rank.resolveRankById(Integer.parseInt(String.valueOf(tempArray[0].charAt(0))));
            Role tempRole = Role.resolveRoleById(Integer.parseInt(tempArray[1]));
            String tempName = tempArray[2];

            try {
                crewMembers.add(CrewServiceImpl.getInstance().createCrewMember(tempRole, tempName, tempRank));
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
    }
}
