package task.raif.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Sock {
    @NotBlank
    private final String color;

    @Min(0)
    @Max(100)
    private final int cottonPart;

    public Sock(String color, int cottonPart) {

        this.color = color;

        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sock sock = (Sock) o;
        return cottonPart == sock.cottonPart && Objects.equals(color, sock.color);
    }

    @Override
    public String toString() {
        return "Sock{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                '}';
    }

    @Override
    public int hashCode() {
        return color.hashCode()+cottonPart;
    }
}
