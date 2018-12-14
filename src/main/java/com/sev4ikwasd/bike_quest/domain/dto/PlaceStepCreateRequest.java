package com.sev4ikwasd.bike_quest.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PlaceStepCreateRequest extends StepCreateRequest {
    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("latency_place_dot")
    double latencyPlaceDot;

    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("longitude_place_dot")
    double longitudePlaceDot;

    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("place_radius")
    double placeRadius;

    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("showed_latency_place_dot")
    double showedLatencyPlaceDot;

    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("showed_longitude_place_dot")
    double showedLongitudePlaceDot;

    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    //@JsonProperty("showed_place_radius")
    double showedPlaceRadius;

    public PlaceStepCreateRequest(String text, double latencyPlaceDot, double longitudePlaceDot, double placeRadius, double showedLatencyPlaceDot, double showedLongitudePlaceDot, double showedPlaceRadius) {
        super(text);
        this.latencyPlaceDot = latencyPlaceDot;
        this.longitudePlaceDot = longitudePlaceDot;
        this.placeRadius = placeRadius;
        this.showedLatencyPlaceDot = showedLatencyPlaceDot;
        this.showedLongitudePlaceDot = showedLongitudePlaceDot;
        this.showedPlaceRadius = showedPlaceRadius;
    }
}
