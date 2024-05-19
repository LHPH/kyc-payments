package com.kyc.payments.constants;

public final class AppConstants {

    public static final String NAME_SPACE_PAYMENTS_URI = "http://kyc-payments.com/PaymentTypes";
    public static final String NAME_SPACE_HEADER_URI = "http://kyc-payments.com/HeaderTypes";

    public static String ERROR_CODE_01 ="E01";
    public static String ERROR_DESC_01 = "Ocurrio un error inesperado en la aplicacion.";

    public static String ERROR_CODE_02 ="E02";
    public static String ERROR_DESC_02 = "No se pudo autenticar al usuario.";

    public static String ERROR_CODE_03 ="E03";
    public static String ERROR_DESC_03 = "El cargo ya ha sido pagado.";

    public static String ERROR_CODE_04 ="E04";
    public static String ERROR_DESC_04 = "No existe informacion del cargo con la referencia ingresada.";

    public static String ERROR_CODE_05 ="E05";
    public static String ERROR_DESC_05 = "No existe informacion del estatus del pago ingresado.";

    public static String ERROR_CODE_06 ="E06";
    public static String ERROR_DESC_06 = "No existe informacion del pago ingresado.";

    public static String ERROR_CODE_07 = "E07";
    public static String ERROR_DESC_07 = "La entidad bancaria ingresada no existe o no esta autorizada.";

    public static String ERROR_CODE_08 = "E08";
    public static String ERROR_DESC_08 = "No se pudo efectuar el pago.";

    public static final String ERROR_CODE_001 = "001";
    public static final String ERROR_CODE_002 = "002";
    public static final String ERROR_CODE_003 = "003";
    public static final String ERROR_CODE_004 = "004";
    public static final String ERROR_CODE_005 = "005";
    public static final String ERROR_CODE_006 = "006";

    private AppConstants(){}
}
