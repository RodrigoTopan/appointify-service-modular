package usecase.user.contract.command;

import domain.valueobject.UserRole;

public record CreateUser(
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        UserRole role
) {
}

