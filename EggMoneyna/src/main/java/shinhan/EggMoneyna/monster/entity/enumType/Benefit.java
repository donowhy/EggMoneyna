package shinhan.EggMoneyna.monster.entity.enumType;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Benefit {


    ConvenienceStore("ConvenienceStore", "편의점"),
    Movie("Movie", "영화"),
    Delivery("Delivery","배달"),
    Music("Music", "음악"),
    OTT("OTT", "OTT");

    private final String key;
    private final String title;

}
