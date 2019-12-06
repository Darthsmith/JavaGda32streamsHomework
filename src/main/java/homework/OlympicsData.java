package homework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("WeakerAccess")

public class OlympicsData {

private String countryShortName;
private int goldenMedalsAmount;
private int silverMedalsAmount;
private int bronzeMedalsAmount;
private int fourthPlacesAmount;
}
