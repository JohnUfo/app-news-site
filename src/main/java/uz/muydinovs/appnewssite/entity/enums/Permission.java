package uz.muydinovs.appnewssite.entity.enums;

public enum Permission {
    ADD_USER,   //ADMIN
    EDIT_USER,   //ADMIN
    DELETE_USER,   //ADMIN
    VIEW_USER,   //ADMIN

    ADD_ROLE,   //ADMIN
    EDIT_ROLE,   //ADMIN
    DELETE_ROLE,   //ADMIN
    VIEW_ROLE,   //ADMIN

    ADD_POST,   //ALL
    EDIT_POST,   //ALL
    DELETE_POST,   //ALL

    ADD_COMMENT,   //ALL
    EDIT_COMMENT,   //ALL
    DELETE_MY_COMMENT,   //ALL
    DELETE_COMMENT   //ADMIN
}
