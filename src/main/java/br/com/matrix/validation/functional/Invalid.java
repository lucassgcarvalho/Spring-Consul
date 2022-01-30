package br.com.matrix.validation.functional;

import java.util.Objects;
import java.util.Optional;

public final class Invalid implements ValidationResult {
    private final String reason;

    Invalid(String reason) {
        this.reason = reason;
    }

    public String reason() {
        return reason;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Invalid) obj;
        return Objects.equals(this.reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason);
    }

    @Override
    public String toString() {
        return "Invalid[" +
                "reason=" + reason + ']';
    }

    public boolean isValid() {
        return false;
    }

    public Optional<String> getReason() {
        return Optional.of(reason);
    }

}