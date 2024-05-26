package com.exemplo.TrabalhoWeb.exception;

public class NoSuchElementException extends RuntimeException{
    public NoSuchElementException(String pMensagem) {
        super(pMensagem);
    }

    public NoSuchElementException(String pMensagem, Throwable pCausa) {
        super(pMensagem, pCausa);
    }
}
