package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryGraphDto {
    private double convenienceStore;
    private double restaurants;
    private double game;
    private double shopping;
    private double cafe;
    private double etc;

    @Builder
    public CategoryGraphDto(double convenienceStore, double restaurants, double game, double shopping, double cafe, double etc) {
        this.convenienceStore = convenienceStore;
        this.restaurants = restaurants;
        this.game = game;
        this.shopping = shopping;
        this.cafe = cafe;
        this.etc = etc;
    }
}
