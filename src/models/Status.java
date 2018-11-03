package models;

import java.io.Serializable;

public enum Status implements Serializable {

    VALID,
    WRONG_TYPE,
    ID_NOT_FOUND,
    ID_WRONG_FORMAT,
    NOT_A_PERSON,
    ID_NOT_REQUIRED,
    ID_REQUIRED;

    private static final long serialVersionUID = 1L;

}
