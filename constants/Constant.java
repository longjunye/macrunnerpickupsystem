package com.fairymo.macrunnerpickupsystem.constants;

public class Constant {
	//request
	public static final String REQUEST_SUCCESS = "success";
	public static final String REQUEST_TOKEN = "token";

	public static final String LOGIN = "login";
	public static final String SHOPPING_ID_SET = "shopping_id_set";

	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
	public static final String REGEX_PASSWORD = "[a-zA-Z0-9\\W_]{8,}";
	public static final String REGEX_SMS_CODE = "^\\d{6}$";

	public static final String IMAGE_TYPE_PNG = ".png";
	public static final String IMAGE_TYPE_JPEG = ".jpg";

	public static final String ALGORITHM_SHA1 = "SHA1";
	public static final String ALGORITHM_SHA256 = "SHA-256";
	public static final String ALGORITHM_HMAC_256 = "HmacSHA256";

	//pay mode
	public static final String PAY_MODE_E_CASH = "E_CASH";
	public static final String PAY_MODE_SOUND_WAVE = "SOUNDWAVE";
	public static final String PAY_MODE_NFC = "NFC";
	public static final String PAY_MODE_CODE_SCAN = "CODE_SCAN";
	public static final String PAY_MODE_MANUAL = "MANUAL";

	public static final String POS_APP_ID = "469384ae64660c3801648e7fe53101e9";
	public static final String POS_APP_KEY = "73c3608ebf044e7690af82a7b83eebb1";
	public static final String POS_TERMINAL_ID = "70174121";
	public static final String POS_CUSTOMER_ID = "898310173993003";
	public static final String RMB_TRANSACTION_CURRENCY_CODE = "156";

	public static final String INTENT_PRINTER = "printer";
	public static final String INTENT_ORDER = "order";
	public static final String INTENT_PAY_SUCCESS_RESPONSE = "pay_success_response";
	public static final String INTENT_SHOPPING_RESPONSE_EXTRA = "shopping_response_extra";
	public static final String INTENT_PAY = "pay";

	public static final int SHOPPING_PAGE_REQUEST_CODE = 10023;
	public static final int SCANNER_REQUEST_CODE = 10024;

	public static final String SCANDIT_LICENSE_KEY = "AZBsnzaGKmI4KFihPBuQ2VAsEK9mKWY+fX6s/W9r3hzIcdq6mmOKJTVC06DyQWQYMVP0XVtmpRfQIdPKLE6Zcdl8g16JcU5GTTQH+7F5Ub0JdshWTUBhIMF+Rub2EqZvSj8qh6pPY02RPNkKAweEZubUjm5QDUqbBEFZ1GehxBsWKLq+G9JLrXQREfYisQSwAz4vLLaMSksUuxe48EyWn9vMnnz7Kc3DyTXDMeYsMzzomZeSNVEH2RPldvAIr5RMs+ZpzYoZ8UfYp+Mm3bMnJszUAAEcC5NU1QjS/BCVE9+03o7/S68fgA0kBny5ch4m2GCeCG99XVFHPOA/5A8K3yh8SpkbCcE5NbTnhTIFr8qQkGBiFsdzcEGxJdytOAauxZ930PoyD9mOFCLqZJeYezP4x3UXf+pQQsk67kL9O51T072fD0tULbLKs0aBdXCjLQvWBwXmfmERRwRMCV3Pvc2RusWBhXo4Z89mRud4o121tsC9VyqTU0G+Y+73yY0Y8dKwGjup/no+QkSNtj2mmf+3WZOVYTr6fN7JQHmCyqf83h5VBdoPrGracmwob0szfo8z43nckRG2YStkXRzM6HMtLxhEwfHEuBDE/4KDOiO9FdJ0jB0vDzjAiQPGRYznNfu72RXqLui0lzclMl3oG8YXosyFyeeyp+KQLMbKx7SzSD4itAr7pNzw0Gx1xiNCCyLiN+gP17KxB2Zw35B6gjpBE/HBusIDNv0k5sViJMN2bodTRQVkPh2WetwjryEzF35XdaHtqDEk9Z2rZAvsjZTLqbfm2VegOucqizSrZ6/BhMxAdg==";

	public static final String DEFAULT_SHOPPING_NO = "MC099";
	public static final String DEFAULT_BRAND_NO = "07";
	public static final String SHOPPING_NO = "SHOPPING_NO";
	public static final String BRAND_NO = "BRAND_NO";
}
