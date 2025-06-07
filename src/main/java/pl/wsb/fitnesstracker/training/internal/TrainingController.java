package pl.wsb.fitnesstracker.training.internal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Retrieves a list of all trainings.
     * Converts training entities to DTOs before returning them.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> getTrainings() {
        return trainingService.getTrainings().stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Creates a new training record based on the provided training data.
     * Returns the newly created training as a DTO with all generated fields.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training savedTraining = trainingService.createTraining(trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Retrieves all finished trainings that occurred after the specified date.
     * Date should be provided in 'yyyy-MM-dd' format.
     */
    @GetMapping("/finished/{afterTime}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> getAllFinishedTrainingsAfterTime(@PathVariable String afterTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = simpleDateFormat.parse(afterTime);
        log.info("Received data in controller: {}", afterTime);
        List<Training> allFinishedTrainingsAfterTime = trainingService.getAllFinishedTrainingsAfterTime(parsedDate);
        log.info("Finished trainings after time: {}", allFinishedTrainingsAfterTime);
        return allFinishedTrainingsAfterTime.stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings filtered by the specified activity type.
     * Activity type is provided as a request parameter.
     */
    @GetMapping("/activityType")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> getAllTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing training identified by the trainingId with new data.
     * Returns the updated training as a DTO.
     */
    @PutMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTraining(@RequestBody @Valid TrainingDto trainingDto, @PathVariable String trainingId) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training updatedTraining = trainingService.updateTraining(trainingId, training);
        return trainingMapper.toDto(updatedTraining);
    }

}
