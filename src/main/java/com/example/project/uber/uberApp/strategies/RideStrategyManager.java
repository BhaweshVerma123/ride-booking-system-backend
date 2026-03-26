package com.example.project.uber.uberApp.strategies;

import com.example.project.uber.uberApp.strategies.Impl.DriverMatchingHighestRatedDriverStrategy;
import com.example.project.uber.uberApp.strategies.Impl.DriverMatchingNearestDriverStrategy;
import com.example.project.uber.uberApp.strategies.Impl.DriverMatchingNearestDriverStrategy;
import com.example.project.uber.uberApp.strategies.Impl.RideFareSurgePricingFareCalculationStrategy;
import com.example.project.uber.uberApp.strategies.Impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@RequiredArgsConstructor
@Component
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy  defaultFareCalculationStrategy;



    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
    if(riderRating >=4.8){
        return highestRatedDriverStrategy;
    }
    else{
        return nearestDriverStrategy;
    }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime currentTime = LocalTime.now();
        LocalTime surgeStartTime=LocalTime.of(18,0);
        LocalTime surgeEndTime=LocalTime.of(21,0);
        boolean isSurgeTime =currentTime.isAfter(surgeStartTime) &&  currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;

        }
        else{
            return defaultFareCalculationStrategy;
        }
    }

}




