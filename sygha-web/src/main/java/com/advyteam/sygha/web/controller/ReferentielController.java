package com.advyteam.sygha.web.controller;

import com.advyteam.sygha.DTO.*;
import com.advyteam.sygha.entity.*;
import com.advyteam.sygha.repository.UserRepository;
import com.advyteam.sygha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "*")
@RequestMapping("/api/ref")
@RestController
public class ReferentielController {

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private InseeService inseeService;

    @Autowired
    private CtpDsnService ctpDsnService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/establishments")
    public ResponseEntity getAllEstablishment(@RequestParam(value = "page") int pageNo,
                                              @RequestParam(value = "size") int pageSize) {
        Response establishments = establishmentService.getAllEstablishment(pageNo, pageSize);
        return ResponseEntity.accepted().body(establishments);
    }

    @PostMapping("/updateestablishment")
    public ResponseEntity updateestablishment(@RequestBody EstablishmentDTO establishment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.establishmentService.updateEstablishment(establishment, userConnecte));
    }

    @PostMapping("/addestablishment")
    public ResponseEntity addestablishment(@RequestBody EstablishmentDTO establishment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.establishmentService.addEstablishment(establishment, userConnecte));
    }

    @PostMapping("/deleteestablishment")
    public ResponseEntity deleteetablishment(@RequestBody Establishment establishment){
        return ResponseEntity.accepted().body(this.establishmentService.deleteEstablishment(establishment));
    }

    @RequestMapping("/companies")
    public ResponseEntity getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.accepted().body(companies);
    }

    @PostMapping("/updatecompany")
    public ResponseEntity updatecompany(@RequestBody CompanyDTO company){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.companyService.updateCompany(company, userConnecte));
    }

    @PostMapping("/addcompany")
    public ResponseEntity addcompany(@RequestBody CompanyDTO company){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.companyService.addCompany(company, userConnecte));
    }

    @PostMapping("/deletecompany")
    public ResponseEntity deletecompany(@RequestBody Company company){
        return ResponseEntity.accepted().body(this.companyService.deleteCompany(company));
    }

    @RequestMapping("/insee")
    public ResponseEntity getAllInsee(){
        List<Insee> inseeList = inseeService.getAllInsee();
        return ResponseEntity.accepted().body(inseeList);
    }

    @PostMapping("/updateinsee")
    public ResponseEntity updateinsee(@RequestBody InseeDTO inseeDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.inseeService.updateInsee(inseeDTO, userConnecte));
    }

    @PostMapping("/addinsee")
    public ResponseEntity addeinsee(@RequestBody InseeDTO inseeDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.inseeService.addInsee(inseeDTO, userConnecte));
    }

    @PostMapping("/deleteinsee")
    public ResponseEntity deleteinsee(@RequestBody Insee insee){
        return ResponseEntity.accepted().body(this.inseeService.deleteInsee(insee));
    }

    @RequestMapping("/ctp")
    public ResponseEntity getAllCtp(){
        List<CtpDsn> ctpList = ctpDsnService.getAllCtp();
        return ResponseEntity.accepted().body(ctpList);
    }

    @PostMapping("/updatectp")
    public ResponseEntity updatectp(@RequestBody CtpDTO ctpDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.ctpDsnService.updateCtp(ctpDTO, userConnecte));
    }

    @PostMapping("/addctp")
    public ResponseEntity addctp(@RequestBody CtpDTO ctpDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.ctpDsnService.addCtp(ctpDTO, userConnecte));
    }

    @PostMapping("/deletectp")
    public ResponseEntity deletectp(@RequestBody CtpDsn ctpDsn){
        return ResponseEntity.accepted().body(this.ctpDsnService.deleteCtp(ctpDsn));
    }

    @RequestMapping("/groups")
    public ResponseEntity getAllGroups(){
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.accepted().body(groups);
    }

    @PostMapping("/group")
    public ResponseEntity updategroup(@RequestBody GroupDTO groupDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.groupService.updateGroup(groupDTO, userConnecte));
    }

    @PostMapping("/addgroup")
    public ResponseEntity addgroup(@RequestBody GroupDTO groupDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.accepted().body(this.groupService.addGroup(groupDTO, userConnecte));
    }

    @PostMapping("/deletegroup")
    public ResponseEntity deleteGroup(@RequestBody Group group){
        return ResponseEntity.accepted().body(this.groupService.deleteGroup(group));
    }

    @PostMapping("/deletecompanygroup")
    public ResponseEntity deleteCompany(@RequestBody Group group, Company company){
        return ResponseEntity.accepted().body(this.groupService.deleteCompany(group,company));
    }

    @GetMapping("/groupexport")
    public ResponseEntity<InputStreamResource> excelGroupsReport() throws IOException {
        List<Group> listGroup = groupService.getAllGroups();

        ByteArrayInputStream in = ExportGroupService.groupsToExcel(listGroup);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=groups.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/companyexport")
    public ResponseEntity<InputStreamResource> excelCompaniesReport() throws IOException {
        List<Company> listCompany = companyService.getAllCompanies();

        ByteArrayInputStream in = ExportCompanyService.companiesToExcel(listCompany);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=companies.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
//
    @GetMapping("/establishmentexport")
    public ResponseEntity<InputStreamResource> excelEstablishmentReport() throws IOException {
        Response listEstablishment = establishmentService.getAllEstablishment(0, 100);

        ByteArrayInputStream in = ExportEstablishmentService.establishmentsToExcel(listEstablishment.establishments);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=establishments.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/inseeexport")
    public ResponseEntity<InputStreamResource> excelInseeReport() throws IOException {
        List<Insee> listInsee = inseeService.getAllInsee();

        ByteArrayInputStream in = ExportInseeService.inseesToExcel(listInsee);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=insees.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/ctpexport")
    public ResponseEntity<InputStreamResource> excelCtpReport() throws IOException {
        List<CtpDsn> listCtp = ctpDsnService.getAllCtp();

        ByteArrayInputStream in = ExportCtpService.ctpsToExcel(listCtp);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ctps.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }



}
