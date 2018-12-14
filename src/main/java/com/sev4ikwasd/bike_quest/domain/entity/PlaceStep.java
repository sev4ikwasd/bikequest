package com.sev4ikwasd.bike_quest.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "place_steps")
public class PlaceStep extends Step{

    @Column(name = "latency_place_dot")
    private double latencyPlaceDot;

    @Column(name = "longitude_place_dot")
    private double longitudePlaceDot;

    @Column(name = "place_radius")
    private double placeRadius;

    @Column(name = "showed_latency_place_dot")
    private double showedLatencyPlaceDot;

    @Column(name = "showed_longitude_place_dot")
    private double showedLongitudePlaceDot;

    @Column(name = "showed_place_radius")
    private double showedPlaceRadius;

    public PlaceStep(String text, double latencyPlaceDot, double longitudePlaceDot, double placeRadius, double showedLatencyPlaceDot, double showedLongitudePlaceDot, double showedPlaceRadius) {
        super(text);
        this.latencyPlaceDot = latencyPlaceDot;
        this.longitudePlaceDot = longitudePlaceDot;
        this.placeRadius = placeRadius;
        this.showedLatencyPlaceDot = showedLatencyPlaceDot;
        this.showedLongitudePlaceDot = showedLongitudePlaceDot;
        this.showedPlaceRadius = showedPlaceRadius;
    }
}
