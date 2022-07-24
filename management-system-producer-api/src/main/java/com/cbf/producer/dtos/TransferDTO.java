package com.cbf.producer.dtos;

import com.cbf.producer.core.dtos.ModelDTO;
import com.cbf.producer.domain.Player;
import com.cbf.producer.domain.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransferDTO extends ModelDTO {

    private Long id;

    private TeamDTO originTeam;

    private TeamDTO destinyTeam;

    private LocalDate date;

    private Float value;

    private PlayerDTO player;

    public TransferDTO(Transfer transfer) {
        this.id = transfer.getId();
        this.originTeam = new TeamDTO(transfer.getOriginTeam());
        this.destinyTeam = new TeamDTO(transfer.getDestinyTeam());
        this.date = transfer.getDate();
        this.value = transfer.getValue();
        this.player = new PlayerDTO(transfer.getPlayer());
    }
}
