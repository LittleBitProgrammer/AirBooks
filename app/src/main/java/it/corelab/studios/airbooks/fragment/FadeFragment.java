package it.corelab.studios.airbooks.fragment;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import it.corelab.studios.airbooks.CustomNested;
import it.corelab.studios.airbooks.Http.HttpHandler;
import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.adapters.CardViewReviewAdapter;
import it.corelab.studios.airbooks.adapters.SnapExploreRecyclerAdapter;
import it.corelab.studios.airbooks.adapters.SnapLibraryAdapter;
import it.corelab.studios.airbooks.adapters.SnapRecyclerAdapter;
import it.corelab.studios.airbooks.object.Book;
import it.corelab.studios.airbooks.object.Item;
import it.corelab.studios.airbooks.object.User;
import it.corelab.studios.airbooks.recyclerViewExtension.SnappingRecyclerView;
import it.corelab.studios.airbooks.widget.RoundedImageView;

/*

* This is official coded by © Roberto Vecchio and property of all member of: corelab
* if you want to use this code send an email to: vecchioroberto93@gmail.com
* Any uses without permission could be subject to legal action

 */

public class FadeFragment extends Fragment {


   /*

   * Initialization of the @GLOBAL recyclerView
   * The first recyclerView is used for the management of section in the refresh method

    */

   private RecyclerView recyclerView;
   private GetBestOfWeek asyncTask;
   private GetBestOfWeek asyncTaskExplorer;
    private GetBestOfWeek asyncTaskLib;
   private GetCurrentUser userAsync;
   private static User userclass;
   private TextView nameSurname;
   private TextView fromTo;
   private TextView followers;
   private RoundedImageView profileImage;
   private static ProgressDialog pDialog;
   public static CustomNested customNested;
   public static ImageView trapezoid;
   public static float angleVariation;
   public static int yPosition;

   /*

   * Initialization of the ArrayList
   * These ArrayList are used to populate the cell of the recyclerView
   * Modify the generics < > to change what type you would use

    */

   private ArrayList<Item> items;
   private ArrayList<Item> reviewCard;
    private ArrayList<Item> rvCategoriesItem;
   private static ArrayList<Book> bookArrayList;


