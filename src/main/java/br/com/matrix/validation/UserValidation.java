package br.com.matrix.validation;

import br.com.matrix.configuration.PropertiesConfig;
import br.com.matrix.model.User;
import br.com.matrix.validation.functional.ValidationResult;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static br.com.matrix.validation.functional.ValidationResult.invalid;
import static br.com.matrix.validation.functional.ValidationResult.valid;


public interface UserValidation extends Function<User, List<ValidationResult>> {

    static UserValidation nameIsNotEmpty() {
        return execution(user -> Strings.isNotBlank(user.getName()), "Name can't be empty or null");
    }

    static UserValidation emailContainsAtSign() {
        return execution(user -> user.getEmail().contains("@"), "Missing @-sign");
    }

    static UserValidation emailNotNullOrEmpty() {
        return execution(user -> Strings.isNotBlank(user.getEmail()), "E-mail can't be empty or null");
    }
    static UserValidation ageNotNullMinAndMax(PropertiesConfig propertiesConfig) {
        return execution(user -> user.getAge()
                != null && user.getAge() >= propertiesConfig.getAgeMin()
                && user.getAge() <= propertiesConfig.getAgeMax(), String.format("Age can't be empty or null and must to be between %s and %s", propertiesConfig.getAgeMin(), propertiesConfig.getAgeMax() ));
    }

    static UserValidation execution(Predicate<User> p, String message){
        return user -> p.test(user) ? List.of(valid()) : List.of(invalid(message));
    }

    default UserValidation and(UserValidation other) {
        return user -> List.of(this.apply(user).stream().findFirst().get(), other.apply(user).stream().findFirst().get());
    }
}
