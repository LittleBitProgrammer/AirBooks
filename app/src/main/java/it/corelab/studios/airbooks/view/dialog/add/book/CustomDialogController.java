package it.corelab.studios.airbooks.view.dialog.add.book;

import it.corelab.studios.airbooks.model.data.ADD.BOOK.PostAddBook;

public interface CustomDialogController {
    void setPositiveButtonAction(PostAddBook postAddBook, String url, String lang, String os, String token);
}
