package clipper.hotel.validators;

import clipper.hotel.exceptions.EntityValidationException;
import clipper.hotel.models.Accommodation;

import java.time.LocalDate;

public class AccommodationValidator implements Validator<Accommodation> {

    @Override
    public void validate(Accommodation obj) {
        requiredFieldsAreFilled(obj);
        checkInIsBeforeCheckOut(obj);
        checkInIsNotInPast(obj);
    }

    public void requiredFieldsAreFilled(Accommodation obj) {
        if (!obj.requiredFieldsAreNotNull()) {
            throw new EntityValidationException("Todos os campos devem ser preenchidos!");
        }
    }

    public void checkInIsBeforeCheckOut(Accommodation obj) {
        if (!obj.getCheckInDate().isBefore(obj.getCheckOutDate())) {
            throw new EntityValidationException("A data de check-in deve ser antes da data de check-out!!");
        }
    }

    public void checkInIsNotInPast(Accommodation obj) {
        if (!obj.getCheckInDate().isAfter(LocalDate.now().minusDays(1))) {
            throw new EntityValidationException("A data de check-in deve não deve ser no passado");
        }
    }
}
