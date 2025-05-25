package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.List;
import java.util.Optional;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Training getTraining(final Long trainingId) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        if (training.isPresent()) {
            return training.get();
        } else {
            throw new UnsupportedOperationException("Not finished yet");
        }
    }

    @Override
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public TrainingDto createTraining(TrainingDto trainingDto) {
        return trainingRepository.save(trainingDto);
    }
}
