package pro.sky.socks.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SizeType {
    S36(36),
    S38(38),
    S40(40),
    S42(42);

    @Getter
    private final int sizeNum;
}
