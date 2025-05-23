package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

record UserIdAndEmailDto(@Nullable Long Id, String email) {

}
