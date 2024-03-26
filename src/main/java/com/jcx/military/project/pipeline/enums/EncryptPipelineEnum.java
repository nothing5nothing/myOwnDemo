package com.jcx.military.project.pipeline.enums;

public enum EncryptPipelineEnum {

    NO_ENCRYPT,

    ENCRYPT_BY_PATH,

    ENCRYPT_BY_STREAM;

    public static EncryptPipelineEnum getEncryptEnum(boolean encrypt,
                                              boolean useRange,
                                              boolean tag) {
        if(!encrypt) {
            return NO_ENCRYPT;
        }
        if(tag|useRange){
            return ENCRYPT_BY_PATH;
        }
        return ENCRYPT_BY_STREAM;
    }

}
