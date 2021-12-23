package com.senior.reporTown.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    public ApplicationUser selectApplicationUserByUsername(String username);
}
