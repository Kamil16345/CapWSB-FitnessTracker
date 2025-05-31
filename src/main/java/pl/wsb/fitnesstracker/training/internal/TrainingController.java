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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> getTrainings() {
        return trainingService.getTrainings().stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training savedTraining = trainingService.createTraining(trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(savedTraining);
    }

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
}
