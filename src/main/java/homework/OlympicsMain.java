package homework;

import java.util.*;

public class OlympicsMain {

    public static void main(String[] args) {

        final String pathToLoad = args[0];

        final OlympicsDataValidator validator = new OlympicsDataValidator();
        final OlympicsTxtProcessing service = new OlympicsTxtProcessing( validator );
        final OlympicsStatisticsService statisticsService = new OlympicsStatisticsService();

//        service.readData4txt( pathToLoad )
//                .forEach( x -> System.out.println( x ) );


        //output państwa które zdobyły przynajmniej jeden złoty medal

//                statisticsService.ifGotOneGoldenMedal(service.readData4txt(pathToLoad))
//                .forEach( (x) -> System.out.println(x) );

        //output państwa które zdobyły przynajmniej jeden jakikolwiek medal

//        statisticsService.ifGotAnyMedal(service.readData4txt(pathToLoad))
//                .forEach( (x) -> System.out.println(x) );

        //output państwa które zdobyło najwięcej złotych medali

//        String countryThatGotTheMostGoldenMedals = statisticsService.countryThatGotTheMostGoldenMedals( service.readData4txt( pathToLoad ) );
//        System.out.println(countryThatGotTheMostGoldenMedals);


        //output państwa które zdobyło najwięcej medali (w sumie)

//        Map<String,Integer> combinedMedalsOfCountries = statisticsService.countryThatGotTheMostMedals( service.readData4txt( pathToLoad ) );
//
//        // odczytywanie ostatniego elementu mapy

//        List<Map.Entry<String,Integer>> entryList =
//                new ArrayList<>(combinedMedalsOfCountries.entrySet());
//        Map.Entry<String, Integer> lastCountryOnList =
//                entryList.get(entryList.size()-1);
//        System.out.println(lastCountryOnList);


        //Posortowana lista wg 4tych miejsc
        statisticsService.sortedFourthPlaces(service.readData4txt(pathToLoad))
                .forEach( (x) -> System.out.println(x) );


        //output najwięcej 4tych miejsc

        String countryThatGotTheMostFourthPlaces = statisticsService.countryThatGotTheMostFourthPlaces( service.readData4txt( pathToLoad ) );
        System.out.println(countryThatGotTheMostFourthPlaces);


        // ilosc srebrnych medali > ilosc złotych medali

//        statisticsService.moreSilverMedalsThanGolden( service.readData4txt( pathToLoad ) )
//                .forEach( x -> System.out.println(x) );

        // znajdzie kraje które zdobyły więcej brązowych medali od srebrnych i więcej srebrnych od złotych

//                statisticsService.moreBronzeThanSilverAndSilverThanGold( service.readData4txt( pathToLoad ) )
//                .forEach( x -> System.out.println(x) );

        //zwróci statystyki (tzn. Map<String, Integer>) ile państ w sumie zdobyło medali
//                statisticsService.howManyMedalsEachCountryGot( service.readData4txt( pathToLoad ) )
//                .forEach( (x,y) -> System.out.println(x+ " "+y) );

        //znajdzie dominantę złotych medali zdobytych per państwo - nie dokończone

        Map.Entry<Integer, List<List<OlympicsData>>> goldenMedalsDominant = statisticsService.goldenMedalsDominant( service.readData4txt( pathToLoad ) );
        System.out.println(goldenMedalsDominant);
    }
}
