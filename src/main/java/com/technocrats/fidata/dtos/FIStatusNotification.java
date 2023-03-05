package com.technocrats.fidata.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIStatusNotification {
    public String sessionId;

    public String sessionStatus;

    public List<FIStatusResponse> FIStatusResponse;
}
