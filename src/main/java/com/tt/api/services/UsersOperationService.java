package com.tt.api.services;

import com.tt.api.entities.User;

public interface UsersOperationService {
    User getCurrentUser();

    User assignCompanyToUser(String userId, String companyId);
}
