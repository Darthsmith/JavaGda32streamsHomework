package homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
@SuppressWarnings({"WeakerAccess","FieldCanBeLocal"})

public class OlympicsTxtProcessing {

    private int COUNTRY_INDEX=0;
    private int GOLDEN_MEDALS_INDEX=1;
    private int SILVER_MEDALS_INDEX=2;
    private int BRONZE_MEDALS_INDEX=3;
    private int FOURTH_PLACE_INDEX=4;
    private int WITH_FOURTH_PLACE_DATA_LENGTH=5;

    private OlympicsDataValidator validator;

    public OlympicsTxtProcessing(final OlympicsDataValidator validator) {
        this.validator = validator;
    }

    private OlympicsData toOlympicsData(final String[] splitLineData){
        final String countryShortName = splitLineData[COUNTRY_INDEX];
        final int goldenMedalsAmount = Integer.parseInt( splitLineData[GOLDEN_MEDALS_INDEX] );
        final int silverMedalsAmount = Integer.parseInt( splitLineData[SILVER_MEDALS_INDEX] );
        final int bronzeMedalsAmount = Integer.parseInt( splitLineData[BRONZE_MEDALS_INDEX] );
        final int fourthPlacesAmount = addFourthPlaces( splitLineData );

        return OlympicsData.builder()
                .countryShortName(countryShortName)
                .goldenMedalsAmount( goldenMedalsAmount )
                .silverMedalsAmount( silverMedalsAmount )
                .bronzeMedalsAmount( bronzeMedalsAmount )
                .fourthPlacesAmount( fourthPlacesAmount )
                .build();
    }

    public List<OlympicsData> readData4txt (final String path){
        try {
            return Files.readAllLines( Paths.get(path)).stream()
                    .filter( line ->  !line.isEmpty() )
                    .map( line -> line.replace( "(" ,"" ) )
                    .map( line -> line.replace( ")" ,"" ) )
                    .map( line -> line.split( "," ) )
                    .map( splitLineData -> toOlympicsData(splitLineData))
                    .filter(validator::isValid)
                    .collect( Collectors.toList());

        } catch (final IOException exp) {
            throw new GenericException( "File " + path + " not found", exp );
        }
    }

    private int addFourthPlaces(final String[] splitLineData) {
        if (splitLineData.length == WITH_FOURTH_PLACE_DATA_LENGTH) {
            return Integer.parseInt( splitLineData[FOURTH_PLACE_INDEX] );
        }
        return 0;
    }


}
