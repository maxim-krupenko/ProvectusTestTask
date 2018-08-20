package com.tt.api.controllers;

import com.tt.api.entities.User;
import com.tt.api.services.UsersOperationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UsersController extends AbstractCrudController<User> {

    @Setter(onMethod=@__({@Autowired}))
    private UsersOperationService usersOperationService;

    @GetMapping("/current")
    public User getCurrentUserInfo() {
        return usersOperationService.getCurrentUser();
    }

    @PutMapping("/{userId}/assign-company/{companyId}")
    public ResponseEntity<User> assignCompanyToUser(@PathVariable String userId, @PathVariable String companyId) {
        User user = usersOperationService.assignCompanyToUser(userId, companyId);
        if(user == null) return notFound().build();
        return ok(user);
    }
}
