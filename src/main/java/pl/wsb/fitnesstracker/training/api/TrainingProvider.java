package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Training getTraining(Long trainingId);

    List<Training> getTrainings();

    Training createTraining(Training training);

    List<Training> getAllFinishedTrainingsAfterTime(Date afterTime);

    List<Training> getTrainingsByActivityType(ActivityType activityType);

    Training updateTraining(String trainingId, Training training);

//    Training getTrainingsForDedicatedUser(String userId);
}
