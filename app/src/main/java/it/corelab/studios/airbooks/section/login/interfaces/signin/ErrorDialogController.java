package it.corelab.airbooks.section.login.interfaces.signin;

import cn.pedant.SweetAlert.SweetAlertDialog;

public interface ErrorDialogController {

    void showProgressDialog(SweetAlertDialog dialog);

    void showErrorDialog();

}
