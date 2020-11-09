package com.epam.jwd.core_final.strategy.impl.crewmember;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.ReadFromFileCrewMemberStrategy;

import java.util.Collection;
import java.util.Scanner;

public class ReadAsNameRoleRank implements ReadFromFileCrewMemberStrategy {
    @Override
    public void read(Scanner in, Collection<CrewMember> crewMembers) {
        while (in.hasNextLine()) {
            String[] inputDataArray = in.nextLine().split(",");
            try {
                crewMembers.add(CrewServiceImpl.getInstance().createCrewMember(
                        Role.resolveRoleById(Integer.parseInt(inputDataArray[1])),
                        inputDataArray[0],
                        Rank.resolveRankById(Integer.parseInt(String.valueOf(inputDataArray[2].charAt(0))))));
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
    }
}
