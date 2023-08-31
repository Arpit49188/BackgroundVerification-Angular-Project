package com.background.verification.security.service;

import com.background.verification.security.model.Role;

public interface RoleService {
    Role findByName(String name);
}
