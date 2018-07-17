package it.corelab.studios.airbooks.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import it.corelab.studios.airbooks.R;

import static android.app.Activity.RESULT_OK;


public class AddBook_fragment extends Fragment implements View.OnClickListener {

    protected View view;
    private FragmentManager fragmentManager;
    private ImageView centralCard;
    private ImageButton returnButton;
    private Button nextButton;
    private TextInputLayout textInputLayout;
    private Uri pickedImage;
    private EditText editText;
    private TextInputEditText editTextInput;
    public static final int PICK_IMAGE = 1;

    public AddBook_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_add_section, container, false);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        initViews();
        setListeners();
        return view;
    }

    private void initViews(){

        fragmentManager = getActivity().getSupportFragmentManager();
        centralCard = view.findViewById(R.id.placeholder_add);
        returnButton = view.findViewById(R.id.left_arrow_add_categories);
        nextButton = view.findViewById(R.id.color_button_next_add_book);
        editTextInput = view.findViewById(R.id.edit_text);
        editText = view.findViewById(R.id.edit_text_hidden);
        textInputLayout = view.findViewById(R.id.text_input_layout);

        editText.setEnabled(false);

        textInputLayout.setHint("Insert your title here");


    }

    private void setListeners(){
        centralCard.setOnClickListener(this);
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter_animation,R.anim.left_exit_animation)
                            .replace(R.id.view_pager,new FadeFragment(), "AddFragment")
                            .commit();
                    return true;
                }
                return false;
            }
        } );
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.placeholder_add:

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;
        }
    }

    public boolean isEditTextEmpty(EditText editText){
        return  editText.length() == 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap
            centralCard.setImageURI(pickedImage);


            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }

}
