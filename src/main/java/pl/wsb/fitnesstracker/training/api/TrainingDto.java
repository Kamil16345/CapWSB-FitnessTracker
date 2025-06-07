package pl.wsb.fitnesstracker.training.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

public record TrainingDto(
        @Nullable Long id,
        @Nullable UserDto user,
        @Nullable Long userId,
        @NotNull(message = "Add start time")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
        Date startTime,
        @NotNull(message = "Add end time")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
        Date endTime,
        @NotNull(message = "Add activity type")
        ActivityType activityType,
        @PositiveOrZero(message = "Distance must be positive or zero")
        double distance,
        @PositiveOrZero(message = "Average speed must be positive or zero")
        double averageSpeed) {
}