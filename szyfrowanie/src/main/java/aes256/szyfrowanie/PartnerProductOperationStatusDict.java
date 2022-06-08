package aes256.szyfrowanie;

public enum PartnerProductOperationStatusDict {

    SUCCESS("Success"), FAIL("Fail"), REJECTED("Rejected");

    PartnerProductOperationStatusDict(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
