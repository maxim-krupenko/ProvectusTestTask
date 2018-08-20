package com.tt.api.controllers;

import com.tt.api.entities.Company;
import com.tt.api.services.CompaniesOperationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompaniesController extends AbstractCrudController<Company> {

    @Setter(onMethod = @__({@Autowired}))
    private CompaniesOperationService companiesOperationService;

    @GetMapping("/for-current")
    public Company getCompanyOfCurrentUser() {
        return companiesOperationService.getCompanyOfCurrentUser();
    }
}
