package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.GroupDTO;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.Group;
import com.advyteam.sygha.entity.ReferentielAudit;
import com.advyteam.sygha.entity.User;
import com.advyteam.sygha.repository.CompanyRepository;
import com.advyteam.sygha.repository.GroupRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @Override
    public Group updateGroup(GroupDTO groupDTO, User user) {
        Group group = groupRepository.findByCode(groupDTO.getCode());
        group.setLabel(groupDTO.getLabel());
        group.setAddress(groupDTO.getAddress());
        group.setCompanies(deleteDoublon(groupDTO.getCompanies()));

        Referentiel referentiel = new Referentiel();
        group.setReferentielAudit(referentiel.referentiel(user));

        return groupRepository.save(group);
    }

    @Override
    public Group addGroup(GroupDTO groupDTO, User user) {
        Group group = new Group();

        group.setCode(groupDTO.getCode());
        group.setLabel(groupDTO.getLabel());
        group.setAddress(groupDTO.getAddress());
        group.setCompanies(deleteDoublon(groupDTO.getCompanies()));

        Referentiel referentiel = new Referentiel();
        group.setReferentielAudit(referentiel.referentiel(user));

        return groupRepository.save(group);
    }

    @Override
    public Group deleteGroup(Group group){
        groupRepository.deleteById(group.getId());
        return group;
    }

    @Override
    public Group deleteCompany(Group group, Company company){
        group.getCompanies().remove(company);
        group.setCompanies(group.getCompanies());
        return group;
    }


    private List<Company> deleteDoublon(List<Company> companies){
        ArrayList<Company> companiesNotDuplicated = new ArrayList<>();
        for(Company company: companies){
            if(!companiesNotDuplicated.contains(company)){
                companiesNotDuplicated.add(company);
            }
        }
        return  companiesNotDuplicated;
    }

}
