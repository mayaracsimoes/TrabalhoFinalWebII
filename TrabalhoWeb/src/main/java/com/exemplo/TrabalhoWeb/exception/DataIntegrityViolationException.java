package com.exemplo.TrabalhoWeb.exception;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String pMensagem) {
        super(pMensagem);
    }

    public DataIntegrityViolationException(String pMensagem, Throwable pCausa) {
        super(pMensagem, pCausa);
    }
}