   /*

   * create a new instance of the fragment
   * this instance of fragment is used by section to initialize different layout

    */


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (asyncTask != null) {
            asyncTask.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }else if (userAsync != null) {
            userAsync.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }else if (asyncTaskExplorer != null){
            asyncTaskExplorer.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }
    }


   /*=====================================================================
                                onCreateView
    ====================================================================*/

   /*

   * This is our onCreate method
   * @onCreate call different layout
   * based on the index that the bottomBar return in airbook's app
   * after the index return the method to init our choosen @USER INTERFACE
   * and finally return the @VIEW to the user

   */


   @Override
   public View onCreateView(@NonNull LayoutInflater inflanter, ViewGroup container, Bundle savedInstanceState){

       switch (getArguments().getInt("index", 0)) {
           case 0:
           case 1: {
               View view = inflanter.inflate(R.layout.explore_fragment, container, false);
               initExplore(view);
               return view;
           }
           case 2: {
               View view = inflanter.inflate(R.layout.library_fragment, container, false);
               initLibrary(view);
               return view;
           }
           case 3: {
               View view = inflanter.inflate(R.layout.profile_fragment, container, false);
               initProfile(view);
               return view;
           }
           default: {
               View view = inflanter.inflate(R.layout.profile_fragment, container, false);
               initProfile(view);
               return view;
           }
       }

   }


    /*=====================================================================
                                initExplore()
    =====================================================================*/

    /*

    * Explore initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the explore section

     */

    public void initExplore(View view){
        bookArrayList = new ArrayList<>();


        /*

        * these method initialize and fill the empty recyclerView with the choosen elements
        * @EMPTY RECYCLERVIEW FILLED:
        *
        * 1. ExploreCard

         */



        /*

        Initialize the @fragmentContainer with the choosen layout

         */

        //exploreDiagonal = view.findViewById(R.id.diagonal_view_explore);

        /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line

         */



        /*

        * The first command enable the @SCALING on horizontal scroll of @recyclerView
        * The second command enable the @ALPHA SCALING on horizontal scroll of @recyclerView
        * uncomment second command to alpha scaling

         */

        //recyclerCardExplore.enableAlphaScaling(true);


        /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */


        /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

        //OverScrollDecoratorHelper.setUpOverScroll(recyclerCardExplore, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        asyncTaskExplorer = new GetBestOfWeek(this);
        asyncTaskExplorer.setListener(new GetBestOfWeek.BestOfWekkAsyncTaskLinestener() {
            @Override
            public void onExampleAsyncTaskFinished(Integer value) {
                // update UI in Activity here
                //dismiss the progress dialog
              /*  if (pDialog.isShowing()){
                    pDialog.dismiss();
                }*/

            }
        });
        asyncTaskExplorer.execute();

    }


    /*=====================================================================
                                initLibrary()
    =====================================================================*/

    /*

    * Library initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the library section

     */

    public void initLibrary(View view){


        /*

        Initialize the @fragmentContainer with the choosen layout

         */


        //libDiagonal = view.findViewById(R.id.diagonal_view_library);

        //3.GROUP
        final RecyclerView recyclerViewLibrary = view.findViewById(R.id.recycler_view_cardView_library);


        /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

        recyclerViewLibrary.setItemViewCacheSize(20);
        recyclerViewLibrary.setDrawingCacheEnabled(true);
        recyclerViewLibrary.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        /*

        * Here we are setting the layout manager that manage the recyclerView
        * Change it carefully, it could change completely your layout appearance
        * we set also a commmand for each recyclerView to have a fixedSize

         */

        recyclerViewLibrary.setLayoutManager(new GridLayoutManager(getActivity(),3, GridLayout.VERTICAL,false));
        recyclerViewLibrary.setHasFixedSize(true);

        /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

        //OverScrollDecoratorHelper.setUpOverScroll(recyclerViewLibrary, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        asyncTaskLib = new GetBestOfWeek(this);
        asyncTaskLib.setListener(new GetBestOfWeek.BestOfWekkAsyncTaskLinestener() {
            @Override
            public void onExampleAsyncTaskFinished(Integer value) {
                // update UI in Activity here
                //dismiss the progress dialog
              /*  if (pDialog.isShowing()){
                    pDialog.dismiss();
                }*/

                SnapLibraryAdapter snapLibraryAdapter = new SnapLibraryAdapter(getActivity(), bookArrayList );
                recyclerViewLibrary.setAdapter(snapLibraryAdapter);
            }
        });
        asyncTaskLib.execute();

    }


    /*=====================================================================
                                initProfile()
    =====================================================================*/

    /*

    * Profile initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the profile section

     */

   public void initProfile(View view){

       /*

        Initialize the @fragmentContainer with the choosen layout

         */

       //profileDiagonal = view.findViewById(R.id.diagonal_view_profile);


       /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line

         */

       //1. GROUP
       final Button buttonAction = view.findViewById(R.id.buttonAction);
       final TextView nameSurname = view.findViewById(R.id.NameSurname);
       final TextView fromTo = view.findViewById(R.id.fromTo);
       final TextView followers = view.findViewById(R.id.followNumber);
       final RoundedImageView profileImage = view.findViewById(R.id.profileImagePlaceHolder);

       //userAsync = new GetCurrentUser(this);
      /* userAsync.setListener(new GetCurrentUser.userAsyncTaskLinestener() {
           @Override
           public void onExampleAsyncTaskFinished(Integer value) {
               // update UI in Activity here
               nameSurname.setText(userclass.getFirstName() + " " + userclass.getLastName());
               fromTo.setText(userclass.getNationality());
               followers.setText(userclass.getFollower() + "");
               Picasso.get().load(userclass.getProfilePictureUrl()).into(profileImage);
           }
       });
       userAsync.execute();*/
      
       //3.GROUP
       RecyclerView cardReviewRecycleView = view.findViewById(R.id.recycler_view_cardView);
       RecyclerView recyclerViewAllBooks = view.findViewById(R.id.recycler_view);

       /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

       recyclerViewAllBooks.setItemViewCacheSize(20);
       recyclerViewAllBooks.setDrawingCacheEnabled(true);
       recyclerViewAllBooks.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


       /*

        * Here we are setting the layout manager that manage the recyclerView
        * Change it carefully, it could change completely your layout appearance
        * we set also a commmand for each recyclerView to have a fixedSize

         */

       recyclerViewAllBooks.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       recyclerViewAllBooks.setHasFixedSize(true);

       cardReviewRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       cardReviewRecycleView.setHasFixedSize(true);


       /*

        * we can't use one adapter for all the recyclerView
        * so here we are initializing and creating different adapter
        * and then assigning to the different recyclerView
        * @FOLLOW the guidelines of these

         */

       SnapRecyclerAdapter adapter = new SnapRecyclerAdapter(getActivity(), items);
       recyclerViewAllBooks.setAdapter(adapter);

       CardViewReviewAdapter reviewAdapter = new CardViewReviewAdapter(getActivity(),reviewCard);
       cardReviewRecycleView.setAdapter(reviewAdapter);


       /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

       //OverScrollDecoratorHelper.setUpOverScroll(recyclerViewAllBooks, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
       //OverScrollDecoratorHelper.setUpOverScroll(cardReviewRecycleView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);


       /*

        * This method is used to enlarge the hit area of @addButton
        * for now the area is 200, to modify comunicate it to design team

         */

       final View parent = (View) buttonAction.getParent();  // button: the view you want to enlarge hit area
       parent.post( new Runnable() {
           public void run() {
               final Rect rect = new Rect();
               buttonAction.getHitRect(rect);
               rect.top -= 200;    // increase top hit area
               rect.left -= 200;   // increase left hit area
               rect.bottom += 200; // increase bottom hit area
               rect.right += 200;  // increase right hit area
               parent.setTouchDelegate( new TouchDelegate( rect , buttonAction));
           }
       });



       /*

        * This method is used for action on tap of @addButton
        * In this case it set the popUp menu action

         */

       buttonAction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu = new PopupMenu(getActivity(), buttonAction);
               popupMenu.getMenuInflater().inflate(R.menu.actions, popupMenu.getMenu());

               popupMenu.show();
           }
       });

   }


   /*=====================================================================
                           Filling recyclerView methods
    =====================================================================*/

    /*

    * These methods is used to fill the elements of recyclerViews
    * But for now are only for placeHolder
    * @NEXT TODO:// collegue to dataBase

     */

    public static class GetBestOfWeek extends AsyncTask<Void,Void,Integer> {

        private WeakReference<FadeFragment> activityReference;

        private String TAG = FadeFragment.class.getSimpleName();
        private String url ="http://airbooks.altervista.org/API/v2/books/";
        private GetBestOfWeek.BestOfWekkAsyncTaskLinestener listener;

        GetBestOfWeek(FadeFragment context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            FadeFragment activity = activityReference.get();
            //showing progress dialog
           /* pDialog = new ProgressDialog(activity.getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        @Override
        protected Integer doInBackground(Void... arg0){
            final FadeFragment activity = activityReference.get();

            HttpHandler sh = new HttpHandler();

            //Making a request to url and getting a response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject result = jsonObject.getJSONObject("result");

                    //Getting JSON Array node
                    JSONArray books = result.getJSONArray("items");

                    //looping through all books
                    for (int i = 0; i < books.length(); i++){
                        JSONObject c = books.getJSONObject(i);

                        String id = c.getString("id");
                        String userID = c.getString("user_id");
                        String title = c.getString("bookTitle");
                        String description = c.getString("description");
                        String genreID = c.getString("genre_id");
                        String language = c.getString("language");
                        String uploadDate = c.getString("upload_date");
                        String coverUrl = c.getString("cover_url");
                        String bookUrl = c.getString("book_url");
                        String authorName = c.getString("author_first_name");
                        String authorLastName = c.getString("author_last_name");
                        int readings = c.getInt("readings");
                        int lovers = c.getInt("lovers");
                        double averageRatings = c.getDouble("average_rating");
                        //boolean isSaved = c.getBoolean("is_saved");

                        //temp hash map for single book
                        HashMap<String,Object> bookItem = new HashMap<>();

                        //adding each child node to HashMap key => value
                        bookItem.put("id",id);
                        bookItem.put("user_id", userID);
                        bookItem.put("bookTitle", title);
                        bookItem.put("description", description);
                        bookItem.put("genre_id", genreID);
                        bookItem.put("language", language);
                        bookItem.put("upload_date", uploadDate);
                        bookItem.put("cover_url",coverUrl);
                        bookItem.put("book_url", bookUrl);
                        bookItem.put("readings", readings);
                        bookItem.put("lovers", lovers);
                        bookItem.put("average_rating", averageRatings);
                        bookItem.put("author_first_name",authorName);
                        bookItem.put("author_last_name", authorLastName);
                        // bookItem.put("is_saved", isSaved);

                        Book bookClass = new Book(bookItem);

                        bookArrayList.add(bookClass);
                    }
                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.getActivity(),"JSon parsing error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else{
                Log.e(TAG,"Couldn't get json from server");
                (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getActivity(),"Couldn't get json from server", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer value){
            super.onPostExecute(value);

            if (listener != null) {
                listener.onExampleAsyncTaskFinished(value);
            }
        }

        void setListener(GetBestOfWeek.BestOfWekkAsyncTaskLinestener listener) {
            this.listener = listener;
        }

        public interface BestOfWekkAsyncTaskLinestener {
            void onExampleAsyncTaskFinished(Integer value);
        }
    }

    public static class GetCurrentUser extends AsyncTask<Void,Void,Integer> {

        private String TAG = FadeFragment.class.getSimpleName();
        private String urlUser = "http://airbooks.altervista.org/API/v2/users/5ae64d9f3d1570.58512716/all";
        private GetCurrentUser.userAsyncTaskLinestener listener;
        private WeakReference<FadeFragment> activityReference;


        GetCurrentUser(FadeFragment context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            Log.e(TAG, "please wait");
        }

        @Override
        protected Integer doInBackground(Void... arg0){
            final FadeFragment activity = activityReference.get();
            HttpHandler sh = new HttpHandler();

            //Making a request to url and getting a response
            String jsonStr = sh.makeServiceCall(urlUser);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject userObj = jsonObject.getJSONObject("result");

                    String ID = userObj.getString("id");
                    String firstName = userObj.getString("first_name");
                    String lastName = userObj.getString("last_name");
                    String profilePictureUrl = userObj.getString("profile_picture_url");
                    String nationality = userObj.getString("nationality");
                    String email = userObj.getString("email");
                    int followers = Integer.parseInt(userObj.getString("followers_count"));

                    //temp hash map for single book
                    HashMap<String, Object> userItem = new HashMap<>();

                    userItem.put("id",ID);
                    userItem.put("first_name", firstName);
                    userItem.put("last_name", lastName);
                    userItem.put("profile_picture_url", profilePictureUrl);
                    userItem.put("nationality", nationality);
                    userItem.put("followers_count", followers);
                    userItem.put("email",email);

                    userclass = new User(userItem);

                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.getActivity(),"JSon parsing error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else{
                Log.e(TAG,"Couldn't get json from server");
                ((Objects.requireNonNull(activity.getActivity()))).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getActivity(),"Couldn't get json from server", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer value){
            super.onPostExecute(value);

            if (listener != null) {
                listener.onExampleAsyncTaskFinished(value);
            }
        }

        void setListener(GetCurrentUser.userAsyncTaskLinestener listener) {
            this.listener = listener;
        }

        public interface userAsyncTaskLinestener {
            void onExampleAsyncTaskFinished(Integer value);
        }
    }
}