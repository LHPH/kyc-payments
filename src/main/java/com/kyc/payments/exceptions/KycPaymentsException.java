package com.kyc.payments.exceptions;


import com.kyc.payments.ws.coretypes.ErrorData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KycPaymentsException extends Exception{

    private ErrorData errorData;

}
