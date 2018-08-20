package com.tt.api.services.impl;

import com.tt.api.entities.Company;
import com.tt.api.entities.User;
import com.tt.api.repositories.CompanyRepository;
import com.tt.api.repositories.UsersRepository;
import com.tt.api.services.CrudService;
import com.tt.api.services.UsersOperationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl
        extends AbstractCrudServiceImpl<User>
        implements CrudService<User>, UsersOperationService {

    @Setter(onMethod = @__({@Autowired}))
    private UsersRepository usersRepository;

    @Setter(onMethod = @__({@Autowired}))
    private CompanyRepository companyRepository;

    @Override
    public User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return usersRepository.findByLogin(login).orElse(new User());
    }

    @Override
    public User assignCompanyToUser(String userId, String companyId) {
        Optional<User> userById = usersRepository.findById(userId);
        Optional<Company> companyById = companyRepository.findById(companyId);

        if(!userById.isPresent() || !companyById.isPresent())
            return null;

        return userById.map(user -> {
            user.setCompany(companyById.get());
            return usersRepository.save(user);
        }).get();
    }
}
