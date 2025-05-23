package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

record UserIdAndEmailDto(@Nullable Long id, String email) {

}
