package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.CrewMember;

import java.util.Collection;
import java.util.Scanner;

public interface ReadFromFileCrewMemberStrategy {

    void read(Scanner in, Collection<CrewMember> crewMembers);
}
