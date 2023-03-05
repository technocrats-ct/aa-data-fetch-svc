package com.technocrats.fidata.data;


import com.technocrats.fidata.dtos.ErrorInfo;
import com.technocrats.fidata.dtos.FIStatusNotification;
import com.technocrats.fidata.dtos.FiFetchRespBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "FiFetchDetails")
public class FiFetchDetails {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    private FIStatusNotification fiStatusNotification;

    private FiDataRequestDetails fiDataRequestDetails;

    private FiFetchRespBody fiFetchRespBody;

    private ErrorInfo errorInfo;


}
