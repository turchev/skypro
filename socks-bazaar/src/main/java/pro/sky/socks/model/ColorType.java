package pro.sky.socks.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ColorType {
    RED("Красный"),
    WHITE("Белый"),
    BLACK("Черный");

    @Getter
    private final String colorRu;
}
