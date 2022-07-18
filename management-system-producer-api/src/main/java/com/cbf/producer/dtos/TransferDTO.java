package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO extends ModelDTO {

    private Long id;

    private TeamDTO originTeam;

    private TeamDTO destinyTeam;

    private LocalDate date;

    private Float value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamDTO getOriginTeam() {
        return originTeam;
    }

    public void setOriginTeam(TeamDTO originTeam) {
        this.originTeam = originTeam;
    }

    public TeamDTO getDestinyTeam() {
        return destinyTeam;
    }

    public void setDestinyTeam(TeamDTO destinyTeam) {
        this.destinyTeam = destinyTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
