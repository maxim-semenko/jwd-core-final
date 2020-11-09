package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    public static Long ID = 0L;

    private static CrewMemberFactory instance;

    public static CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(Object... args) throws InvalidStateException {
        if (args.length == 3) {
            return new CrewMember((Role) args[0], (String) args[1], (Rank) args[2], ++ID);
        } else if (args.length == 4) {
            return new CrewMember((Role) args[0], (String) args[1], (Rank) args[2], (Long) args[3]);
        } else {
            throw new InvalidStateException("The count of args is not correct!");
        }
    }

}
