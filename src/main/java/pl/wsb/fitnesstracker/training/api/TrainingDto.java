package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDateTime;
import java.util.Date;

public record TrainingDto(@Nullable Long id, Date startTime, Date endTime,
                          ActivityType activityType, Float distance, Float averageSpeed){}
//                          @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,) {


//                    "userId": "%s",
//                            "startTime": "2024-04-01T11:00:00",
//                            "endTime": "2024-04-01T11:00:00",
//                            "activityType": "RUNNING",
//                            "distance": 10.52,
//                            "averageSpeed": 8.2