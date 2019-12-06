package homework;

@SuppressWarnings("WeakerAccess")

public class OlympicsDataValidator {


    protected boolean isValid(final OlympicsData data){
        return isShortNameValid( data ) && isGoldenMedalsAmountValid( data )
                && isSilverMedalsAmountValid( data ) && isBronzeMedalsAmountValid( data )
                && isFourthPlacesAmountValid( data );
    }

    private boolean isShortNameValid(final OlympicsData data) {
        return data.getCountryShortName().length() == 3;
    }

    private boolean isGoldenMedalsAmountValid(final OlympicsData data) {
        return data.getGoldenMedalsAmount() >= 0;
    }

    private boolean isSilverMedalsAmountValid(final OlympicsData data){
        return data.getSilverMedalsAmount() >= 0;
    }

    private boolean isBronzeMedalsAmountValid(final OlympicsData data){
        return data.getBronzeMedalsAmount() >= 0;
    }

    private boolean isFourthPlacesAmountValid(final OlympicsData data){
        return data.getFourthPlacesAmount() >= 0;
    }
}
