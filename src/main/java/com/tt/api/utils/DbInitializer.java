package com.tt.api.utils;

import com.tt.api.entities.Company;
import com.tt.api.entities.Role;
import com.tt.api.entities.User;
import com.tt.api.repositories.CompanyRepository;
import com.tt.api.repositories.UsersRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CompanyRepository companyRepository;

    public DbInitializer(UsersRepository repository,
                         CompanyRepository companyRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        usersRepository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.companyRepository = companyRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDB() {
        final String ADMIN_LOGIN = "admin";
        final String ADMIN_PASSWORD = "admin";
        final String SIMPLE_USER_LOGIN = "login";
        final String SIMPLE_USER_PASSWORD = "password";
        final String DEFAULT_COMPANY_NAME = "COMPANY_ONE";

        if (usersRepository.count() == 0) {
            User admin = new User();
            admin.setLogin(ADMIN_LOGIN);
            admin.setUserRole(Role.ADMIN);
            admin.setPassword(bCryptPasswordEncoder.encode(ADMIN_PASSWORD));
            usersRepository.save(admin);

            User simpleUser = new User();
            simpleUser.setLogin(SIMPLE_USER_LOGIN);
            simpleUser.setPassword(bCryptPasswordEncoder.encode(SIMPLE_USER_PASSWORD));
            simpleUser.setUserRole(Role.USER);
            usersRepository.save(simpleUser);
        }
        if (companyRepository.count() == 0) {
            Company company = new Company();
            company.setName(DEFAULT_COMPANY_NAME);
            Company saved = companyRepository.save(company);
            usersRepository
                    .findByLogin(SIMPLE_USER_LOGIN)
                    .map(user -> {
                        user.setCompany(saved);
                        return usersRepository.save(user);
                    });
        }
    }
}
