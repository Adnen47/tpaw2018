package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.GroupDTO;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.Group;
import com.advyteam.sygha.entity.User;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();
    Group addGroup(GroupDTO group, User user);
    Group updateGroup(GroupDTO group, User user);
    Group deleteGroup(Group group);
    Group deleteCompany(Group group, Company company);

}
