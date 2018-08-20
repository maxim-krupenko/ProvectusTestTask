package com.tt.api.services.impl;

import com.tt.api.entities.Company;
import com.tt.api.entities.User;
import com.tt.api.repositories.UsersRepository;
import com.tt.api.services.CompaniesOperationService;
import com.tt.api.services.CrudService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CompaniesServiceImpl
        extends AbstractCrudServiceImpl<Company>
        implements CrudService<Company>, CompaniesOperationService {

    @Setter(onMethod = @__({@Autowired}))
    private UsersRepository usersRepository;

    @Override
    public Company getCompanyOfCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return usersRepository.findByLogin(login).map(User::getCompany).orElse(null);
    }
}
