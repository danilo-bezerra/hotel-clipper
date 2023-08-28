package clipper.hotel.validators;

public interface Validator<T> {
    public abstract void validate(T obj) ;
}
