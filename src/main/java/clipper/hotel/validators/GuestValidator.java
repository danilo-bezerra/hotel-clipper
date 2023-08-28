package clipper.hotel.validators;

import clipper.hotel.exceptions.EntityValidationException;
import clipper.hotel.models.Accommodation;
import clipper.hotel.models.Guest;

import java.time.LocalDate;

public class GuestValidator implements Validator<Guest> {
    @Override
    public void validate(Guest obj) {
        requiredFieldsAreFilled(obj);
        birthDateIsInPast(obj);
    }

    public void requiredFieldsAreFilled(Guest obj) {
        if (!obj.requiredFieldsAreNotNull()) {
            throw new EntityValidationException("Todos os campos devem ser preenchidos!");
        }
    }

    public void birthDateIsInPast(Guest obj) {
        if (!obj.getBirthDate().isBefore(LocalDate.now().minusYears(18))) {
            throw new EntityValidationException("O hospede deve ter ao menos 18 anos!");
        }
    }
}
