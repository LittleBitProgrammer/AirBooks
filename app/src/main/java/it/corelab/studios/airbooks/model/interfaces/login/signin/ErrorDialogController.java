package it.corelab.studios.airbooks.model.interfaces.login.signin;

import cn.pedant.SweetAlert.SweetAlertDialog;

public interface ErrorDialogController {

    void showProgressDialog(SweetAlertDialog dialog);

    void showErrorDialog();

}
