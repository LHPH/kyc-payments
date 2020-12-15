package com.kyc.payments.constants;

public interface Constants {

    String NAME_SPACE_WSDL_PAYMENTS_URI = "http://kyc-payments/KYCPayments.wsdl";
    String NAME_SPACE_PAYMENTS_URI = "http://kyc-payments.com/PaymentTypes";
    String NAME_SPACE_HEADER_URI = "http://kyc-payments.com/HeaderTypes";

    String ERROR_CODE_01 ="E01";
    String ERROR_DESC_01 = "Ocurrio un error inesperado en la aplicacion.";

    String ERROR_CODE_02 ="E02";
    String ERROR_DESC_02 = "No se pudo autenticar al usuario.";

    String ERROR_CODE_03 ="E03";
    String ERROR_DESC_03 = "El cargo ya ha sido pagado.";

    String ERROR_CODE_04 ="E04";
    String ERROR_DESC_04 = "No existe informacion del cargo con la referencia ingresada.";

    String ERROR_CODE_05 ="E05";
    String ERROR_DESC_05 = "No existe informacion del estatus del pago ingresado.";

    String ERROR_CODE_06 ="E06";
    String ERROR_DESC_06 = "No existe informacion del pago ingresado.";

    String ERROR_CODE_07 = "E07";
    String ERROR_DESC_07 = "La entidad bancaria ingresada no existe o no esta autorizada.";

    String ERROR_CODE_08 = "E08";
    String ERROR_DESC_08 = "No se pudo efectuar el pago.";

}
