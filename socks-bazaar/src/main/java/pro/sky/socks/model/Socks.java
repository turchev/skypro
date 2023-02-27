package pro.sky.socks.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode
public class Socks {
    @NotNull
    private ColorType colorType;
    @NotNull
    private SizeType sizeType;
    @Min(value = 0, message = "Процент должен быть положительным числом")
    @Max(value = 100, message = "Процент должен быть меньше или равен 100")
    private int cottonPart;

    @Override
    public String toString() {
        return String.format("Носки модные: Цвет %s, Размер %s, Содержание хлопка %s%%",
                getColorType().getColorRu(),
                getSizeType().getSizeNum(),
                getCottonPart()).trim();
    }
}
