package homework;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OlympicsStatisticsService {

    //znajdzie państwa które zdobyły przynajmniej jeden złoty medal
    public List<String> ifGotOneGoldenMedal(final Collection<OlympicsData> data) {
        return data.stream()
                .filter( list -> list.getGoldenMedalsAmount() > 0 )
                .map( olympicsData -> olympicsData.getCountryShortName() )
                .collect( Collectors.toList() );
    }

    //znajdzie państwa które zdobyły przynajmniej jeden jakikolwiek medal
    public List<String> ifGotAnyMedal(final Collection<OlympicsData> data) {
        return data.stream()
                .filter( list -> list.getGoldenMedalsAmount() > 0 ||
                        list.getSilverMedalsAmount() > 0 ||
                        list.getBronzeMedalsAmount() > 0 )
                .map( olympicsData -> olympicsData.getCountryShortName() )
                .collect( Collectors.toList() );
    }

    //znajdzie państwo które zdobyło najwięcej złotych medali
    public String countryThatGotTheMostGoldenMedals(final Collection<OlympicsData> data) {
        return data.stream()
                .collect( Collectors.groupingBy( goldenMedalsAmount -> goldenMedalsAmount.getGoldenMedalsAmount() ) )
                .values().stream()
                .flatMap( Collection::stream )
                .map( olympicsData -> olympicsData.getCountryShortName() )
                .reduce( (first, second) -> second )
                .orElse( null );
    }

    //znajdzie państwo które zdobyło najwięcej medali (w sumie)
    public LinkedHashMap<String,Integer> countryThatGotTheMostMedals(final Collection<OlympicsData> data) {
        return data.stream()
//                .map( medalsCombined -> medalsCombined.getGoldenMedalsAmount()+medalsCombined.getSilverMedalsAmount()+medalsCombined.getBronzeMedalsAmount() )
                .collect( Collectors.toMap(
                        countryShortName -> countryShortName.getCountryShortName(),
                        medalsCombined -> medalsCombined.getGoldenMedalsAmount()
                                + medalsCombined.getSilverMedalsAmount()
                                + medalsCombined.getBronzeMedalsAmount()) )
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap( stringIntegerEntry -> stringIntegerEntry.getKey(), Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    //najwięcej 4tych miejsc - ostateczna metoda
    public String countryThatGotTheMostFourthPlaces(final Collection<OlympicsData> data) {
        return data.stream()
                .collect( Collectors.groupingBy( fourthPlacesAmount -> fourthPlacesAmount.getFourthPlacesAmount() ) )
                .values().stream()
                .flatMap( olympicsData -> olympicsData.stream() )
                .map( OlympicsData::getCountryShortName )
                .reduce( (first, second) -> second )
                .orElse( null );
    }

    // sorted 4te miejsca - pokazanie błędnego sortowania
    public List<String> sortedFourthPlaces(final Collection<OlympicsData> data) {
        return data.stream()
                .collect( Collectors.groupingBy( fourthPlacesAmount -> fourthPlacesAmount.getFourthPlacesAmount() ) )
                .values().stream()
                .sorted( (olympicsData, o) -> olympicsData.lastIndexOf( o ) )
                .flatMap( olympicsData1 -> olympicsData1.stream() )
                .map( OlympicsData::getCountryShortName )
                .collect( Collectors.toList() );
    }

    // więcej srebrnych od złotych
    public List<String> moreSilverMedalsThanGolden(final Collection<OlympicsData> data) {
        return data.stream()
                .filter( list-> list.getSilverMedalsAmount() > list.getGoldenMedalsAmount())
                .map( olympicsData -> olympicsData.getCountryShortName() )
                .collect( Collectors.toList() );
    }

    public List<String> moreBronzeThanSilverAndSilverThanGold(final Collection<OlympicsData> data) {
        return data.stream()
                .filter( list-> (list.getBronzeMedalsAmount() > list.getSilverMedalsAmount())
                        && list.getSilverMedalsAmount() > list.getGoldenMedalsAmount())
                .map( olympicsData -> olympicsData.getCountryShortName() )
                .collect( Collectors.toList() );
    }


    public Map<String,Integer> howManyMedalsEachCountryGot(final Collection<OlympicsData> data) {
        return data.stream()
                .collect( Collectors.toMap(
                        countryShortName -> countryShortName.getCountryShortName(),
                        medalsCombined -> medalsCombined.getGoldenMedalsAmount()
                                + medalsCombined.getSilverMedalsAmount()
                                + medalsCombined.getBronzeMedalsAmount() ) )
                .entrySet().stream()
                .collect( Collectors.toMap(countryName -> countryName.getKey(), medalsAmount -> medalsAmount.getValue()) );
    }
    // dominanta złotych medali
    //działa nie do końca
    public Map.Entry<Integer, List<List<OlympicsData>>> goldenMedalsDominant(final Collection<OlympicsData> data){

        final var groupingByGoldenMedals = data.stream()
                .collect( Collectors.groupingBy( goldenMedalsAmount -> goldenMedalsAmount.getGoldenMedalsAmount()) );

        var groupByGoldenMedalsAmountOcurrence = groupingByGoldenMedals.values().stream() // <{1,1,1},{2,2,2},{3,3},{4,4,4,4}>
                .collect( Collectors.groupingBy( goldenMedalsOcurrence -> goldenMedalsOcurrence.size() ) ).entrySet();

        return Collections.max( groupByGoldenMedalsAmountOcurrence, Comparator.comparing(x -> x.getKey()) );
                //największa ilośc wystąpień tego samego goldenMedalAmounts

    }


}
